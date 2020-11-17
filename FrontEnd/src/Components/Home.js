import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import "./CSS/global.css"

export default class Home extends Component {
    render() {
        return (
            <div className="Persons">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Bookings</h1>
                        <ul>
                            <a button type="button" class="btn btn-primary" href="/ViewBooking">View All Bookings</a>
                            <br />
                            <br />
                            <a button type="button" class="btn btn-primary" href="/CreateBooking">Make a Booking</a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        )
    }
}
