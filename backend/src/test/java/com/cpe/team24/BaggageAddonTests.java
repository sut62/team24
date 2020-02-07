package com.cpe.team24;

import com.cpe.team24.entity.*;
import com.cpe.team24.repository.*;
import com.cpe.team24.repository.auth.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class BaggageAddonTests {

    private Validator validator;

    @Autowired
    private   BaggageAddonRepository baggageaddonRepository;

    @Autowired
    private BaggageImageRepository baggageimageRepository;

    @Autowired
    private BaggageTypeRepository baggagetypeRepository;

    @Autowired
    private  AirportRepository airportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6026066_testAddBaggageSuccess() {
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(5);
        baggageAddon.setPrice(500.00);

        baggageAddon.setBaggageType(baggagetypeRepository.findById(1L));
        baggageAddon.setBaggageImage(baggageimageRepository.findById(1L));
        baggageAddon.setAirport(airportRepository.findById(1L).orElse(null));

        baggageAddon = baggageaddonRepository.saveAndFlush(baggageAddon);

        Optional<BaggageAddon> found = baggageaddonRepository.findById(baggageAddon.getId());
        assertEquals(5, found.get().getMaxWeight());
        assertEquals(500, found.get().getPrice());
        assertEquals(1L, found.get().getBaggageType().getId());
        assertEquals(1L, found.get().getBaggageImage().getId());
        assertEquals(1L, found.get().getAirport().getId());
    }

    @Test
    void b6026066_maxWeightMustNotBeNull(){
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(null);
        baggageAddon.setPrice(500.00);

        baggageAddon.setBaggageType(baggagetypeRepository.findById(1L));
        baggageAddon.setBaggageImage(baggageimageRepository.findById(1L));
        baggageAddon.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(baggageAddon);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("maxWeight", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_maxPriceNotBeNull(){
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(5);
        baggageAddon.setPrice(null);

        baggageAddon.setBaggageType(baggagetypeRepository.findById(1L));
        baggageAddon.setBaggageImage(baggageimageRepository.findById(1L));
        baggageAddon.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(baggageAddon);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_BaggagetypeMustNotBeNull(){
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(10);
        baggageAddon.setPrice(500.00);

        baggageAddon.setBaggageType(null);
        baggageAddon.setBaggageImage(baggageimageRepository.findById(1L));
        baggageAddon.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(baggageAddon);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("baggageType", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_BaggageImageMustNotBeNull(){
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(10);
        baggageAddon.setPrice(500.00);

        baggageAddon.setBaggageType(baggagetypeRepository.findById(1L));
        baggageAddon.setBaggageImage(null);
        baggageAddon.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(baggageAddon);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("baggageImage", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_BaggageAirportMustNotBeNull(){
        BaggageAddon baggageAddon= new BaggageAddon();
        baggageAddon.setMaxWeight(10);
        baggageAddon.setPrice(500.00);

        baggageAddon.setBaggageType(baggagetypeRepository.findById(1L));
        baggageAddon.setBaggageImage(baggageimageRepository.findById(1L));
        baggageAddon.setAirport(null);

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(baggageAddon);
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("airport", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_PriceNoneNegative() {
        BaggageAddon bag = new BaggageAddon();
        bag.setMaxWeight(10);
        bag.setPrice(-10.00);

        bag.setBaggageType(baggagetypeRepository.findById(1L));
        bag.setBaggageImage(baggageimageRepository.findById(1L));
        bag.setAirport(airportRepository.findById(1L).orElse(null));

        
        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(bag);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_MaxWeightNoneNegative() {
        BaggageAddon bag = new BaggageAddon();
        bag.setMaxWeight(-10);
        bag.setPrice(500.00);

        bag.setBaggageType(baggagetypeRepository.findById(1L));
        bag.setBaggageImage(baggageimageRepository.findById(1L));
        bag.setAirport(airportRepository.findById(1L).orElse(null));

        
        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(bag);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("maxWeight", v.getPropertyPath().toString());
    }
    
    @Test
    void b6026066_WeightMax10000() {
        BaggageAddon bag = new BaggageAddon();
        bag.setMaxWeight(100000);
        bag.setPrice(500.00);

        bag.setBaggageType(baggagetypeRepository.findById(1L));
        bag.setBaggageImage(baggageimageRepository.findById(1L));
        bag.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(bag);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        System.out.println("======================================="+ result);
        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must be less than or equal to 10000", v.getMessage());
        assertEquals("maxWeight", v.getPropertyPath().toString());
    }

    @Test
    void b6026066_PriceMax100000() {
        BaggageAddon bag = new BaggageAddon();
        bag.setMaxWeight(10);
        bag.setPrice(100000000000.00);

        bag.setBaggageType(baggagetypeRepository.findById(1L));
        bag.setBaggageImage(baggageimageRepository.findById(1L));
        bag.setAirport(airportRepository.findById(1L).orElse(null));

        Set<ConstraintViolation<BaggageAddon>> result = validator.validate(bag);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
        System.out.println("======================================="+ result);
        // error message ตรงชนิด และถูก field
        ConstraintViolation<BaggageAddon> v = result.iterator().next();
        assertEquals("must be less than or equal to 100000", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    
}