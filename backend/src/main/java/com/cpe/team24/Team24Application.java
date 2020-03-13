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

import javax.transaction.Transactional;
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
						   FlightBookingTypeRepository flightBookingTypeRepository,
						   FlightAirportTypeRepository flightAirportTypeRepository,
						   FlightAirportRepository flightAirportRepository,
						   CheckInRepository checkInRepository,
						   CheckInStatusRepository checkInStatusRepository,
						   CheckInTypeRepository checkInTypeRepository,
						   PaymentTypeRepository paymentTypeRepository,
						   PaymentRepository paymentRepository,
						   PaymentWayRepository paymentWayRepository,
						   PromotionRepository promotionRepository,
						   PromotionStatusRepository promotionStatusRepository,
						   PromotionImageRepository promotionImageRepository,
						   PromotionCodeRepository promotionCodeRepository
						   ) {
		return args -> {
			Object[][] data;
			//----------- Add Promotion Status ---------
			promotionStatusRepository.save(new PromotionStatus(EPromotionStatus.AVALIABLE));
			promotionStatusRepository.save(new PromotionStatus(EPromotionStatus.EXPIRED));
			promotionStatusRepository.save(new PromotionStatus(EPromotionStatus.CANCELED));
			//---------- Add Promotion Image ---------
			promotionImageRepository.save(new PromotionImage("Promotion1","https://image.shutterstock.com/image-vector/promotion-rubber-stamp-seal-vector-260nw-1437143363.jpg"));
			promotionImageRepository.save(new PromotionImage("Promotion2","https://www.c3inter.com/images/slideshow/resize-1537237433756.png"));
			promotionImageRepository.save(new PromotionImage("Promotion3","http://blog.sogoodweb.com/upload/510/ukoJ7reQYc.png"));
			//---------- Add Promotion ----------
			PromotionCode p = new PromotionCode();
			p.setCode("1234A");
			p.setDiscount(90);
			p.setExp(new Date(new Date().getTime() + (1000*60*60*24)));
			p = promotionCodeRepository.save(p);
			promotionCodeRepository.save(p);
			
			// ------------Add User ROLE ---------
			roleRepository.save(new Role(ERole.ROLE_MEMBER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			// ------------Member-----------------
			data = new Object[][] { { "Alice", "0882223331", "Alick@mail.com", "1234" ,"Alice","admin","AliceFirst","AliceLast"},
					{ "Bob", "0881112223", "Bob@mail.com", "1234" ,"Bob","member","BobFirst","BobLast"},
					{ "Admin", "0881112223", "Admin@mail.com", "1234" ,"Admin","admin","AdminFirst","AdminLast"} };
			for (int i = 0; i < data.length; i++) {
				User user = new User(data[i][4].toString().toLowerCase(),
						data[i][2].toString(),
						encoder.encode(data[i][3].toString()),
						data[i][1].toString(),
						data[i][6].toString(),
						data[i][7].toString()
						);

				Set<Role> roles = new HashSet<>();
				if(i==0){
					roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role Admin is not found.")));
				}
				if(i==2){
					roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role Admin is not found.")));
				}
				if(i!=2)
					roles.add(roleRepository.findByName(ERole.ROLE_MEMBER).orElseThrow(() -> new RuntimeException("Error: Role Member is not found.")));
				user.setRoles(roles);
				user = userRepository.save(user);

				System.out.printf("\n------------Add Member%d--------------\n", i + 1);
				System.out.println(user);
				System.out.println("-------------------------------------------");
			}
			//-------------- Add FlightBookingType --------
			flightBookingTypeRepository.save(new FlightBookingType(EFlightBookingType.DEPART_FLIGHT));
			flightBookingTypeRepository.save(new FlightBookingType(EFlightBookingType.RETURN_FLIGHT));
			// ------------Add FlightAirportType ---------
			flightAirportTypeRepository.save(new FlightAirportType(EFlightAirportType.ARRIVE_AIRPORT));
			flightAirportTypeRepository.save(new FlightAirportType(EFlightAirportType.DEPART_AIRPORT));
			// ------------ City -----------------
			data = new Object[][] { { "กรุงเทพมหานคร" }, { "ขอนแก่น" }, { "เชียงใหม่" }, { "ตรัง" }, 
					{ "นครพนม" }, { "นครศรีธรรมราช" }, { "นราธิวาส" },{ "บุรีรัมย์" }, { "พิษณุโลก" }, 
					{ "ภูเก็ต" }, { "ร้อยเอ็ด" }, { "ระนอง" }, { "เลย" }, { "สกลนคร" },
					{ "นครพนม" }, { "สุราษธานี" }, { "หาดใหญ่" }, { "อุบลราชธานี" }, { "อุดรธานี" } };
			for (int i = 0; i < data.length; i++) {
				City city = new City();
				city.setName(data[i][0].toString());
				city = cityRepository.save(city);
				System.out.printf("\n------------Add Flight City%d--------------\n", i + 1);
				System.out.println(city);
				System.out.println("-------------------------------------------");
			};
			// ------------ Airplane -----------------
			data = new Object[][] { { "Airbus A320" , "180"}, { "Airbus A320-Neo" , "186"}, { "Airbus For-Test" , "1"} };
			for (int i = 0; i < data.length; i++) {
				Airplane airplane = new Airplane();
				airplane.setName(data[i][0].toString());
				airplane.setSeatAmout(Integer.parseInt(data[i][1].toString()));
				airplane = airplaneRepository.save(airplane);
				System.out.printf("\n------------Add Flight Airplane %d --------------\n", i + 1);
				System.out.println(airplane);
				System.out.println("-------------------------------------------");
			};

			// ------------ Airport -----------------
			data = new Object[][] { { "สุวรรณภูมิ" , 1}, {"ท่าอากาศยานขอนแก่น", 2}, {"ท่าอากาศยานเชียงใหม่", 3}, {"ท่าอากาศยานตรัง", 4},
					{"ท่าอากาศยานนครพนม", 5}, {"ท่าอากาศยานนครศรีธรรมราช", 6}, {"ท่าอากาศยานนราธิวาส", 7}, {"ท่าอากาศยานบุรีรัมย์", 8},
					{"ท่าอากาศยานพิษณุโลก", 9}, {"ท่าอากาศยานภูเก็ต", 10}, {"ท่าอากาศยานร้อยเอ็ด", 11}, {"ท่าอากาศยานระนอง", 12} };
			for (int i = 0; i < data.length; i++) {
				Airport airport = new Airport();
				airport.setName(data[i][0].toString());
				City city = cityRepository.findById((int) data[i][1]);
				airport.setCity(city);
				airport = airportRepository.save(airport);
				System.out.printf("\n------------Add Flight Airplane %d --------------\n", i + 1);
				System.out.println(airport);
				System.out.println("-------------------------------------------");
			};
			// ------------Flight-----------------
			data = new Object[][] {
					// price , depart(days), flight duration(minute) , Airport (Depart), Airpot (Arrive), Airplane
					// 0 is today , 1 tomorrow , 2 next 2 day
					{ 1900, 0, 30 ,1,2,1 }, { 1800, 0, 40 ,1,2,1 }, { 2000, 1, 75 ,2,1,1 }, { 2400, 1, 55,2,1,1 },
					{ 1900, 0, 30 ,1,3,1 }, { 1800, 0, 40 ,1,3,1 }, { 2000, 1, 75 ,3,1,1 }, { 2400, 1, 55,3,1,1 },
					{ 1900, 0, 30 ,1,4,3 }, { 1800, 0, 40 ,1,4,3 }, { 2000, 1, 75 ,4,1,3 }, { 2400, 1, 55,4,1,3 },
			};
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
				flight.setAirplane(airplaneRepository.findById(Long.parseLong(data[i][5].toString())).orElse(null));
				flight = flightRepository.save(flight);

				//FlightAirport depart
				FlightAirport flightAirport = new FlightAirport();
				flightAirport.setAirport(airportRepository.findById(Long.parseLong(data[i][3].toString())).orElse(null));
				flightAirport.setFlightAirportType(flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT));
				flightAirport.setFlight(flightRepository.findById(flight.getId()).orElse(null));
				flightAirportRepository.save(flightAirport);

				//FlightAirport arrive
				flightAirport = new FlightAirport();
				flightAirport.setAirport(airportRepository.findById(Long.parseLong(data[i][4].toString())).orElse(null));
				flightAirport.setFlightAirportType(flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT));
				flightAirport.setFlight(flightRepository.findById(flight.getId()).orElse(null));
				flightAirportRepository.save(flightAirport);

				System.out.printf("\n------------Add Flight%d--------------\n", i + 1);
				System.out.println(flight);
				System.out.println("-------------------------------------------");
			}
			// ------------Booking Status-----------------
			bookingStatusRepository.save(new BookingStatus(EBookingStatus.PENDING));
			bookingStatusRepository.save(new BookingStatus(EBookingStatus.REJECT));
			bookingStatusRepository.save(new BookingStatus(EBookingStatus.SUBMIT));
			// --------------Flight Booking-----------------
			data = new Object[][] {
					// departFlightId,returnFlightId,departSeatId,returnSeatId,MemberId
					{ 3, 1, 0, 0, 1 }, { 1, 3, 0, 0, 1 },{ 1, 3, 0, 0, 2 },{ 3, 1, 0, 0, 1 },
					{ 7, 5, 0, 0, 1 }, { 7, 5, 0, 0, 1 },{ 5, 7, 0, 0, 2 },{ 5, 7, 0, 0, 1 }
				 };
			for (int i = 0; i < data.length; i++) {
				FlightBooking flightBooking = new FlightBooking();
				flightBooking.book((Integer) data[i][2], (Integer) data[i][3]);
				if(i==1){
					flightBooking.setBookId("ABC001");
				}else
				if(i==2){
					flightBooking.setBookId("ABC002");
				}else
				if(i==3){
					flightBooking.setBookId("ABC003");
				}else
				if(i==0){
					flightBooking.setBookId("123456");
				}
				
				flightBooking.setBookingStatus(bookingStatusRepository.findByName(EBookingStatus.PENDING));
				flightBooking.setUser(userRepository.findById(Long.parseLong(data[i][4].toString())).orElse(null));
				flightBooking = flightBookingRepository.save(flightBooking);

				// Add Depart's Flight and Return's Flight to TableLink
				FlightBookingLink flightBookingLink = new FlightBookingLink();
				Flight departFlight = flightRepository.findById(Long.parseLong(data[i][0].toString())).orElse(null);
				flightBookingLink.setFlight(departFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT));
				flightBookingLinkRepository.save(flightBookingLink);

				flightBookingLink = new FlightBookingLink();
				Flight returnFlight = flightRepository.findById(Long.parseLong(data[i][1].toString())).orElse(null);
				flightBookingLink.setFlight(returnFlight);
				flightBookingLink.setFlightBooking(flightBooking);
				flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT));
				flightBookingLinkRepository.save(flightBookingLink);
				
				System.out.printf("\n------------Add FlightBooking%d--------------\n", i + 1);
				System.out.println(flightBooking);
				System.out.println("-------------------------------------------");

			};
			// ------- Payment Type ---------
			String[] types = new String[]{"Over the counter","Internet banking"};
			for(String type : types){
				paymentTypeRepository.save(new PaymentType(type));
			}
			// ------- Payment Way --------
			String[] ways = new String[]{"7-11","Big C","Tesco Lotus","True Money"};
			for(String way : ways){
				paymentWayRepository.save(new PaymentWay(way,paymentTypeRepository.findById(1l).orElse(null)));
			}
			ways = new String[]{"ธนาคารไทยพาณิชย์","ธนาคารทหารไทย","ธนาคารกรุงไทย","ธนาคารกรุงเทพ"};
			for(String way : ways){
				paymentWayRepository.save(new PaymentWay(way,paymentTypeRepository.findById(2l).orElse(null)));
			}


			// ------- Baggage Type --------
			Stream.of(
				"ธรรมดา","ที่ต้องดูแลเป็นพิเศษ","อุปกรณ์การกีฬา"
			).forEach(bagtype-> {
				BaggageType baggagetype = new BaggageType(); 
				baggagetype.setBtypename(bagtype); 
				baggagetypeRepository.save(baggagetype); 
			});


			
		
			Object[][] bagimage = new Object[][] {
				{ "SMALL", "https://image.flaticon.com/icons/svg/1926/1926375.svg" },
				{ "MEDIUM", "https://image.flaticon.com/icons/svg/494/494835.svg" },
				{ "LARGE","https://image.flaticon.com/icons/svg/2118/2118860.svg" },
				{ "VIP", "https://image.flaticon.com/icons/svg/1040/1040624.svg" },
				{ "SPORT", "https://image.flaticon.com/icons/svg/33/33736.svg" }, 
			};
			
			for (int i = 0; i < bagimage.length; i++) {
				BaggageImage image = new BaggageImage();
				image.setName((String) bagimage[i][0]);
				image.setUrl((String) bagimage[i][1]);
				baggageimageRepository.save(image);
			};

			Object[][] bag = new Object[][] { 
				// 0=weight,1=price,2=type, 3=image,4=airport
				{ 5, 50.00, 1,1,1 },
				{ 10, 100.00, 1,2,1 },
				{ 15, 150.00, 1,3,1 },
				{ 5, 200.00, 2,4,1 },
				{ 10, 400.00, 2,4,1 },
				{ 15, 600.00, 2,4,1 },
				{ 5, 300.00, 3,5,1 },
				{ 10, 500.00, 3,5,1 },
				{ 15, 700.00, 3,5,1 },

				{ 5, 100.00, 1,1,2 },
				{ 10, 150.00, 1,2,2 },
				{ 15, 200.00, 1,3,2 },
				{ 5, 260.00, 2,4,2 },
				{ 10, 460.00, 2,4,2 },
				{ 15, 660.00, 2,4,2 },
				{ 5, 380.00, 3,5,2 },
				{ 10, 580.00, 3,5,2 },
				{ 15, 780.00, 3,5,2 },
			};
			for (int i = 0; i < bag.length; i++) {
				BaggageAddon newBag = new BaggageAddon();
				newBag.setMaxWeight((Integer) bag[i][0]);
				newBag.setPrice((Double) bag[i][1]);
				
				BaggageType btype = baggagetypeRepository.findById((int) bag[i][2]);
				newBag.setBaggageType(btype);

				BaggageImage bimage = baggageimageRepository.findById((int) bag[i][3]);
				newBag.setBaggageImage(bimage);

				// Airport bagairport = airportRepository.findById(id)
				Airport bagairport = airportRepository.findById(Long.parseLong(bag[i][4].toString())).orElse(null);
				newBag.setAirport(bagairport);
				

				baggageaddonRepository.save(newBag);
			}

			//-----------------------------------------
			Object[][] checkInStatus = new Object[][] { 
				{ "อนุมัติ"},
				{ "ไม่อนุมัติ"},
				{ "ยกเลิก"},
				{ "ผู้โดยสารขึ้นเครื่องแล้ว"}
			};
			for (int i = 0; i < checkInStatus.length; i++) {
				CheckInStatus newCheckInStatus = new CheckInStatus();
				newCheckInStatus.setName((String) checkInStatus[i][0]);		
				checkInStatusRepository.save(newCheckInStatus);
			}
			Object[][] checkInType = new Object[][] { 
				{ "Webside"},
				{ "WalkIn"},
				{ "Machines"}
			};
			for (int i = 0; i < checkInType.length; i++) {
				CheckInType newCheckInType = new CheckInType();
				newCheckInType.setName((String) checkInType[i][0]);		
				checkInTypeRepository.save(newCheckInType);
			}

			Object[][] checkIn = new Object[][] { 
				{ 2,2,3,"FDSAFQ","bobzaza@gmail.com"},
			};
			for (int i = 0; i < checkIn.length; i++) {
				CheckIn newCheckIn = new CheckIn();
				FlightBooking fBooking = flightBookingRepository.findById(Long.parseLong(checkIn[i][2].toString())).orElse(null);
				CheckInStatus checkStatus = checkInStatusRepository.findById(Long.parseLong(checkIn[i][0].toString())).orElse(null);
				CheckInType checkType = checkInTypeRepository.findById(Long.parseLong(checkIn[i][1].toString())).orElse(null);
				newCheckIn.setFlightBooking(fBooking);
				newCheckIn.setCheckInStatus(checkStatus);
				newCheckIn.setCheckInType(checkType);
				newCheckIn.setBoardingPass((String) checkIn[i][3]);
				newCheckIn.setEmail((String) checkIn[i][4]);
				newCheckIn.setDate(new Date());
				checkInRepository.save(newCheckIn);
			}

		};
	}

	// Fix the CORS errors
	@Bean
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
