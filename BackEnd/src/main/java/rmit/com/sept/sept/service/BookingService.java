package rmit.com.sept.sept.service;

import rmit.com.sept.sept.Booking;
import rmit.com.sept.sept.Company;
import rmit.com.sept.sept.repository.BookingRepository;

import java.util.List;

public interface BookingService {
	public void createBooking(Booking booking);
	
	public List<Company> findAllCompany(String service_name);
	
	public List<Booking> getAllBookings();

	public boolean isBookingPresent(int id);

	public BookingRepository getBookingRepository();

	public void deleteBooking(int id);
	
	public List<Booking> getUserBooking(int userID);
	
	public List<Booking> getPastBooking(int userID);
	
	public List<Booking> getWorkerBooking(String workerName);

	
}
