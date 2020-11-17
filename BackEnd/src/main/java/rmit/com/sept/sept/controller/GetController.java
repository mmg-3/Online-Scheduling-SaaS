package rmit.com.sept.sept.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rmit.com.sept.sept.Booking;
import rmit.com.sept.sept.CustomLoginSuccessHandler;
import rmit.com.sept.sept.User;
import rmit.com.sept.sept.repository.BookingRepository;
import rmit.com.sept.sept.repository.RoleRepository;
import rmit.com.sept.sept.repository.UserRepository;
import rmit.com.sept.sept.repository.WorkerRepository;
import rmit.com.sept.sept.service.BookingService;
import rmit.com.sept.sept.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GetController {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final BCryptPasswordEncoder encoder;

	private final UserService userService;

	private final BookingRepository bookingRepository;
	
	private final WorkerRepository workerRepository;

	private int userID;
	
	private String workerName = "";

	private final BookingService bookingService;

	private final CustomLoginSuccessHandler custom;


	private final PostController postController;


	
	public GetController(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder encoder, UserService userService, CustomLoginSuccessHandler custom,
			BookingRepository bookingRepository, BookingService bookingService, WorkerRepository workerRepository, PostController postController) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.userService = userService;
		this.custom = custom;
		this.bookingRepository = bookingRepository;
		this.bookingService = bookingService;
		this.workerRepository=workerRepository;
		this.postController=postController;
	}
	
	
	
	// returns a json containing information of the bookings
	@GetMapping("/bookings")
	public String booking() {
		List<Booking> bookingList = bookingService.getAllBookings();
		String jsonString = "{\"data\":[";

		for (int i = 0; i < bookingList.size(); i++) {
			jsonString += "{\"bookingID\":\"" + bookingList.get(i).getBookingId() + "\",\"serviceName\":\""
					+ bookingList.get(i).getServiceName() + "\",\"workerName\":\"" + bookingList.get(i).getWorkerName()
					+ "\",\"date\":\"" + bookingList.get(i).getDate() + "\",\"time\":\"" + bookingList.get(i).getTime();

			if (i != bookingList.size() - 1) {
				jsonString += "\"},";
			}
		}

		jsonString += "\"}]}";
		return jsonString;
	}

	// logs user out from the system
	@GetMapping("/logoutUser")
	public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		//userID = 0;
		postController.setPostUserId(0);
		return "redirect:/login?logout";
	}

	// returns the the type of user logging in for authentication
	

	// returns json of email, first name and last name
	@GetMapping("/profile")
	public String profile() {
		System.out.println(userID);
		List<User> userList = userService.getUserDetails(postController.getPostUserID());
		String jsonString = "{\"email\":\"" + userList.get(0).getEmail() + "\"," + " \"firstName\":\""
				+ userList.get(0).getName() + "\"," + " \"lastName\":\"" + userList.get(0).getLastName() + "\"}";

		return jsonString;
	}

	
	
	
	
	@GetMapping("/getUserBooking")
	public String getUserBooking() {
		List<Booking> userBookings = bookingService.getUserBooking(postController.getPostUserID());
		String jsonString = "{\"data\":[";

		for (int i = 0; i < userBookings.size(); i++) {
			jsonString += "{\"bookingID\":\"" + userBookings.get(i).getBookingId() + "\",\"serviceName\":\""
					+ userBookings.get(i).getServiceName() + "\",\"workerName\":\"" + userBookings.get(i).getWorkerName()
					+ "\",\"date\":\"" + userBookings.get(i).getDate() + "\",\"time\":\"" + userBookings.get(i).getTime();

			if (i != userBookings.size() - 1) {
				jsonString += "\"},";
			}
		}

		jsonString += "\"}]}";
		return jsonString;
	}
	
	@GetMapping("/getPastBooking")
	public String getPastBooking() {
		List<Booking> userBookings = bookingService.getPastBooking(postController.getPostUserID());
		String jsonString = "{\"data\":[";

		for (int i = 0; i < userBookings.size(); i++) {
			jsonString += "{\"bookingID\":\"" + userBookings.get(i).getBookingId() + "\",\"serviceName\":\""
					+ userBookings.get(i).getServiceName() + "\",\"workerName\":\"" + userBookings.get(i).getWorkerName()
					+ "\",\"date\":\"" + userBookings.get(i).getDate() + "\",\"time\":\"" + userBookings.get(i).getTime();

			if (i != userBookings.size() - 1) {
				jsonString += "\"},";
			}
		}

		jsonString += "\"}]}";
		return jsonString;
	}
	
	
	

	
	

	@GetMapping("/getWorkerBooking")
	public String getWorkerBooking() {
		System.out.println(postController.getWorkerPostName());

		List<Booking> userBookings = bookingService.getWorkerBooking(postController.getWorkerPostName());
		System.out.println(userBookings.size());
		String jsonString = "{\"data\":[";

		for (int i = 0; i < userBookings.size(); i++) {
			jsonString += "{\"bookingID\":\"" + userBookings.get(i).getBookingId() + "\",\"serviceName\":\""
					+ userBookings.get(i).getServiceName() + "\",\"workerName\":\"" + userBookings.get(i).getWorkerName()
					+ "\",\"date\":\"" + userBookings.get(i).getDate() + "\",\"time\":\"" + userBookings.get(i).getTime();

			if (i != userBookings.size() - 1) {
				jsonString += "\"},";
			}
		}

		jsonString += "\"}]}";
		return jsonString;
	}

}