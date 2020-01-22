package com.cpe.team24;

import com.cpe.team24.entity.*;
import com.cpe.team24.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PaymentTest {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentWayRepository paymentWayRepository;

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6000035_testAddSuccess(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);

        payment = paymentRepository.saveAndFlush(payment);

        Payment payment2 = paymentRepository.findById(payment.getId()).get();

        assertEquals(date,payment2.getPayDate());
        assertEquals(amount,payment2.getAmount());
        assertEquals(paymentWay.getId(),payment2.getPaymentWay().getId());
        assertEquals(flightBooking.getId(),payment2.getFlightBooking().getId());
        assertEquals(promotionCode,payment2.getPromotionCode());
    }

    @Test
    void b6000035_testPhoneMustNotBe9digit(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("012345678");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 10 and 10",v.getMessage());
        assertEquals("phone",v.getPropertyPath().toString());
    } 

    @Test
    void b6000035_testPhoneMustNotBe11digit(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("01234567890");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 10 and 10",v.getMessage());
        assertEquals("phone",v.getPropertyPath().toString());
    }

    @Test
    void b6000035_testEmailValidation(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("seez");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void b6000035_testPhoneMatPattern(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("Safi!#^ge*");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must match \"[0-9]*\"", v.getMessage());
        assertEquals("phone", v.getPropertyPath().toString());
    }

    // +++++++++++++++++ NOTNULL+++++++++++++++++++

    @Test
    void b6000035_tesPayDateMustBeNull(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        // Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(null);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("payDate", v.getPropertyPath().toString());
    }

    @Test
    void b6000035_tesAmountMustBeNull(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        // Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(null);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void b6000035_tesPhoneMustBeNull(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone(null);
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("phone", v.getPropertyPath().toString());
    }

    @Test
    void b6000035_tesEmailMustBeNull(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail(null);
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    

    @Test
    void b6000035_testPaymentWayMustBeNull(){
        Payment payment = new Payment();
        FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        // PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(null);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("paymentWay", v.getPropertyPath().toString());
    }

    @Test
    void b6000035_testFlightBookingMustBeNull(){
        Payment payment = new Payment();
        // FlightBooking flightBooking = flightBookingRepository.findById(1l).get();
        PaymentWay paymentWay = paymentWayRepository.findById(1l).get();
        PromotionCode promotionCode = promotionCodeRepository.findByCode("ABC123");
        Date date = new Date();
        Double amount = 3500.00;

        payment.setPhone("0123456789");
        payment.setEmail("test@gmail.com");
        payment.setPayDate(date);
        payment.setAmount(amount);
        payment.setFlightBooking(null);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("flightBooking", v.getPropertyPath().toString());
    }


    

}