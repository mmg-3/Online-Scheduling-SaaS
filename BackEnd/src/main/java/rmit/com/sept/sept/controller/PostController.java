package rmit.com.sept.sept.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rmit.com.sept.sept.*;
import rmit.com.sept.sept.repository.BookingRepository;
import rmit.com.sept.sept.repository.RoleRepository;
import rmit.com.sept.sept.repository.UserRepository;
import rmit.com.sept.sept.repository.WorkerRepository;
import rmit.com.sept.sept.service.BookingService;
import rmit.com.sept.sept.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController {

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


	
	public PostController(UserRepository userRepository, RoleRepository roleRepository,
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
		System.out.println(jsonString);
		return jsonString;

	}

	public int getPostUserID(){
		return this.userID;
	}

	public String getWorkerPostName(){
		return this.workerName;

	}


	public void setPostUserId(int userID){
		this.userID = userID;

	}
	@PatchMapping("/acceptWorker/{id}")
	public ResponseEntity<Object> acceptWorker(@RequestBody User user, @PathVariable int id) {
		Optional<User> workerList = userRepository.findById(id);
	
		if (!workerList.isPresent())
			return ResponseEntity.notFound().build();
		
		user.setWorkerID(id);
		userRepository.save(user);
		
		return ResponseEntity.noContent().build();
	}
	
	// Registers a user
	@PostMapping("/createUser")
	public User registerUser(@RequestBody User newUser) {
		List<User> userList = userService.getRegisteredCompanyID();
		List<User> workerList = userService.getRegisteredWorkerID();
		
		int registeredCompanyID =0;
		if(userList.size()>0)
		if(userList.get(userList.size()-1).company_id>0){
			registeredCompanyID = userList.get(userList.size()-1).company_id;
		}

		//registeredCompanyID = userList.get(userList.size()-1).company_id;
		int registeredWorkerID=0;
		if(workerList.size()>0)
		if(workerList.get(workerList.size()-1).worker_id>0)
		 registeredWorkerID = workerList.get(workerList.size()-1).worker_id;
			
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
	

}