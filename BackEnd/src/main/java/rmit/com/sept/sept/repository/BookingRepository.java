package rmit.com.sept.sept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rmit.com.sept.sept.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	
}