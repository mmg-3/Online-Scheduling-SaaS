import React, { Component } from 'react'

 class Footer extends Component {
    render() {
        return (
        <div>
            <div className="footer">
                <nav className="navbar fixed-bottom navbar-expand-sm navbar-dark bg-dark">
                    <div className="container">    
                        <div className="collapse navbar-collapse" id="mobile-nav">
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item">
                                    <a className="nav-link" href="/Contact">Contact Us</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/About">About</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        )
    }
}
export default Footer;