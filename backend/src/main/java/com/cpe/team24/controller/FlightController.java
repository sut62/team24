package com.cpe.team24.controller;

import com.cpe.team24.entity.Airport;
import com.cpe.team24.entity.EFlightAirportType;
import com.cpe.team24.entity.Flight;
import com.cpe.team24.entity.FlightAirport;
import com.cpe.team24.model.BodyFlight;
import com.cpe.team24.repository.*;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

@RestController
@RequestMapping(path="/api/flight")
public class FlightController {
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

    public FlightController(FlightRepository flightRepository) {

        this.flightRepository = flightRepository;
    }
    
    @GetMapping("")
    public Collection<Flight> getFlight(){
        return flightRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Flight getFlightById(@PathVariable Long id){
        return flightRepository.findById(id).orElse(null);
    }
    

    @DeleteMapping("/id/{id}")
    public boolean deleteFlight(@Valid @PathVariable Long id){
        flightRepository.deleteById(id);
        return true;
    }


    // For Book Flight - ToeiKanta had been creating.
    @GetMapping("/{date}/{airport_depart_id}/{airport_arrive_id}")
    public Collection<Flight> getFlightByDepartDate(@PathVariable String date,@PathVariable Long airport_depart_id,@PathVariable Long airport_arrive_id) throws ParseException {
        Date departDayStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String dateEnd = getNextDate(date);
        Date departDayEnd = new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd);
        Collection<Flight> flights = flightRepository.findAllByDepartBetween(departDayStart,departDayEnd);
        //Filter flights by depart airport
        List<Flight> departFlight = new ArrayList<>();
        for(Flight flight : flights){
            for(FlightAirport flightAirport : flight.getFlightAirports()){
                Airport airport = flightAirport.getAirport();
                if(flightAirport.getFlightAirportType() == flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT)){
                    if (
                        airport == airportRepository.findById(airport_depart_id).orElseThrow(() -> new MessageDescriptorFormatException("Not found depart airport ID"))
                        & flight.getLastTempSeatId() < flight.getAirplane().getSeatAmout()
                    ) {
                        departFlight.add(flight);
                    }
                }

            }
        }
        //Filter flight by depart and arrive airport
        List<Flight> results = new ArrayList<>();
        for(Flight flight : departFlight){
            for(FlightAirport flightAirport : flight.getFlightAirports()){
                Airport airport = flightAirport.getAirport();
                if(flightAirport.getFlightAirportType() == flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT)){
                    if (airport == airportRepository.findById(airport_arrive_id).orElseThrow(() -> new MessageDescriptorFormatException("Not found arrive airport ID"))) {
                        results.add(flight);
                    }
                }
            }
        }

        //System.out.println(departDayStart);
        //System.out.println(departDayEnd);
        //System.out.println(result);
        return results;
    }

    // For Book Flight - ToeiKanta had been creating.
    @GetMapping("/{date}")
    public Collection<Flight> getFlightByDepartDate(@PathVariable String date) throws ParseException {
        Date departDayStart = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String dateEnd = getNextDate(date);
        Date departDayEnd = new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd);
        Collection<Flight> result = flightRepository.findAllByDepartBetween(departDayStart,departDayEnd);
        //System.out.println(departDayStart);
        //System.out.println(departDayEnd);
        //System.out.println(result);
        return result;
    }

    @PostMapping("/create")
    public Flight createFlight(@RequestBody BodyFlight bodyFlight) throws ParseException {
        Flight flight = new Flight();
        flight.setPrice(bodyFlight.getPrice());

        // date create
        Date depart = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(bodyFlight.getDepartDate());
        Date arrive = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(bodyFlight.getArriveDate());
        //
        flight.setDepart(depart);
        flight.setArrive(arrive);
        flight.setAirplane(airplaneRepository.findById(bodyFlight.getAirplaneId()).orElse(null));
        flight = flightRepository.save(flight);

        //FlightAirport depart
        FlightAirport flightAirport = new FlightAirport();
        flightAirport.setAirport(airportRepository.findById(bodyFlight.getDepartAirportId()).orElse(null));
        flightAirport.setFlightAirportType(flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT));
        flightAirport.setFlight(flightRepository.findById(flight.getId()).orElse(null));
        flightAirportRepository.save(flightAirport);

        //FlightAirport arrive
        flightAirport = new FlightAirport();
        flightAirport.setAirport(airportRepository.findById(bodyFlight.getArriveAirportId()).orElse(null));
        flightAirport.setFlightAirportType(flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT));
        flightAirport.setFlight(flightRepository.findById(flight.getId()).orElse(null));
        flightAirportRepository.save(flightAirport);

        return flightRepository.findById(flight.getId()).orElse(null);
    }
    // This Function return Tomorrow's Date from Today's Date - ToeiKanta had been creating.
    public String getNextDate(String curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

}
