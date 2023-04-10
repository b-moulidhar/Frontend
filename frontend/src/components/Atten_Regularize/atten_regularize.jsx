import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./atten_regularize.css"
import { useParams } from 'react-router-dom';

function Atten_Regularize(){

  const [id,setid] = useState(window.localStorage.getItem("EId"))
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    async function handleLogout() {
        setIsLoggingOut(true);
      
        try {
          const response = await fetch('http://10.191.80.73:7001/api/logout', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
          });
      
          if (response.ok) {
            localStorage.removeItem('token');
            localStorage.removeItem('EId');
            localStorage.removeItem('role');
            window.location = '/';
          } else {
            throw new Error('Logout failed.');
          }
        } catch (error) {
          console.error(error);
          setIsLoggingOut(false);
        }
      }

  

  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [shiftStart, setShiftStart] = useState([]);
  const [shiftEnd, setShiftEnd] = useState([]);
  const [Start,setStart] = useState();
  const [End,setEnd] = useState();
  // var shift_End;
 var sttime = Start+"-"+End;
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    axios.get('http://20.253.3.209:7001/shiftStart',{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role":localStorage.getItem("role"),
          "X-Eid":localStorage.getItem("eid")
      }
  }).then(function (response) {
        //  shiftStart = response.data;
        setShiftStart(response.data)
        //  console.log(shiftStart)
        return axios.get('http://20.253.3.209:7001/shiftEnd',{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
      });
      })
      .then(function (response) {
        setShiftEnd(response.data)
         console.log(shiftEnd)
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []); 

  
  const handleSubmit = (e) => {
    
    e.preventDefault();
    axios.post(`http://20.253.3.209:7001/attendanceRegularization/${localStorage.getItem("EId")}?startDate=${startDate}&endDate=${endDate}&stTime=${sttime}`,{},{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
        }).then((res) => {
          console.log(res.data);
          // console.log("hello")
          setSuccessMessage("Regularization request submitted successfully.");
        });
  };

  // const StartShiftTime =(e) => {
  //   shift_Start = e.target.value
  // }
  // const EndShiftTime =(e) => {
  //   shift_End = e.target.value
  // }

    return(
      <div>
         
         <nav className="navbar navbar-expand-lg navbar-light bg-light">
              <a className="navbar-brand" href="#">SMS</a>
              <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon" />
                {/* {console.log(id)} */}
              </button>
              <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mr-auto">
                  <li className="nav-item active">
                    <a className="nav-link" href={`/dashboard/${id}`}>DashBoard <span className="sr-only"></span></a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href={`/profile/${id}`}>Profile</a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="">Regularization</a>
                  </li>
                </ul>
                <ul className="navbar-nav ml-auto">
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href={`/notify/${id}`}>Notification</a>
                  </li>
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                  </li>
                </ul>
              </div>
            </nav>

        <form onSubmit={handleSubmit} className='col-sm-10 regularize_form'>
          <label className='form-label'>
            Start Date:
            <input type="date" className='form-control' value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          </label>
        
          <label className='form-label'>
            End Date:
            <input type="date" className='form-control' value={endDate} onChange={(e) => setEndDate(e.target.value)} />
          </label>
          <br />
          <label htmlFor="shift-start-input"  className='form-label'>
            Shift Start:
            <select id="shift-start-input"
            value={Start}
            onChange={(e)=>{setStart(e.target.value)}}
            className='atten-form'
            required>
                {/* <option value="" disabled>--Select--</option> */}
                {
          
          shiftStart.map((start,idx)=>{
            console.log(start)
            return <option key={idx} value={start}>{start}</option>
            
          })
        }
            </select>
          </label >
          <br />
          <label htmlFor="shift-end-input" className='form-label'>
            Shift End:
            <select id="shift-end-input"
            value={End}
            onChange={(e)=>{setEnd(e.target.value)}}
            className='atten-form'
            required>
                {/* <option value="" disabled>--Select--</option> */}
                {
          
          shiftEnd.map((end,idx)=>{
            return <option key={idx} value={end}>{end}</option>
          })
        }
            </select>
          </label>
          <button type="submit" className=' col-6 btn btn-primary rbtn'>Regularize</button>
          {console.log(startDate)}
          {console.log(endDate)}
          {console.log(Start)}
          {console.log(End)}
          {console.log(sttime)}
        </form>
        </div>
  );
}

export default Atten_Regularize;