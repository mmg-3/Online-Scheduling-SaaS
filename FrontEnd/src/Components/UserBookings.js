import React,{useEffect,useState} from 'react';
import axios from 'axios';
import UserBookingsTable from './UserBookingTable'

const Bookings=()=>{
    const [bookingsData,setbookingsData]=useState([])
    useEffect(()=>{
        axios.get('http://localhost:8080/getUserBooking')
            .then(res=>{
                setbookingsData(res.data.data)
            })
            .catch(err=>{
                console.log(err);
            })
    },[])
    return(
        <>
            <UserBookingsTable data={bookingsData}/>
        </>
    )
}

export default Bookings;
