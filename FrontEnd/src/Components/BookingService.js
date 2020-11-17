import axios from 'axios'

const booking_url = 'http://localhost:8080/bookings';

class BookingService {

    booking(){
        return axios.get(booking_url);
    }
}

export default new BookingService();