package com.cpe.team24;

import com.cpe.team24.entity.*;
import com.cpe.team24.entity.auth.ERole;
import com.cpe.team24.entity.auth.Role;
import com.cpe.team24.repository.*;
import com.cpe.team24.repository.auth.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@DataJpaTest
@SpringBootTest
public class BookFlightTests {

    private Validator validator;

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private FlightBookingLinkRepository flightBookingLinkRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingTypeRepository flightBookingTypeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void b6000530_testAddSuccess() {
        FlightBooking flightBooking = new FlightBooking();
        BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
        FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
        FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

        User user = userRepository.findByUsername("alice").get();
        flightBooking.book(1,1);
        String bookId = flightBooking.getBookId();
        Date date = flightBooking.getDate();
        flightBooking.setBookingStatus(pendingStatus);
        flightBooking.setUser(user);
        flightBooking = flightBookingRepository.saveAndFlush(flightBooking);

        // Add Depart's Flight and Return's Flight to TableLink
        FlightBookingLink flightBookingLinkDepart = new FlightBookingLink();
        Flight departFlight = flightRepository.findById(1l).get();
        flightBookingLinkDepart.setFlight(departFlight);
        flightBookingLinkDepart.setFlightBooking(flightBooking);
        flightBookingLinkDepart.setFlightBookingType(departType);
        //save
        flightBookingLinkDepart = flightBookingLinkRepository.saveAndFlush(flightBookingLinkDepart);

        FlightBookingLink flightBookingLinkReturn = new FlightBookingLink();
        Flight returnFlight = flightRepository.findById(2l).get();
        flightBookingLinkReturn.setFlight(returnFlight);
        flightBookingLinkReturn.setFlightBooking(flightBooking);
        flightBookingLinkReturn.setFlightBookingType(returnType);
        //save
        flightBookingLinkReturn = flightBookingLinkRepository.saveAndFlush(flightBookingLinkReturn);

        // Validation Test case
        FlightBooking tempFlightBooking = flightBookingRepository.findById(flightBooking.getId()).get();

        assertEquals(date,tempFlightBooking.getDate());
        assertEquals(1,tempFlightBooking.getReturnSeatId());
        assertEquals(1,tempFlightBooking.getDepartSeatId());
        assertEquals(bookId,tempFlightBooking.getBookId());
        assertEquals(pendingStatus.getName(),tempFlightBooking.getBookingStatus().getName());
        assertEquals(user.getUsername(),tempFlightBooking.getUser().getUsername());

        //Check Depart
        assertEquals(departFlight,flightBookingLinkDepart.getFlight());
        assertEquals(flightBooking,flightBookingLinkDepart.getFlightBooking());
        assertEquals(departType,flightBookingLinkDepart.getFlightBookingType());
        //Check Return
        assertEquals(returnFlight,flightBookingLinkReturn.getFlight());
        assertEquals(flightBooking,flightBookingLinkReturn.getFlightBooking());
        assertEquals(returnType,flightBookingLinkReturn.getFlightBookingType());

    }

    @Test
    void testBookIdMustNotBeNull() {
        FlightBooking flightBooking = new FlightBooking();
        BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
        FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
        FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

        User user = userRepository.findByUsername("alice").get();
        flightBooking.book(1,1);
        String bookId = flightBooking.getBookId();
        Date date = flightBooking.getDate();
        flightBooking.setBookId(null);
        flightBooking.setBookingStatus(pendingStatus);
        flightBooking.setUser(user);
        Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FlightBooking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookId", v.getPropertyPath().toString());
    }

    @Test
    void testBookIdMustNotBe5Digit() {
        FlightBooking flightBooking = new FlightBooking();
        BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
        FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
        FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

        User user = userRepository.findByUsername("alice").get();
        flightBooking.book(1,1);
        String bookId = flightBooking.getBookId();
        Date date = flightBooking.getDate();
        flightBooking.setBookId("12345");
        flightBooking.setBookingStatus(pendingStatus);
        flightBooking.setUser(user);
        Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FlightBooking> v = result.iterator().next();
        assertEquals("size must be between 6 and 10", v.getMessage());
        assertEquals("bookId", v.getPropertyPath().toString());
    }
    @Test
    void testBookIdMustNotBe11Digit() {
        FlightBooking flightBooking = new FlightBooking();
        BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
        FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
        FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

        User user = userRepository.findByUsername("alice").get();
        flightBooking.book(1,1);
        String bookId = flightBooking.getBookId();
        Date date = flightBooking.getDate();
        flightBooking.setBookId("12345678901");
        flightBooking.setBookingStatus(pendingStatus);
        flightBooking.setUser(user);
        Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FlightBooking> v = result.iterator().next();
        assertEquals("size must be between 6 and 10", v.getMessage());
        assertEquals("bookId", v.getPropertyPath().toString());
    }
    @Test
    void testBookIdMustFitToPattern() {
        FlightBooking flightBooking = new FlightBooking();
        BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
        FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
        FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

        User user = userRepository.findByUsername("alice").get();
        flightBooking.book(1,1);
        String bookId = flightBooking.getBookId();
        Date date = flightBooking.getDate();
        flightBooking.setBookId("34ฟ$5%as");
        flightBooking.setBookingStatus(pendingStatus);
        flightBooking.setUser(user);
        Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<FlightBooking> v = result.iterator().next();
        assertEquals("must match \"[A-Z0-9]*\"", v.getMessage());
        assertEquals("bookId", v.getPropertyPath().toString());
    }

//    @Test
//    void testPersonIdMustNotBe14Digits() {
////        Person person = new Person();
////        person.setPersonId("12345678901234"); // 14 digits
////
////        Set<ConstraintViolation<Person>> result = validator.validate(person);
////
////        // result ต้องมี error 1 ค่าเท่านั้น
////        assertEquals(1, result.size());
////
////        // error message ตรงชนิด และถูก field
////        ConstraintViolation<Person> v = result.iterator().next();
////        assertEquals("must match \"\\d{13}\"", v.getMessage());
////        assertEquals("personId", v.getPropertyPath().toString());
//    }
//
//    @Test
//    void testPersonIdMustBeUnique() {
////        // สร้าง person object
////        Person p1 = new Person();
////        p1.setPersonId("1234567890123");
////        personRepository.saveAndFlush(p1);
////
////        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
////        assertThrows(DataIntegrityViolationException.class, () -> {
////            // สร้าง person object ตัวที่ 2
////            Person p2 = new Person();
////            p2.setPersonId("1234567890123");
////            personRepository.saveAndFlush(p2);
////        });
//    }

}


















