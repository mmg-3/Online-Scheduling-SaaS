import React, { Component } from 'react'
import loginImg from '../Components/avatar.png'
import './CSS/global.css'
import axios from "axios";
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'


class RegisterWorker extends Component {

    state = {
        name: "",
        lastName: "",
        company_name: "",
        number: "",
        email: "",
        password: "",
        servicename: "",
    };
    firsthandler = (event) => {
        this.setState({
            name: event.target.value
        });
    };
    lasthandler = (event) => {
        this.setState({
            lastName: event.target.value
        });
    };
    emailhandler = (event) => {
        this.setState({
            email: event.target.value
        });
    };
    workernameHandler = (event) => {
        this.setState({
            worker_name: event.target.value
        });
    };
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        });
    };

    companyNameHandler = (event) => {
        this.setState({
            company_name: event.target.value
        });
    };

    mobileHandler = (event) => {
        this.setState({
            number: event.target.value
        });
    };

    serviceHandler = (event) => {
        this.setState({
            servicename: event.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        const worker = {
            name: this.state.name,
            lastName: this.state.lastName,
            company_name: this.state.company_name,
            number: this.state.number,
            email: this.state.email,
            password: this.state.password,
        };
        axios
            .post("http://localhost:8080/createUser", worker)
            .then(res => console.log(res))
            .catch(err => console.log(err));
        this.props.history.push({
            pathname: '/AdminHome',
        });

  
    }



    render() {

        
        return (
            <div className="registerBox col-md-4 align-items-center bg-dark">
             <div className="container-fluid">
                <img src={loginImg} class="avatar"></img>
                <form onSubmit={this.handleSubmit}>

                    <h1 className="form-title">Register Worker</h1>
                    <p className="form-text">First Name</p>
                    <input type="text" value={this.state.name}
                        onChange={this.firsthandler}
                        placeholder="Name" required />
                    
                    <p className="form-text">Last Name</p>
                    <input type="text" value={this.state.lastName}
                        onChange={this.lasthandler}
                        placeholder="Name" required />

                    <p className="form-text">Email</p> <input type="text"
                        value={this.state.email}
                        onChange={this.emailhandler}
                        placeholder="Email" required />

                    <p className="form-text">Password</p>
                    <input type="password"
                        value={this.state.password}
                        onChange={this.passwordhandler}
                        placeholder="Password" required />

                  
                        <p className="form-text">Company name</p>
                        <input type="text"
                            value={this.state.companyName}
                            onChange={this.companyNameHandler}
                            placeholder="Company name"
                            class="form-control" />

                        <p className="form-text">Mobile number</p>
                        <input type="text"
                            value={this.state.number}
                            onChange={this.mobileHandler}
                            placeholder="Number"
                            class="form-control" />
                                   
                    <br />
                    <input className="btn btn-success btn-block mt-2" type="submit" value="Register Account" />
                    <br />    
                    <div className="row">
                            <div className="col">
                                <Link to="/AdminHome" class="btn btn-block btn-info" role="button">Admin Home</Link>
                            </div>
                        </div>
                </form>

            </div>
            </div>
        );
    }
}

export default RegisterWorker;