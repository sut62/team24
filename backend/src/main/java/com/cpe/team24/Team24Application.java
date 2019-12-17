package com.cpe.team24;

import com.cpe.team24.entity.BookingStatus;
import com.cpe.team24.entity.Flight;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.FlightBookingLink;
import com.cpe.team24.repository.BookingStatusRepository;
import com.cpe.team24.repository.FlightBookingLinkRepository;
import com.cpe.team24.repository.FlightBookingRepository;
import com.cpe.team24.repository.FlightRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class Team24Application {

	public static void main(String[] args) {
		SpringApplication.run(Team24Application.class, args);
	}

	@Bean
	ApplicationRunner init(
			FlightBookingRepository flightBookingRepository,
			BookingStatusRepository bookingStatusRepository,
			FlightRepository flightRepository,
			FlightBookingLinkRepository flightBookingLinkRepository
	){
		return args -> {
			Object[][] data;
			//------------Flight-----------------
			data = new Object[][] {
					{1900},
					{1800}
			};
			for (int i = 0; i < data.length; i++) {
				Flight flight = new Flight();
				flight.setPrice(Double.parseDouble(data[i][0].toString()));
				flight.setDepart(new Date());
				flight.setArrive(new Date());
				flight = flightRepository.save(flight);
				System.out.println("");
				System.out.println("------------Add Flight--------------");
				System.out.println(flight);
				System.out.println("-------------------------------------------");
			}
			//------------Booking Status-----------------
			data = new Object[][] {
					{"ยังไม่ชำระ"},
					{"ชำระแล้ว"},
					{"เช็คอินแล้ว"}
			};
			for (int i = 0; i < data.length; i++) {
				BookingStatus bookingStatus = new BookingStatus();
				bookingStatus.setName(data[i][0].toString());
				bookingStatus = bookingStatusRepository.save(bookingStatus);
				System.out.println("");
				System.out.println("------------Add BookingStatus--------------");
				System.out.println(bookingStatus);
				System.out.println("-------------------------------------------");
			}
			//--------------Flight Booking-----------------
			data = new Object[][] {
					{2,1,1,1},
					{1,2,2,2}
			};
			for (int i = 0; i < data.length; i++) {
				FlightBooking flightBooking = new FlightBooking();
				flightBooking.book((Integer) data[i][2],(Integer) data[i][3]);
				BookingStatus bs = bookingStatusRepository.findById(1).orElse(null);
				flightBooking.setBookingStatus(bs);
				flightBooking = flightBookingRepository.save(flightBooking);

				// Add Depart's Flight and Return's Flight to TableLink
				Flight departFlight = flightRepository.findById(Long.parseLong(data[i][0].toString())).orElse(null);
				Flight returnFlight = flightRepository.findById(Long.parseLong(data[i][1].toString())).orElse(null);

				FlightBookingLink flightBookingLink= new FlightBookingLink();
				flightBookingLink.setFlight(departFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setDepartFlight(true);
				flightBookingLinkRepository.save(flightBookingLink);

				flightBookingLink = new FlightBookingLink();
				flightBookingLink.setFlight(returnFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setDepartFlight(false);
				flightBookingLinkRepository.save(flightBookingLink);

				System.out.println("");
				System.out.println("------------Add FlightBooking--------------");
				System.out.println(flightBooking);
				System.out.println("-------------------------------------------");
			}
		};
	}

	// Fix the CORS errors
	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
