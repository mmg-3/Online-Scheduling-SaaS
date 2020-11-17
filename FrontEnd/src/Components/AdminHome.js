import React, { Component } from 'react'
import './CSS/global.css'
import logo from './CSS/logo.png'
import AdminHeader from './Layout/AdminHeader'

class AdminHome extends Component {

        render() {
        return (
            <div>
            <AdminHeader/>
            <div className="Home">
                <head>                
                    <title>Admin Home</title>
                </head> 
                <body>
                    <header>
                        <div className="container-fluid col-md-8">
                            <a className="btn btn-info btn-block" href="AdminCreateBooking">Create a Booking</a>
                            <a className="btn btn-info btn-block" href="AdminProfile">View Details</a>
                            <a className="btn btn-info btn-block" href="Bookings">View All Bookings</a>
                            <a className="btn btn-info btn-block" href="RegisterWorker">Register Worker</a>
                        </div>
                    </header>
                </body>
            </div>
            </div>
        )
    }
}
export default AdminHome
