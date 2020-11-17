import React, { Component } from 'react'
import loginImg from '../Components/avatar.png'
import './CSS/global.css'
import axios from "axios";
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'


class Register extends Component {

    state = {
        email: "",
        name: "",
        lastName: "",
        password: "",
        companyName: "",
        serviceName: "",
        number: "",
        isRegistered: false,
        show: false
    };

    emailhandler = (event) => {
        this.setState({
            email: event.target.value
        });
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
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        });
    };

    companyNameHandler = (event) => {
        this.setState({
            companyName: event.target.value
        });
    };

    serviceNameHandler = (event) => {
        this.setState({
            serviceName: event.target.value
        });
    };

    mobileHandler = (event) => {
        this.setState({
            number: event.target.value
        });
    };

    handleSubmit = e => {
        e.preventDefault();
        const customer = {
            name: this.state.name,
            lastName: this.state.lastName,
            email: this.state.email,
            password: this.state.password,
        };
        
        const admin = {
            name: this.state.name,
            lastName: this.state.lastName,
            email: this.state.email,
            password: this.state.password,
            serviceName: this.state.serviceName,
            companyName: this.state.companyName,
            number: this.state.number,
        };

        if(this.state.companyName == ""){
        axios
            .post("http://localhost:8080/createUser", customer)
            .then(res => console.log(res))
            .catch(err => console.log(err));
        this.props.history.push({
            pathname: '/login',
        });
    }
    else {
        axios
            .post("http://localhost:8080/createUser", admin)
            .then(res => console.log(res))
            .catch(err => console.log(err));
        this.props.history.push({
            pathname: '/login',
        });
    }
    }

    handleField = (event) => {
        this.setState({
            show: this.state.show = false
        });
    };
        
          operation(){
              this.setState({
                  show:!this.state.show
              })
          }

        operationTrue(){
            this.setState({
                show:true
            })
          }

    render() {

        
        return (
            <div className="registerBox col-md-4 align-items-center bg-dark">
             <div className="container-fluid">
                <img src={loginImg} class="avatar"></img>
                <form onSubmit={this.handleSubmit}>

                    <h1 className="form-title">Create an account</h1>

                    
                    <button className="btn btn-primary btn-block mt-2" onClick={()=>this.operation()} >Register as a company</button><br />

                    <p className="form-text">First name</p>
                    <input type="text" value={this.state.name}
                        onChange={this.firsthandler}
                        placeholder="First name" required />

                    <p className="form-text">Last name</p>
                    <input type="text" value={this.state.lastName}
                        onChange={this.lasthandler} placeholder="Last name" required /><br />

                    <p className="form-text">Email</p> <input type="text"
                        value={this.state.email}
                        onChange={this.emailhandler}
                        placeholder="Email" required />

                    <p className="form-text">Password</p>
                    <input type="password"
                        value={this.state.password}
                        onChange={this.passwordhandler}
                        placeholder="Password" required />

                    {this.state.show?
                    <div className="ifYes" >
                        <p className="form-text">Company name</p>
                        <input type="text"
                            value={this.state.companyName}
                            onChange={this.companyNameHandler}
                            placeholder="Company name"
                            class="form-control" />

                    <p className="form-text">Service Name</p>
                        <input type="text"
                            value={this.state.serviceName}
                            onChange={this.serviceNameHandler}
                            placeholder="Service Name"
                            class="form-control" />

                        <p className="form-text">Mobile number</p>
                        <input type="text"
                            value={this.state.number}
                            onChange={this.mobileHandler}
                            placeholder="Number"
                            class="form-control" />
               
                    </div>
                    :null
                    }
                    {/* <p>Do You Want To Register as a company</p><br /> */}
                    
                    <br />
                    <input className="btn btn-success btn-block mt-2" type="submit" value="Register Account" />
                    <br />    
                    <div className="row">
                            <div className="col">
                                <Link to="/Login" class="btn btn-block btn-info" role="button">Login</Link>
                            </div>
                        </div>
                </form>

            </div>
            </div>
        );
    }
}

export default Register;


