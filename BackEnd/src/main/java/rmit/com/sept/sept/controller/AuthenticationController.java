package rmit.com.sept.sept.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import rmit.com.sept.sept.Booking;
import rmit.com.sept.sept.CustomLoginSuccessHandler;
import rmit.com.sept.sept.Role;
import rmit.com.sept.sept.User;
import rmit.com.sept.sept.Worker;
import rmit.com.sept.sept.repository.BookingRepository;
import rmit.com.sept.sept.repository.RoleRepository;
import rmit.com.sept.sept.repository.UserRepository;
import rmit.com.sept.sept.repository.WorkerRepository;
import rmit.com.sept.sept.service.BookingService;
import rmit.com.sept.sept.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthenticationController {

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


	
	public AuthenticationController(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder encoder, UserService userService, CustomLoginSuccessHandler custom,
			BookingRepository bookingRepository, BookingService bookingService, WorkerRepository workerRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.userService = userService;
		this.custom = custom;
		this.bookingRepository = bookingRepository;
		this.bookingService = bookingService;
		this.workerRepository=workerRepository;
	}
	
	@PostMapping("/createWorker")
	public Worker registerWorker(@RequestBody Worker newWorker) {
		newWorker.setPassword(encoder.encode(newWorker.getPassword()));
		return workerRepository.save(newWorker);

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
		userID = 0;
		return "redirect:/login?logout";
	}

	// returns the the type of user logging in for authentication
	@PostMapping("/loginUser")
	public String login(@RequestBody User newUser) {
	
		int id = userService.findIdLogin(newUser.getEmail());
		userID = id;
		String userType = userService.findUserType(id);
		System.out.println(userID);
		System.out.println(userType);
		if(userType.equals("WORKER_USER")) {
			List<User> userList = userService.getUserDetails(userID);
			workerName = userList.get(0).getName();
			System.out.println(workerName);
		}
		String jsonString = "{\"email\":\"" + newUser.getEmail() + "\", \"userType\":\"" + userType + "\"}";

		return jsonString;

	}

	// returns json of email, first name and last name
	@GetMapping("/profile")
	public String profile() {
		List<User> userList = userService.getUserDetails(userID);
		String jsonString = "{\"email\":\"" + userList.get(0).getEmail() + "\"," + " \"firstName\":\""
				+ userList.get(0).getName() + "\"," + " \"lastName\":\"" + userList.get(0).getLastName() + "\"}";

		return jsonString;
	}
	
	@GetMapping("/acceptWorker")
	public void acceptWorker() {
		
	}
	
	// Registers a user
	@PostMapping("/createUser")
	public User registerUser(@RequestBody User newUser) {
		List<User> userList = userService.getRegisteredCompanyID();
		List<User> workerList = userService.getRegisteredWorkerID();
		
		int registeredCompanyID = userList.get(userList.size()-1).company_id; 
		
		int registeredWorkerID = workerList.get(workerList.size()-1).worker_id;
			
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		newUser.setStatus("VERIFIED");

		if (newUser.getCompanyName() == null) {
			newUser.setCompanyID(registeredCompanyID);
			newUser.setWorkerID(registeredWorkerID);

			Role userRole = roleRepository.findByRole("SITE_USER");
			newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		else if(newUser.getServiceName() == null) {
			newUser.setCompanyID(registeredCompanyID);
			newUser.setWorkerID(registeredWorkerID+1);

			newUser.setWorker(true);
			Role userRole = roleRepository.findByRole("WORKER_USER");
			newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		}
		else {
			newUser.setCompanyID(registeredCompanyID+1);
			newUser.setWorkerID(registeredWorkerID);

			newUser.setIsCompany(true);
			Role userRole = roleRepository.findByRole("ADMIN_USER");
			newUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
	
		
		return userRepository.save(newUser);

	}
	
	// Creates a Booking For the User 
	@PostMapping("/createBooking")
	public Booking createBooking(@RequestBody Booking newBooking) {
		newBooking.setUserId(userID);
		return bookingRepository.save(newBooking);
	}
	
	@GetMapping("/getUserBooking")
	public String getUserBooking() {
		List<Booking> userBookings = bookingService.getUserBooking(userID);
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
		List<Booking> userBookings = bookingService.getPastBooking(userID);
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
	
	@DeleteMapping("/users/{id}")
	public void deleteStudent(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/editUser/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody User user, @PathVariable int id) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Optional<User> studentOptional = userRepository.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteBooking/{id}")
	public void deleteBoooking(@PathVariable int id) {
		bookingRepository.deleteById(id);
	}

	@GetMapping("/getWorkerBooking")
	public String getWorkerBooking() {
		List<Booking> userBookings = bookingService.getWorkerBooking(workerName);
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
