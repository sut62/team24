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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



// @DataJpaTest
@SpringBootTest
public class CheckInTests {

    private Validator validator;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private CheckInStatusRepository checkInStatusRepository;

    @Autowired
    private CheckInTypeRepository checkInTypeRepository;

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6012540_testCheckInSaveSuccess() {
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);
        checkIn = checkInRepository.saveAndFlush(checkIn);

        assertEquals("test@gmail.com", checkIn.getEmail());
        assertEquals(date, checkIn.getDate());
        assertEquals(boardingPass, checkIn.getBoardingPass());
        assertEquals(checkInStatus, checkIn.getCheckInStatus());
        assertEquals(checkInType, checkIn.getCheckInType());
        assertEquals(flightBooking, checkIn.getFlightBooking());
    }

    @Test
    void b6012540_testBoardingPassMustBeUnique(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(2L).get();
        Date date = new Date();
        String boardingPass = RandomString.make(6).toUpperCase();
        

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        checkIn = checkInRepository.saveAndFlush(checkIn);
        
         //คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            CheckIn checkIn2 = new CheckIn();
            String oldBoardingPass = boardingPass;
            checkIn2.setCheckInStatus(checkInStatus);
            checkIn2.setCheckInType(checkInType);
            checkIn2.setFlightBooking(flightBooking);
            checkIn2.setBoardingPass(oldBoardingPass);
            checkIn2.setEmail("test@gmail.com");
            checkIn2.setDate(date);

            checkIn2 = checkInRepository.saveAndFlush(checkIn2);
        });

     }
     @Test
    void b6012540_testFlightBookingMustBeUnique(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(8L).get();
        Date date = new Date();
        String boardingPass = RandomString.make(6).toUpperCase();
        

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        checkIn = checkInRepository.saveAndFlush(checkIn);
        
         //คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            CheckIn checkIn2 = new CheckIn();
            checkIn2.setCheckInStatus(checkInStatus);
            checkIn2.setCheckInType(checkInType);
            checkIn2.setFlightBooking(flightBooking);
            checkIn2.setBoardingPass(RandomString.make(6).toUpperCase());
            checkIn2.setEmail("test@gmail.com");
            checkIn2.setDate(date);

            checkIn2 = checkInRepository.saveAndFlush(checkIn2);
        });

     }
     @Test
    void b6012540_testEmailValidation(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("gg");
        checkIn.setDate(date);
        
        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
     }

    @Test
    void b6012540_testBoardingPassMustNotBe5Digit(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass("12345");
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);
        
        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
         //result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<CheckIn> v = result.iterator().next();
        //  System.out.println(v.getMessage());
        //  System.out.println(v.getPropertyPath().toString());
         assertEquals("size must be between 6 and 10", v.getMessage());
         assertEquals("boardingPass", v.getPropertyPath().toString());
     }


    @Test
    void b6012540_testBoardingPassMustNotBe11Digit(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass("12345678901");
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);
        
        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
         //result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<CheckIn> v = result.iterator().next();
        //  System.out.println(v.getMessage());
        //  System.out.println(v.getPropertyPath().toString());
         assertEquals("size must be between 6 and 10", v.getMessage());
         assertEquals("boardingPass", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testBoardingPassMustFitToPattern(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).get();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).get();
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass("S#D2@A");
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);
        
        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
         //result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

         // error message ตรงชนิด และถูก field
         ConstraintViolation<CheckIn> v = result.iterator().next();
        //  System.out.println(v.getMessage());
        //  System.out.println(v.getPropertyPath().toString());
        assertEquals("must match \"[A-Z0-9]*\"", v.getMessage());
        assertEquals("boardingPass", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testCheckInTypeMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(null);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        //System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("checkInType", v.getPropertyPath().toString());
    }
    @Test
    void b6012540_testCheckInStatusMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(null);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        //System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("checkInStatus", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testFlightBookingMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(null);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        //System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("flightBooking", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testEmailMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail(null);
        checkIn.setDate(date);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        //System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testBoardingPassMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        Date date = new Date();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(null);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(date);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("boardingPass", v.getPropertyPath().toString());
    }

    @Test
    void b6012540_testDateMustNotBeNull(){
        CheckIn checkIn = new CheckIn();
        CheckInStatus checkInStatus = checkInStatusRepository.findById((1l)).orElse(null);
        CheckInType checkInType = checkInTypeRepository.findById((1l)).orElse(null);
        FlightBooking flightBooking = flightBookingRepository.findById(1L).get();
        String boardingPass = RandomString.make(6).toUpperCase();

        checkIn.setCheckInStatus(checkInStatus);
        checkIn.setCheckInType(checkInType);
        checkIn.setFlightBooking(flightBooking);
        checkIn.setBoardingPass(boardingPass);
        checkIn.setEmail("test@gmail.com");
        checkIn.setDate(null);

        Set<ConstraintViolation<CheckIn>> result = validator.validate(checkIn);
        //System.out.println("======================================="+ result);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<CheckIn> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }
}