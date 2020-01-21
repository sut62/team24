// package com.cpe.team24;

// import com.cpe.team24.entity.*;
// import com.cpe.team24.repository.*;
// import com.cpe.team24.repository.auth.RoleRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.dao.DataIntegrityViolationException;

// import javax.validation.ConstraintViolation;
// import javax.validation.Validation;
// import javax.validation.Validator;
// import javax.validation.ValidatorFactory;
// import java.util.*;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

 
// //@DataJpaTest
// @SpringBootTest
// public class BookFlightTests {

//     private Validator validator;

//     @Autowired
//     private FlightBookingRepository flightBookingRepository;

//     @Autowired
//     private FlightBookingLinkRepository flightBookingLinkRepository;

//     @Autowired
//     private BookingStatusRepository bookingStatusRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private FlightRepository flightRepository;

//     @Autowired
//     private FlightBookingTypeRepository flightBookingTypeRepository;

//     @Autowired
//     private RoleRepository roleRepository;

//     @BeforeEach
//     public void setup() {
//         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         validator = factory.getValidator();
//     }

//     // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
//     @Test
//     void b6000530_testAddSuccess() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         flightBooking = flightBookingRepository.saveAndFlush(flightBooking);

//         // Add Depart's Flight and Return's Flight to TableLink
//         FlightBookingLink flightBookingLinkDepart = new FlightBookingLink();
//         Flight departFlight = flightRepository.findById(1l).get();
//         flightBookingLinkDepart.setFlight(departFlight);
//         flightBookingLinkDepart.setFlightBooking(flightBooking);
//         flightBookingLinkDepart.setFlightBookingType(departType);
//         //save
//         flightBookingLinkDepart = flightBookingLinkRepository.saveAndFlush(flightBookingLinkDepart);

//         FlightBookingLink flightBookingLinkReturn = new FlightBookingLink();
//         Flight returnFlight = flightRepository.findById(2l).get();
//         flightBookingLinkReturn.setFlight(returnFlight);
//         flightBookingLinkReturn.setFlightBooking(flightBooking);
//         flightBookingLinkReturn.setFlightBookingType(returnType);
//         //save
//         flightBookingLinkReturn = flightBookingLinkRepository.saveAndFlush(flightBookingLinkReturn);

//         // Validation Test case
//         FlightBooking tempFlightBooking = flightBookingRepository.findById(flightBooking.getId()).get();

//         assertEquals(date,tempFlightBooking.getDate());
//         assertEquals(1,tempFlightBooking.getReturnSeatId());
//         assertEquals(1,tempFlightBooking.getDepartSeatId());
//         assertEquals(bookId,tempFlightBooking.getBookId());
//         assertEquals(pendingStatus.getName(),tempFlightBooking.getBookingStatus().getName());
//         assertEquals(user.getUsername(),tempFlightBooking.getUser().getUsername());

//         //Check Depart
//         assertEquals(departFlight,flightBookingLinkDepart.getFlight());
//         assertEquals(flightBooking,flightBookingLinkDepart.getFlightBooking());
//         assertEquals(departType,flightBookingLinkDepart.getFlightBookingType());
//         //Check Return
//         assertEquals(returnFlight,flightBookingLinkReturn.getFlight());
//         assertEquals(flightBooking,flightBookingLinkReturn.getFlightBooking());
//         assertEquals(returnType,flightBookingLinkReturn.getFlightBookingType());

//     }

//     @Test
//     void b6000530_testBookingStatusMustNotBeNull() {
//         FlightBooking flightBooking = new FlightBooking();

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         flightBooking.setBookingStatus(null);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("bookingStatus", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testUserMustNotBeNull() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(null);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("user", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testDepartSeatIdMustNotBeNull() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(null,1);
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("departSeatId", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testReturnSeatIdMustNotBeNull() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,null);
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("returnSeatId", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testBookIdMustNotBeNull() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookId(null);
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must not be null", v.getMessage());
//         assertEquals("bookId", v.getPropertyPath().toString());
//     }

//     @Test
//     void b6000530_testBookIdMustNotBe5Digit() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookId("12345");
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("size must be between 6 and 10", v.getMessage());
//         assertEquals("bookId", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testBookIdMustNotBe11Digit() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookId("12345678901");
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("size must be between 6 and 10", v.getMessage());
//         assertEquals("bookId", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testBookIdMustFitToPattern() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookId("ห%$123");
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);

//         Set<ConstraintViolation<FlightBooking>> result = validator.validate(flightBooking);

//         // result ต้องมี error 1 ค่าเท่านั้น
//         assertEquals(1, result.size());

//         // error message ตรงชนิด และถูก field
//         ConstraintViolation<FlightBooking> v = result.iterator().next();
//         assertEquals("must match \"[A-Z0-9]*\"", v.getMessage());
//         assertEquals("bookId", v.getPropertyPath().toString());
//     }
//     @Test
//     void b6000530_testBookIdMustUnique() {
//         FlightBooking flightBooking = new FlightBooking();
//         BookingStatus pendingStatus = bookingStatusRepository.findByName(EBookingStatus.PENDING);
//         FlightBookingType departType = flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT);
//         FlightBookingType returnType = flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT);

//         User user = userRepository.findByUsername("alice").get();
//         flightBooking.book(1,1);
//         String bookId = flightBooking.getBookId();
//         Date date = flightBooking.getDate();
//         flightBooking.setBookingStatus(pendingStatus);
//         flightBooking.setUser(user);
//         flightBooking = flightBookingRepository.saveAndFlush(flightBooking);

//         // Add Depart's Flight and Return's Flight to TableLink
//         FlightBookingLink flightBookingLinkDepart = new FlightBookingLink();
//         Flight departFlight = flightRepository.findById(1l).get();
//         flightBookingLinkDepart.setFlight(departFlight);
//         flightBookingLinkDepart.setFlightBooking(flightBooking);
//         flightBookingLinkDepart.setFlightBookingType(departType);
//         //save
//         flightBookingLinkDepart = flightBookingLinkRepository.saveAndFlush(flightBookingLinkDepart);

//         FlightBookingLink flightBookingLinkReturn = new FlightBookingLink();
//         Flight returnFlight = flightRepository.findById(2l).get();
//         flightBookingLinkReturn.setFlight(returnFlight);
//         flightBookingLinkReturn.setFlightBooking(flightBooking);
//         flightBookingLinkReturn.setFlightBookingType(returnType);
//         //save
//         flightBookingLinkReturn = flightBookingLinkRepository.saveAndFlush(flightBookingLinkReturn);

//         // คาดหวังว่า DataIntegrityViolationException จะถูก throw
//         assertThrows(DataIntegrityViolationException.class, () -> {
//             FlightBooking flightBooking2 = new FlightBooking();
//             flightBooking2.book(1,1);
//             String oldBookId = bookId;
//             flightBooking2.setBookId(oldBookId);
//             flightBooking2.setBookingStatus(pendingStatus);
//             flightBooking2.setUser(user);
//             flightBooking2 = flightBookingRepository.saveAndFlush(flightBooking2);
//         });
//     }

// }


















