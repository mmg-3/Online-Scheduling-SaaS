import React, { Component } from 'react'
import './CSS/homeStyle.css'
import logo from './CSS/logo.png'
import axios from 'axios'
import Header from './Layout/Header'

class WorkerHome extends Component {

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
                        <a className="btn btn-info btn-block" href="WorkerProfile">View Details</a>
                        <a className="btn btn-info btn-block" href="WorkerBookings">View My Schedule</a>
                    </div>
                </header>
            </body>
        </div>

        )
    }
}
export default WorkerHome
