import React,{useEffect,useState} from 'react';
import axios from 'axios';
import AllBookings from '../Components/AllBookings'

const Bookings=()=>{
    const [bookingsData,setbookingsData]=useState([])
    useEffect(()=>{
        axios.get('http://localhost:8080/bookings')
            .then(res=>{
                setbookingsData(res.data.data)
            })
            .catch(err=>{
                console.log(err);
            })
    },[])
    return(
        <>
            <AllBookings data={bookingsData}/>
        </>
    )
}

export default Bookings;
