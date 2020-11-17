import React,{Component,useEffect,useState} from 'react';
import axios from 'axios';
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'
import Header from './Layout/Header';
const WorkerProfile=()=>{
    const [Data,setData]=useState({
        email:'',
        firstName:'',
        lastName:''
    });
    
    useEffect(()=>{
        axios.get('http://localhost:8080/profile')
            .then(res=>{
                let information=res.data;
                setData({email:information.email,firstName:information.firstName,lastName:information.lastName})
            })
            .catch(err=>{
                console.log(err);
            })
    },[])
    return(
        <div>
        <Header/>
        <div className="container-fluid">
                <div className="row">
                    <div className="content-box col-md-8 m-auto">
                        <h5 className="pageTitle display-4 text-center">Your Profile</h5>
                        <hr />
                            <h4>Your name:</h4><p> {Data.firstName} {Data.lastName}</p>
                            <h4>Email:</h4><p> {Data.email}</p>
                            <Link to="WorkerEditProfile" class="btn btn-info mt-4" role="button">Edit Profile</Link>
                            <br/>
                            <Link to="WorkerHome" class="btn btn-info mt-4" role="button">Worker Home</Link>
                </div>
           </div>
        </div>
        </div>
    )
}

export default WorkerProfile;
