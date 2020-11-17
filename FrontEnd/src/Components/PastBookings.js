import React,{useEffect,useState} from 'react';
import axios from 'axios';
import PastBookingsTable from './PastBookingsTable'

const Bookings=()=>{
    const [bookingsData,setbookingsData]=useState([])
    useEffect(()=>{
        axios.get('http://localhost:8080/getPastBooking')
            .then(res=>{
                setbookingsData(res.data.data)
            })
            .catch(err=>{
                console.log(err);
            })
    },[])
    return(
        <>
            <PastBookingsTable data={bookingsData}/>
        </>
    )
}

export default Bookings;
