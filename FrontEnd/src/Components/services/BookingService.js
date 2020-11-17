import axios from 'axios'

const BOOKING_REST_API_URL = 'http://localhost:8080/createBooking';

class BookingService {

    getBookings(){
       return axios.get(BOOKING_REST_API_URL);
    }

    createBooking(booking){
        return axios.post(BOOKING_REST_API_URL, booking);

    }
}

export default new BookingService();