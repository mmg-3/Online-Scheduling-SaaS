import axios from "axios";

export function cancel(id)
{
  console.log(id);   
  const url = 'http://localhost:8080/deleteBooking/'
  axios.delete(url+id)
  .then(res => {
    console.log(res);
    console.log(res.data);
    window.location.reload();
  })
  
}