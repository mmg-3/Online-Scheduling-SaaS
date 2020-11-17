import React, { Fragment } from 'react';
import {BrowserRouter,Route,Switch} from 'react-router-dom'
import './App.css';
import './Components/CSS/global.css';
import Dashboard from './Components/Dashboard';
import Header from './Components/Layout/Header';
import Footer from './Components/Layout/Footer';
import ViewBooking from './Components/ViewBooking';
import CreateBooking from './Components/CreateBooking';
import Home from './Components/Home';
import Contact from './Components/Contact';
import Login from './Components/Login';
import AdminHome from './Components/AdminHome'
import CustomerHome from './Components/CustomerHome' 
import Profile from './Components/Profile'
import AdminProfile from './Components/AdminProfile'
import AllBookings from './Components/AllBookings'
import Bookings from './Components/Bookings'
import Register from './Components/Register'
import AdminCreateBooking from './Components/AdminCreateBooking';
import About from './Components/About'
import RegisterWorker from './Components/RegisterWorker' 
import WorkerHome from './Components/WorkerHome'
import UserBookingsTable from './Components/UserBookingTable'
import UserBookings from './Components/UserBookings'
import PastBookings from './Components/PastBookings'
import WorkerProfile from './Components/WorkerProfile'
import WorkerBookings from './Components/WorkerBookings'
import EditProfile from './Components/EditProfile'
import WorkerEditProfile from './Components/WorkerEditProfile'

function App() {
  
  return (

    <div className="App">
      <BrowserRouter>
         <Switch>
         <Route path="/Login" component={Login}></Route>
         <Route path="/Register" component={Register}></Route>
         <Route path="/RegisterWorker" component={RegisterWorker}></Route>
         <Route exact path="/" component={Login}></Route>

              <Route path="/Home" component={Home}></Route>
              <Route path="/CustomerHome" component={CustomerHome}></Route>
              <Route path="/Profile" component={Profile}></Route>
              <Route path="/EditProfile" component={EditProfile}></Route>
              <Route path="/WorkerEditProfile" component={WorkerEditProfile}></Route>
              <Route path="/CreateBooking" component={CreateBooking}></Route>
              <Route path="/About" component={About}></Route>
              <Route path="/Contact" component={Contact}></Route>

              <Route path="/AdminHome" component={AdminHome}></Route>
              <Route path="/WorkerHome" component={WorkerHome}></Route>
              <Route path="/AdminProfile" component={AdminProfile}></Route>
              <Route path="/AdminCreateBooking" component={AdminCreateBooking}></Route>
              <Route path="/Bookings" component={Bookings}></Route>       
              <Route path="/UserBookings" component={UserBookings}></Route>       
              <Route path="/PastBookings" component={PastBookings}></Route>
              <Route path="/WorkerProfile" component={WorkerProfile}></Route>
              <Route path="/WorkerBookings" component={WorkerBookings}></Route>       
       
       
          </Switch>
      </BrowserRouter> 
    </div>
  );
}
export default App;
