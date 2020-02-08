package com.cpe.team24;

import com.cpe.team24.entity.*;
import com.cpe.team24.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import net.bytebuddy.utility.RandomString;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


// @DataJpaTest
@SpringBootTest
public class FlightTests {

    private Validator validator;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightAirportRepository flightAirportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightAirportTypeRepository flightAirportTypeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6016357_testFlightSaveSuccess() {
        Flight flight = new Flight();
        Airplane airplane = airplaneRepository.findById(1L).get();
        Airport airport = airportRepository.findById(1L).get();
        FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
        FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

        Double price = 1500.4;
       // date create
				Date depart = new Date(
                    new Date().getTime() + ((1000 * 60 * 60 * 24)*2));
            Date arrive = new Date(depart.getTime() + (1000 * 60));
            //

        flight.setPrice(price);
        flight.setDepart(depart);
        flight.setArrive(arrive);
        flight.setAirplane(airplane);
        flight.setDescription("description");
        flight = flightRepository.saveAndFlush(flight);

        //FlightAirport depart
        FlightAirport flightAirportDepart = new FlightAirport();
        flightAirportDepart.setAirport(airport);
        flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
        flightAirportDepart.setFlight(flight);
        flightAirportRepository.saveAndFlush(flightAirportDepart);

        //FlightAirport arrive
        FlightAirport flightAirportArrive = new FlightAirport();
        flightAirportArrive.setAirport(airport);
        flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
        flightAirportArrive.setFlight(flight);
        flightAirportRepository.saveAndFlush(flightAirportArrive);

        //Validation Test case

        assertEquals(price, flight.getPrice());
        assertEquals(depart, flight.getDepart());
        assertEquals(arrive, flight.getArrive());
        assertEquals(airplane, flight.getAirplane());
        assertEquals("description", flight.getDescription());

        //Check Depart
        assertEquals(airport,flightAirportDepart.getAirport());
        assertEquals(flightAirportTypeDepart,flightAirportDepart.getFlightAirportType());
        assertEquals(flight,flightAirportDepart.getFlight());
        //Check Arrive
        assertEquals(airport,flightAirportArrive.getAirport());
        assertEquals(flightAirportTypeArrive,flightAirportArrive.getFlightAirportType());
        assertEquals(flight,flightAirportArrive.getFlight());

    }

   

