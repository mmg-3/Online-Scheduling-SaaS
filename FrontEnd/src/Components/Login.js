import React,{Component,useEffect,useState} from 'react';
import './CSS/style.css'
import loginImg from '../Components/avatar.png'
import axios from 'axios';
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'

class Login extends Component {
    
    state = {
            email: "",
            password: "",
            userType:''
        }

    emailhandler = (event) => {
        this.setState({
            email: event.target.value
        })
    }
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        })
    }

    handleSubmit = e => {
        e.preventDefault();
        let information
        const customer = {
            email: this.state.email,
            password: this.state.password,
           
        };
        axios.post('http://localhost:8080/loginUser',customer)
        .then(res=>{
            console.log('Response from main API: ',res)
            console.log('Home Data: ',res.data.userType)
            information=res.data;
             this.state.userType=information.userType;
            if(information.userType == 'ADMIN_USER'){
                this.props.history.push({
                    pathname: '/AdminHome',
                });
            }
            else if(information.userType == 'SITE_USER'){
                this.props.history.push({
                    pathname: '/CustomerHome',
                });
            }
            else if(information.userType == 'WORKER_USER'){
                this.props.history.push({
                    pathname: '/WorkerHome',
                });
            }
            else{
                alert(`INVALID CREDENTIALS`)
            }

            console.log('Colors Data: ',res.data.data)

        })
        .catch(err=>{
            console.log(err);
        })

    }

    render() {
        return (
            <div className="registerBox col-md-4 align-items-center bg-dark">
            <div className="container-fluid">
               <img src={loginImg} class="avatar"></img>
                   <form onSubmit={this.handleSubmit}>
                       <h1>Login</h1> 
                            <p>Email</p> 
                                <input type="text" 
                                value={this.state.email} 
                                onChange={this.emailhandler} 
                                placeholder="Email" 
                                required />
                            <p>Password</p> 
                                <input type="password"
                                value={this.state.password} 
                                onChange={this.passwordhandler} 
                                placeholder="Password" 
                                required />
                                <input className="btn btn-success btn-block mt-4" type="submit" value="Sign in" />
                                <Link to="Register" class="btn btn-info btn-block mt-4" role="button">Register</Link>
            </form>        
            </div>
        </div>
        )
    }
}
export default Login;