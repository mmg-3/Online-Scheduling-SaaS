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
public class DeleteController {

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


	
	public DeleteController(UserRepository userRepository, RoleRepository roleRepository,
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
	

	
	@DeleteMapping("/users/{id}")
	public void deleteStudent(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	

	
	@DeleteMapping("/deleteBooking/{id}")
	public void deleteBoooking(@PathVariable int id) {
		bookingRepository.deleteById(id);
	}

	
}