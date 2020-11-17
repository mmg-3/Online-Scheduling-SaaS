import React, { Component } from 'react'
import Persons from './Persons/Persons'
import "bootstrap/dist/css/bootstrap.min.css"
class Dashboard extends Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                    <h5 className="pageTitle display-4 text-center">Bookings</h5>
                    <hr />
                        <ul>
                            <a button type="button" class="btn btn-primary" href="/Bookings">View All Bookings</a>
                            <br />
                            <br />
                            <a button type="button" class="btn btn-primary" href="/CreateBooking">Make a Booking</a>
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}
export default Dashboard;
