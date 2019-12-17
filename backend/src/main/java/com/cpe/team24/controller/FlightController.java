package com.cpe.team24.controller;

import com.cpe.team24.entity.Flight;
import com.cpe.team24.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository){}

    // For Book Flight - ToeiKanta had been creating.
    @GetMapping("/flight/{date}")
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
