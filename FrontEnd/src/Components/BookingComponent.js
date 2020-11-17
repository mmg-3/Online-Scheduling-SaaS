import React from 'react';
import BookingService from './BookingService';

class BookingComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            bookings: []
        }
    }

    componentDidMount() {
        BookingService.booking().then((response) => {
            this.setState({ bookings: response.data })
        });
    }

    
    render() {
        return (
            <div>
                <h1 className="text-center"> Users List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>

                            <td> User Id</td>
                            <td> User First Name</td>
                            <td> User Last Name</td>
                            <td> User Email Id</td>
                        </tr>

                    </thead>
                    <tbody>
                        {/* {
                            this.state.bookings.map(
                                booking =>
                                   <tr key={booking.booking_id}>
                                    <td> {booking.date}</td>
                                    <td> {booking.time}</td>
                                    <td> {booking.serviceName}</td>
                                    <td> {booking.workerName}</td>
                                    </tr>
                                
                            )
                        } */}

                    </tbody>
                </table>

            </div>

        )
    }
}

export default BookingComponent