import React,{useEffect,useState} from 'react';
import axios from 'axios';
import WorkerBookingsTable from './WorkerBookingsTable'

const Bookings=()=>{
    const [bookingsData,setbookingsData]=useState([])
    useEffect(()=>{
        axios.get('http://localhost:8080/getWorkerBooking')
            .then(res=>{
                setbookingsData(res.data.data)
            })
            .catch(err=>{
                console.log(err);
            })
    },[])
    return(
        <>
            <WorkerBookingsTable data={bookingsData}/>
        </>
    )
}

export default Bookings;