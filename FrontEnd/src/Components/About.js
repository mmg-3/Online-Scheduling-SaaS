import React, { Component } from 'react'
import './CSS/global.css'
import logo from './Images/logo.png'
import Header from './Layout/Header'

class About extends Component {
    render() {
    return (
        <div>
        <Header/>
        <div className="container-fluid">
        <div className="row">
            <div className="content-box col-md-8 m-auto">
                <h5 className="pageTitle display-4 text-center">About AGME</h5>
                    <hr />
        </div>
        </div>
        </div>
        </div>
        )
    }
}
export default About
