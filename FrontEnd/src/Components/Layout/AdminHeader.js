import React, { Component } from 'react'
import logo from '../Images/logo.png'
import axios from 'axios';

 class AdminHeader extends Component {
    handleSubmit = e => {
        e.preventDefault();
        axios
        .get("http://localhost:8080/logoutUser")
        .then(res => console.log(res))
        .catch(err => console.log(err));
        this.props.history.push({
        pathname: '/Login',
    });
}
    render() {
        return (
        <div>
            <nav className="navbar navbar-expand-sm navbar-dark bg-dark mb-4">
                <div className="container">
                    <a className="navbar-brand" href="/AdminHome"><img className="logo" src={logo} alt=""/></a>
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                            <span className="navbar-toggler-icon" />
                        </button>
                    <div className="collapse navbar-collapse" id="mobile-nav">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <a className="nav-link" href="/AdminHome">Home</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/AdminCreateBooking">Make a Booking</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/Bookings">Bookings</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="/AdminProfile">Profile</a>
                            </li>
                        </ul>
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <a type="submit" className="nav-link btn-danger" href="Login">Logout</a>
                            </li> 
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        )
    }
}
export default AdminHeader;