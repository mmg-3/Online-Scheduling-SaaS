import React from 'react';
import { Table } from 'reactstrap';
import './CSS/global.css';
import AdminHeader from './Layout/AdminHeader'
import {BrowserRouter,Route,Switch,Link} from 'react-router-dom'
import {cancel} from './CancelBooking'

const UserBookingsTable=({data})=>{
    return(
        <>
        <div>
        <AdminHeader/>
        <div className="container-fluid">
        <div className="col-md-10 m-auto">
        <table className="table">
          <thead>
          <th>Booking ID</th>
          <th>Date </th>
          <th>Service Name</th>
          <th>Worker Name</th>
          <th>Time</th>
          <th>Cancel</th>
          </thead>
        </table>
             <div> {
            data && data.map((d)=>{
               return(
                    <div>
                    <table className="table">         
                      <tbody>
                        <td className="text-center" >{d.bookingID}</td>
                        <td className="text-center">{d.date}</td>
                        <td className="text-center">{d.serviceName}</td>
                        <td className="text-center">{d.workerName}</td>
                        <td className="text-center">{d.time}</td>
                        <td className="text-center"><button type="button" class="btn btn-danger" onClick={ () => cancel(d.bookingID) }>Cancel</button></td>
                        
                      </tbody>          
                    </table>  
                  </div>
               )
           })
          } 
        
          <br/>
        </div>
        </div>
        </div>
        <Link to="CustomerHome" class="btn btn-info mt-4" role="button">Customer Home</Link>

        </div>
        </>
    )
}

export default UserBookingsTable;