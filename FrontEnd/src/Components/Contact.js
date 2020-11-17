import React, { Component } from 'react'
import './CSS/global.css'
import Header from './Layout/Header'

class Contact extends Component {
    render() {
    return (
        <div>
        <Header/>
        <div className="container-fluid">
        <div className="row">
            <div className="content-box col-md-8 m-auto">
                <h5 className="pageTitle display-4 text-center">Contact Us</h5>
                    <hr />
                <h3>This page should be populated with contact details of the administrators.</h3>
        </div>
        </div>
        </div>
        </div>
        )
    }
}
export default Contact
