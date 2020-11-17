import React,{Component,useEffect,useState} from 'react';
import './CSS/global.css'
import axios from 'axios';
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'
import AdminHeader from './Layout/AdminHeader'

class AdminCreateBooking extends Component {
    
    state = {
            serviceName: "",
            workerName: "",
            date:"",
            time:""
        }

    servicehandler = (event) => {
        this.setState({
            serviceName: event.target.value
        })
    }
    workerhandler = (event) => {
        this.setState({
            workerName: event.target.value
        })
    }
    datehandler = (event) => {
        this.setState({
            date: event.target.value
        })
    }
    timehandler = (event) => {
        this.setState({
            time: event.target.value
        })
    }

    handleSubmit = e => {
        e.preventDefault();
        let information
        const bookingDetails = {
            serviceName: this.state.serviceName,
            workerName: this.state.workerName,
            date: this.state.date,
            time: this.state.time,
        };
        axios
        .post("http://localhost:8080/createBooking", bookingDetails)
        .then(res => console.log(res))
        .catch(err => console.log(err));
        this.props.history.push({
        pathname: '/AdminHome',
    });
        

    }

    render() {
        return (
            <div>
            <AdminHeader/>
            <div className="container-fluid">
                <div className="row">
                    <div className="content-box col-md-8 m-auto">
                        <h5 className="pageTitle display-4 text-center">Make a New Booking</h5>
                        <hr />

                <form className="booking-form" onSubmit={this.handleSubmit}>
                  
                <h6>Service</h6> 
                    <div className="form-group">
                        <input type="text" className="form-control form-control-lg "
                        value={this.state.serviceName} 
                        onChange={this.servicehandler} 
                        placeholder="Service" 
                        required />
                    </div>

                    <h6>Worker</h6> 
                    <div className="form-group">
                        <input type="text"  className="form-control form-control-lg "
                        value={this.state.workerName} 
                        onChange={this.workerhandler} 
                        placeholder="Worker" 
                        required />
                    </div>

                    <h6>Date</h6>
                    <div className="form-group">
                        <input type="date"  className="form-control form-control-lg "
                        value={this.state.date} 
                        onChange={this.datehandler} 
                        placeholder="Date" 
                        required />
                    </div>

                    <h6>Time</h6>
                    <div className="form-group"> 
                        <input type="time"  className="form-control form-control-lg "
                        value={this.state.time} 
                        onChange={this.timehandler} 
                        placeholder="Time" 
                        required />
                    </div>
                    <input className="btn btn-success btn-block mt-4" type="submit" value="Create Booking" /> 
                    <Link to="/AdminHome" className="btn btn-danger btn-block mt-4">Cancel</Link>
                    </form>        
                </div>
            </div>
        </div>
        </div>
        )
    }
}
export default AdminCreateBooking;