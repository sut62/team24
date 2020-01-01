package com.cpe.team24;

import com.cpe.team24.entity.*;
import com.cpe.team24.entity.auth.ERole;
import com.cpe.team24.entity.auth.Role;
import com.cpe.team24.repository.*;
import com.cpe.team24.repository.auth.RoleRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class Team24Application {

	public static void main(String[] args) {
		SpringApplication.run(Team24Application.class, args);
	}

	@Bean
	ApplicationRunner init(FlightBookingRepository flightBookingRepository,
						   BookingStatusRepository bookingStatusRepository, FlightRepository flightRepository,
						   FlightBookingLinkRepository flightBookingLinkRepository, UserRepository userRepository,
						   CityRepository cityRepository,
						   AirportRepository airportRepository,
						   AirplaneRepository airplaneRepository,
						   PasswordEncoder encoder,
						   RoleRepository roleRepository,
						   BaggageAddonRepository baggageaddonRepository,
						   BaggageImageRepository baggageimageRepository,
						   BaggageTypeRepository baggagetypeRepository,
						   FlightBookingTypeRepository flightBookingTypeRepository) {
		return args -> {
			Object[][] data;
			// ------------Add User ROLE ---------
			roleRepository.save(new Role(ERole.ROLE_MEMBER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			// ------------Member-----------------
			data = new Object[][] { { "Alice", "0882223331", "Alick@mail.com", "1234" ,"Alice","admin","AliceFirst","AliceLast"},
					{ "Bob", "0881112223", "Bob@mail.com", "1234" ,"Bob","member","BobFirst","BobLast"} };
			for (int i = 0; i < data.length; i++) {
//				User user = new User();
//				user.setUsername(data[i][4].toString());
//				user.setName(data[i][0].toString());
//				user.setPhone(data[i][1].toString());
//				user.setEmail(data[i][2].toString());
//				user.setPassword(data[i][3].toString());
//				user.setRegDate(new Date());
//				user = userRepository.save(user);
				User user = new User(data[i][4].toString().toLowerCase(),
						data[i][2].toString(),
						encoder.encode(data[i][3].toString()),
						data[i][1].toString(),
						data[i][6].toString(),
						data[i][7].toString()
						);

				Set<Role> roles = new HashSet<>();
				roles.add(roleRepository.findByName(ERole.ROLE_MEMBER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
				user.setRoles(roles);
				user = userRepository.save(user);

				System.out.printf("\n------------Add Member%d--------------\n", i + 1);
				System.out.println(user);
				System.out.println("-------------------------------------------");
			}
			//-------------- Add FlightType --------
			flightBookingTypeRepository.save(new FlightBookingType(EFlightBookingType.DEPART_FLIGHT));
			flightBookingTypeRepository.save(new FlightBookingType(EFlightBookingType.RETURN_FLIGHT));
			// ------------Flight-----------------
			data = new Object[][] {
					// price , depart(days), flight duration(minute)
					// 0 is today , 1 tomorrow , 2 next 2 day
					{ 1900, 0, 30 }, { 1800, 0, 40 }, { 2000, 1, 75 }, { 2400, 1, 55 }, };
			for (int i = 0; i < data.length; i++) {
				Flight flight = new Flight();
				flight.setPrice(Double.parseDouble(data[i][0].toString()));

				// date create
				Date depart = new Date(
						new Date().getTime() + ((1000 * 60 * 60 * 24) * Integer.parseInt(data[i][1].toString())));
				Date arrive = new Date(depart.getTime() + (1000 * 60) * Integer.parseInt(data[i][2].toString()));
				//
				flight.setDepart(depart);
				flight.setArrive(arrive);
				flight = flightRepository.save(flight);
				System.out.printf("\n------------Add Flight%d--------------\n", i + 1);
				System.out.println(flight);
				System.out.println("-------------------------------------------");
			}
			// ------------Booking Status-----------------
			data = new Object[][] { { "ยังไม่ชำระ" }, { "ชำระแล้ว" }, { "เช็คอินแล้ว" } };
			for (int i = 0; i < data.length; i++) {
				BookingStatus bookingStatus = new BookingStatus();
				bookingStatus.setName(data[i][0].toString());
				bookingStatus = bookingStatusRepository.save(bookingStatus);
				System.out.printf("\n------------Add BookingStatus%d--------------\n", i + 1);
				System.out.println(bookingStatus);
				System.out.println("-------------------------------------------");
			}
			// --------------Flight Booking-----------------
			data = new Object[][] {
					// departFlightId,returnFlightId,departSeatId,returnSeatId,MemberId
					{ 2, 1, 1, 1, 1 }, { 1, 2, 2, 2, 1 } };
			for (int i = 0; i < data.length; i++) {
				FlightBooking flightBooking = new FlightBooking();
				flightBooking.book((Integer) data[i][2], (Integer) data[i][3]);
				BookingStatus bs = bookingStatusRepository.findById(1).orElse(null);
				flightBooking.setBookingStatus(bs);
				flightBooking.setUser(userRepository.findById(Long.parseLong(data[i][4].toString())).orElse(null));
				flightBooking = flightBookingRepository.save(flightBooking);

				// Add Depart's Flight and Return's Flight to TableLink
				Flight departFlight = flightRepository.findById(Long.parseLong(data[i][0].toString())).orElse(null);
				Flight returnFlight = flightRepository.findById(Long.parseLong(data[i][1].toString())).orElse(null);

				FlightBookingLink flightBookingLink = new FlightBookingLink();
				flightBookingLink.setFlight(departFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT));
				flightBookingLinkRepository.save(flightBookingLink);

				flightBookingLink = new FlightBookingLink();
				flightBookingLink.setFlight(returnFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT));
				flightBookingLinkRepository.save(flightBookingLink);

				System.out.printf("\n------------Add FlightBooking%d--------------\n", i + 1);
				System.out.println(flightBooking);
				System.out.println("-------------------------------------------");

			}
			;
			// ------------Add Flight Airport ---------
			airportRepository.save(new Airport(FAir.FLIGHT_AIRPORT_TO));
			airportRepository.save(new Airport(FAir.FLIGHT_AIRPORT_FROM));
			// ------------Flight City-----------------
			data = new Object[][] { { "กรุงเทพมหานคร" }, { "เชียงใหม่" }, { "เชียงราย" }, { "ภูเก็ต" }, { "ส่งขลา" } };
			for (int i = 0; i < data.length; i++) {
				City fightCity = new City();
				fightCity.setName(data[i][0].toString());
				fightCity = cityRepository.save(fightCity);
				System.out.printf("\n------------Add Flight City%d--------------\n", i + 1);
				System.out.println(fightCity);
				System.out.println("-------------------------------------------");
			};
			// ------------ Flight Airplane-----------------
			// data = new Object[][] { { "กรุงเทพมหานคร" }, { "เชียงใหม่" }, { "เชียงราย" }, { "ภูเก็ต" }, { "ส่งขลา" } };
			// for (int i = 0; i < data.length; i++) {
			// 	FlightAirplane flightAirplane = new FlightAirplane();
			// 	flightAirplane.setName(data[i][0].toString());
			// 	flightAirplane = flightAirplaneRepository.save(flightAirplane);
			// 	System.out.printf("\n------------Add Flight City%d--------------\n", i + 1);
			// 	System.out.println(flightAirplane);
			// 	System.out.println("-------------------------------------------");
			// }


			Stream.of(
				"ธรรมดา","ที่ต้องดูแลเป็นเศษ","อุปกรณ์การกีฬา"
			).forEach(bagtype-> {
				BaggageType baggagetype = new BaggageType(); 
				baggagetype.setName(bagtype); 
				baggagetypeRepository.save(baggagetype); 
			});


			Object[][] bagimage;
			//Menu Design
			bagimage = new Object[][] {
				{ "AAA", "http://www.thaifoodheritage.com/uploads/recipe_list/gallery/e3de5-jpg.jpeg" },
				{ "BBB", "https://food.mthai.com/app/uploads/2019/02/Fried-Rice-with-Shrimp-Fat.jpg" },
				{ "CCC","https://image.makewebeasy.net/makeweb/0/zs7gnRKvE/TH/%E0%B8%9C%E0%B8%B1%E0%B8%94%E0%B8%81%E0%B8%A3%E0%B8%B0%E0%B9%80%E0%B8%9E%E0%B8%A3%E0%B8%B2%E0%B9%84%E0%B8%81%E0%B9%88.jpg" },
				{ "DDD", "https://food.mthai.com/app/uploads/2017/02/Spicy-fish-2.jpg" },
				{ "EEE", "https://img.kapook.com/u/2015/surauch/cook2/PT1.jpg" }, 
			};
			for (int i = 0; i < bagimage.length; i++) {
				BaggageImage image = new BaggageImage();
				image.setName((String) bagimage[i][0]);
				image.setUrl((String) bagimage[i][1]);
				baggageimageRepository.save(image);
			};

			Object[][] bag = new Object[][] { 
				{ "สมศรี", "Thai", 1,1,1 },
				{ "สมชาย", "Thai", 1,2,3 },
				{ "สมพงษ์", "Thai", 2,3,2 }
			};
			for (int i = 0; i < bag.length; i++) {
				BaggageAddon newBag = new BaggageAddon();
				newBag.setMaxweight((String) bag[i][0]);		
				newBag.setPrice((String) bag[i][1]);
				
				BaggageType btype = baggagetypeRepository.findById((int) bag[i][2]);
				newBag.setAddbaggagetype(btype);

				BaggageImage bimage = baggageimageRepository.findById((int)bag[i][3]);
				newBag.setAddbaggageimage(bimage);
				

				City fightCity = new City();
				fightCity.setName(bag[i][4].toString());	

				
				baggageaddonRepository.save(newBag);
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
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
