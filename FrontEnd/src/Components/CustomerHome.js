import React, { Component } from 'react'
import './CSS/homeStyle.css'
import logo from './CSS/logo.png'
import axios from 'axios'
import Header from './Layout/Header'

class CustomerHome extends Component {

    handleSubmit = e => {
        e.preventDefault();
        axios
        .get("http://localhost:8080/logout")
        .then(res => console.log(res))
        .catch(err => console.log(err));
        this.props.history.push({
        pathname: '/login',
    });
        

    }
        render() {
        return (
            
        <div className="Home">
        <Header/>
            <head>                
                <title>View Bookings</title>
            </head> 
            <body>
                <header>
                    <div className="container-fluid col-md-8">
                        <a className="btn btn-info btn-block" href="CreateBooking">Create a Booking</a>
                        <a className="btn btn-info btn-block" href="Profile">View Details</a>
                        <a className="btn btn-info btn-block" href="/UserBookings">View Bookings</a>
                        <a className="btn btn-info btn-block" href="/PastBookings">View Past Bookings</a>
                    </div>
                </header>
            </body>
        </div>

        )
    }
}
export default CustomerHome
