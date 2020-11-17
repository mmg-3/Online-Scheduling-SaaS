import React, { Component } from 'react'
import axios from "axios";
import Header from './Layout/Header';
import './CSS/Profile.css'
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'

export default class EditProfile extends Component {

    state = {
        user_id: "",
        company_id: "",
        company_name:"",
        email: "",
        is_company: "",
        is_worker: "0",
        lastname: "",
        firstname: "",
        number:"",
        password: "",
        service_name:"",
        status: "",
        user_type: "",
        worker_id:""
    };
    
    componentDidMount(){
        
        axios.get('http://localhost:8080/getAll')
        .then(response => {
            this.setState({user_id: response.data.user_id})
            this.setState({company_id: response.data.company_id})
            this.setState({company_name: response.data.company_name})
            this.setState({email: response.data.email})
            this.setState({is_company: response.data.is_company})
            this.setState({is_worker: response.data.is_worker})
            this.setState({lastname: response.data.lastname})
            this.setState({firstname: response.data.firstname})
            this.setState({password: response.data.password})
            this.setState({number: response.data.number})
            this.setState({service_name: response.data.service_name})
            this.setState({status: response.data.status})
            this.setState({user_type: response.data.user_type})
            this.setState({worker_id: response.data.worker_id})

            console.log("res data");
            console.log(this.state);
        });
    }
    companyhandler = (event) => {
        this.setState({
            company: event.target.value
        });
    };
    emailhandler = (event) => {
        this.setState({
            email: event.target.value
        });
    };
    lastnamehandler = (event) => {
        this.setState({
            lastName: event.target.value
        });
    };
    firstnamehandler = (event) => {
        this.setState({
            firstname: event.target.value
        });
    };
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        });
    };


    handleSubmit = e => {
        e.preventDefault();
        const user = {
            company_id: this.state.company_id,
            company_name: this.state.company_id,
            email: this.state.email,
            is_company: this.state.is_company,
            is_worker: this.state.is_worker,
            lastName: this.state.lastname,
            name: this.state.firstname,
            password: this.state.password,
            number: this.state.number,
            service_name: this.state.service_name,
            status: this.state.status,
            user_type: this.state.user_type,
            worker_id: this.state.worker_id
        };


        console.log("state prop");
        console.log(this.state.user_id)

        const id = this.state.user_id;
       
        const url = 'http://localhost:8080/editUser/'
        axios
            .put(url+id, user)
            .then(res => {
                console.log(res)
            })
            .catch(err => console.log(err));

        console.log(user)
    }

    


    render() {
        return (
            <div>
            <Header/>
            <div className="container-fluid">
            <form onSubmit={this.handleSubmit}>
                 <p>Email</p> <input type="text" id="email" value={this.state.email} onChange={this.emailhandler} placeholder="Email" ></input>
                 <p>First Name</p> <input type="text" value={this.state.firstname} onChange={this.firstnamehandler} placeholder="First Name" ></input>
                 <p>Last Name</p> <input type="text" value={this.state.lastname} onChange={this.lastnamehandler} placeholder="Last Name"></input>
                 <p>Pasword</p> <input type="password" value={this.state.password} onChange={this.passwordhandler} placeholder="Password" ></input>
                 <br/>
                 <input type="submit" value="Update" class="btn btn-info mt-4"/>
            </form>
            <a href="/Profile"> Back to Profile</a>
 
            </div>
            </div>
        )
    }
}
