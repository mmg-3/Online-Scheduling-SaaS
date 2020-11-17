package rmit.com.sept.sept.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import rmit.com.sept.sept.Booking;
import rmit.com.sept.sept.Company;
import rmit.com.sept.sept.SeptApplication;
import rmit.com.sept.sept.repository.BookingRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
    JdbcTemplate template;
	
	public SeptApplication main;
	
	@Override
	public void createBooking(Booking booking) {

		bookingRepository.save(booking);
	}

	@Override
	public List<Company> findAllCompany(String service_name) {
		System.out.println(service_name);
		String sql = "select * from company where service_name = '" + service_name + "'";
        RowMapper<Company> rm = new RowMapper<Company>() {
            @Override
            public Company mapRow(ResultSet resultSet, int i) throws SQLException {
                Company company = new Company(resultSet.getInt("company_id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("service_name"));
               
                return company;
            }
        };

        return template.query(sql, rm);
	}

	@Override
	public List<Booking> getAllBookings() {
		String sql = "select * from booking" ;
        RowMapper<Booking> rm = new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
                Booking booking = new Booking(
                		resultSet.getInt("user_id"),
                		resultSet.getInt("booking_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("time"),
                        resultSet.getString("servicename"),
                        resultSet.getString("worker_name"));
               
                return booking;
            }
        };

        return template.query(sql, rm);
	}

	@Override
	public void deleteBooking(int id) {

		List<Booking> b = getAllBookings();
		b.removeIf(t -> t.getBookingId() == (id));
	}

	@Override
	public boolean isBookingPresent(int id) {
		// TODO Auto-generated method stub
		if(bookingRepository.existsById(id)){
			return true;
		}		//return false;
		return false;
	}

	@Override
	public BookingRepository getBookingRepository() {
		return this.bookingRepository;
	}

	@Override
	public List<Booking> getUserBooking(int userID) {
		String sql = "select * from booking where booking.user_id  = '" + userID + "'" ;
        RowMapper<Booking> rm = new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
                Booking booking = new Booking(
                		resultSet.getInt("user_id"),
                		resultSet.getInt("booking_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("time"),
                        resultSet.getString("servicename"),
                        resultSet.getString("worker_name"));
               
                return booking;
            }
        };

        return template.query(sql, rm);
	}

	@Override
	public List<Booking> getPastBooking(int userID) {
		String sql = "select * from booking where booking.date < CURRENT_DATE() AND booking.user_id  = '" + userID + "'" ;
        RowMapper<Booking> rm = new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
                Booking booking = new Booking(
                		resultSet.getInt("user_id"),
                		resultSet.getInt("booking_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("time"),
                        resultSet.getString("servicename"),
                        resultSet.getString("worker_name"));
               
                return booking;
            }
        };

        return template.query(sql, rm);
	}
	
	@Override
	public List<Booking> getWorkerBooking(String userID) {
		String sql = "select * from booking where booking.worker_name  = '" + userID + "'" ;
        RowMapper<Booking> rm = new RowMapper<Booking>() {
            @Override
            public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
                Booking booking = new Booking(
                		resultSet.getInt("user_id"),
                		resultSet.getInt("booking_id"),
                        resultSet.getDate("date"),
                        resultSet.getString("time"),
                        resultSet.getString("servicename"),
                        resultSet.getString("worker_name"));
               
                return booking;
            }
        };

        return template.query(sql, rm);
	}
}