   @Test
    void b6016357_testPriceMustFollowWithDigitOnly() {
    Flight flight = new Flight();
    Airplane airplane = airplaneRepository.findById(1L).get();
    Airport airport = airportRepository.findById(1L).get();
    FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
    FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

    Double price = -20.44;
    Date depart = new Date();
    Date arrive = new Date();

    flight.setPrice(price);
    flight.setDepart(depart);
    flight.setArrive(arrive);
    flight.setAirplane(airplane);
    flight.setDescription("description");

    //FlightAirport depart
    FlightAirport flightAirportDepart = new FlightAirport();
    flightAirportDepart.setAirport(airport);
    flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
    flightAirportDepart.setFlight(flight);

    //FlightAirport arrive
    FlightAirport flightAirportArrive = new FlightAirport();
    flightAirportArrive.setAirport(airport);
    flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
    flightAirportArrive.setFlight(flight);

        Set<ConstraintViolation<Flight>> result = validator.validate(flight);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Flight> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6016357_testDescriptionMustBeEnglishOnly() {
    Flight flight = new Flight();
    Airplane airplane = airplaneRepository.findById(1L).get();
    Airport airport = airportRepository.findById(1L).get();
    FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
    FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

    Double price = 1500.40;
    Date depart = new Date();
    Date arrive = new Date();

    flight.setPrice(price);
    flight.setDepart(depart);
    flight.setArrive(arrive);
    flight.setAirplane(airplane);
    flight.setDescription("1escription");

    //FlightAirport depart
    FlightAirport flightAirportDepart = new FlightAirport();
    flightAirportDepart.setAirport(airport);
    flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
    flightAirportDepart.setFlight(flight);

    //FlightAirport arrive
    FlightAirport flightAirportArrive = new FlightAirport();
    flightAirportArrive.setAirport(airport);
    flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
    flightAirportArrive.setFlight(flight);

        Set<ConstraintViolation<Flight>> result = validator.validate(flight);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Flight> v = result.iterator().next();
        assertEquals("must match \"[a-z A-Z]*\"", v.getMessage());
        assertEquals("description", v.getPropertyPath().toString());
    }
    
    @Test
    void b6016357_testFlightDepartMustNotBeNull(){
    Flight flight = new Flight();
    Airplane airplane = airplaneRepository.findById(1L).get();
    Airport airport = airportRepository.findById(1L).get();
    FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
    FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

    Double price = 1500.40;
    Date depart = null;
    Date arrive = new Date();

    flight.setPrice(price);
    flight.setDepart(depart);
    flight.setArrive(arrive);
    flight.setAirplane(airplane);
    flight.setDescription("description");

    //FlightAirport depart
    FlightAirport flightAirportDepart = new FlightAirport();
    flightAirportDepart.setAirport(airport);
    flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
    flightAirportDepart.setFlight(flight);

    //FlightAirport arrive
    FlightAirport flightAirportArrive = new FlightAirport();
    flightAirportArrive.setAirport(airport);
    flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
    flightAirportArrive.setFlight(flight);


       Set<ConstraintViolation<Flight>> result = validator.validate(flight);
       System.out.println("======================================="+ result);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Flight> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("depart", v.getPropertyPath().toString());
   }

   @Test
    void b6016357_testFlightArriveMustNotBeNull(){
    Flight flight = new Flight();
    Airplane airplane = airplaneRepository.findById(1L).get();
    Airport airport = airportRepository.findById(1L).get();
    FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
    FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

    Double price = 1500.40;
    Date depart = new Date();
    Date arrive = null;

    flight.setPrice(price);
    flight.setDepart(depart);
    flight.setArrive(arrive);
    flight.setAirplane(airplane);
    flight.setDescription("description");

    //FlightAirport depart
    FlightAirport flightAirportDepart = new FlightAirport();
    flightAirportDepart.setAirport(airport);
    flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
    flightAirportDepart.setFlight(flight);

    //FlightAirport arrive
    FlightAirport flightAirportArrive = new FlightAirport();
    flightAirportArrive.setAirport(airport);
    flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
    flightAirportArrive.setFlight(flight);


       Set<ConstraintViolation<Flight>> result = validator.validate(flight);
       System.out.println("======================================="+ result);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Flight> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("arrive", v.getPropertyPath().toString());
   }

   @Test
    void b6016357_testFlightPriceMustNotBeNull(){
    Flight flight = new Flight();
    Airplane airplane = airplaneRepository.findById(1L).get();
    Airport airport = airportRepository.findById(1L).get();
    FlightAirportType flightAirportTypeDepart = flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT);
    FlightAirportType flightAirportTypeArrive = flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT);

    Double price = null;
    Date depart = new Date();
    Date arrive = new Date();

    flight.setPrice(price);
    flight.setDepart(depart);
    flight.setArrive(arrive);
    flight.setAirplane(airplane);
    flight.setDescription("description");

    //FlightAirport depart
    FlightAirport flightAirportDepart = new FlightAirport();
    flightAirportDepart.setAirport(airport);
    flightAirportDepart.setFlightAirportType(flightAirportTypeDepart);
    flightAirportDepart.setFlight(flight);

    //FlightAirport arrive
    FlightAirport flightAirportArrive = new FlightAirport();
    flightAirportArrive.setAirport(airport);
    flightAirportArrive.setFlightAirportType(flightAirportTypeArrive);
    flightAirportArrive.setFlight(flight);


       Set<ConstraintViolation<Flight>> result = validator.validate(flight);
       System.out.println("======================================="+ result);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Flight> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("price", v.getPropertyPath().toString());
   }


}