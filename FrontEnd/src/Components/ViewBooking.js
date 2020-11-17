import React, { Component } from "react";
import axios from 'axios';
import DataService from './DataService.js';
import { Link } from "react-router-dom";

export default class ViewBooking extends Component {

  state = {
    booking: []

  }
  componentDidMount() {
    axios.get('http://localhost:8080/bookings').then(response => {this.setState({booking: response.data})});
  }

  deleteRow(id,e){
   const url = 'http://localhost:8080/bookings'
   axios.delete(url+id).then(response => {this.setState({booking: response.data})});
   this.props.history.push('/')

  }

  render() {
    return (
      <div>
      <div className="container-fluid">
         <div className="row">
             <div className="col-md-8 m-auto">
                 <h5 className="pageTitle display-4 text-center">Your Upcoming Bookings</h5>
                 <hr />

                <table className="table">
                  <thead>
                  <tr>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Booking Number</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">User ID</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Service</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Worker</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Status</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Date</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Time</th>
                    <th className="bg-light text-dark text-center align-middle" scope="col">Delete</th>
                  </tr>
                  </thead>
                  <tbody>
          {
              this.state.booking.map(
                  booking =>
                      <tr key={booking.booking_id}>
                          <th scope="row">{booking.booking_id}</th>
                          <td>{booking.user_id}</td>
                          <td>{booking.serviceName}</td>
                          <td>{booking.workerName}</td>
                          <td>{booking.status}</td>
                          <td>{booking.date}</td>
                          <td>{booking.time}</td>
                      </tr>
              )
          }
                  </tbody>
                </table>
              <div className="row">
                <a className="btn btn-success btn-block mt-2" href="/CreateBooking">Make a Booking</a>
              </div>
            </div>
          </div>
        </div>
        
        </div>
      
    );
  }
}
