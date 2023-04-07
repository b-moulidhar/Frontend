import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Atten_Regularize(){
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  // const [shiftStart, setShiftStart] = useState("");
  // const [shiftEnd, setShiftEnd] = useState("");
  // const [dutyType, setDutyType] = useState("");
  // const [comment, setComment] = useState("");
  var shiftStart = [];
  var shiftEnd = [];
  var shift_Start;
  var shift_End;

  // const handleSubmit = async (event) => {
  //   event.preventDefault();
    
  //   try {
  //     const response = await axios.post("", {
  //       start_date,
  //       end_date,
  //       shift_start,
  //       shift_end,
  //     });
      
  //     console.log(response.data); 
  //   } 
    
  //   catch (error) {
  //     console.error(error);
  //   }
  // };

  useEffect(() => {
    axios.get('http://10.191.80.104:7001/shiftStart',{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role":localStorage.getItem("role"),
          "X-Eid":localStorage.getItem("eid")
      }
  }).then(function (response) {
         shiftStart = response.data;
        return axios.get('http://10.191.80.104:7001/shiftEnd',{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
      });
      })
      .then(function (response) {
         shiftEnd = response.data;
         return axios.post('',{
          startDate,
          endDate,
          shift_Start,
          shift_End,
         },{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
        })
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []); 

  
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const StartShiftTime =(e) => {
    shift_Start = e.target.value
  }
  const EndShiftTime =(e) => {
    shift_End = e.target.value
  }

    return(

        <form onSubmit={handleSubmit}>
          <label>
            Start Date:
            <input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          </label>
          <label>
            End Date:
            <input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
          </label>
          <br />
          <label htmlFor="shift-start-input">
            Shift Start:
            <select id="shift-start-input"
            value={shift_Start}
            onChange={StartShiftTime}
            className='atten-form'
            required>
                <option value="" disabled>--Select--</option>
                {
          
          shiftEnd.map((start,idx)=>{
            return <option key={idx} value={start}>{start}</option>
            // return console.log(start);
          })
        }
            </select>
          </label>
          <br />
          <label htmlFor="shift-end-input">
            Shift End:
            <select id="shift-end-input"
            value={shift_End}
            onChange={EndShiftTime}
            className='atten-form'
            required>
                <option value="" disabled>--Select--</option>
                {
          
          shiftEnd.map((end,idx)=>{
            return <option key={idx} value={end}>{end}</option>
            // return console.log(end);
          })
        }
            </select>
          </label>
          {/* <label>
            Shift End:
            <input type="time" value={shiftEnd} onChange={(e) => setShiftEnd(e.target.value)} />
          </label> */}
          <br />
          {/* <label>
            Duty Type:
            <select value={dutyType} onChange={(e) => setDutyType(e.target.value)}>
              <option value="">Select a duty type</option>
              <option value="Bank Related Work">Bank Related Work</option>
              <option value="Caring For Family Member">Caring For Family Member</option>
              <option value="Customer/Vendor Meeting">Customer/Vendor Meeting</option>
              <option value="Forgot to Punch In">Forgot to Punch In</option>
              <option value="Off Duty">Off Duty</option>
              <option value="Official Tour/Travel">Official Tour/Travel</option>
              <option value="Official Training">Official Training</option>
              <option value="On Duty">On Duty</option>
              <option value="Others">Others</option>
              <option value="Personal">Personal</option>
              <option value="School PTM">School PTM</option>
              <option value="Sick">Sick</option>
              <option value="Work From Home">Work From Home</option>
            </select>
          </label> */}
          {/* <br />
          <label>
            Employee Comments:
            <textarea value={comment} onChange={(e) => setComment(e.target.value)} />
          </label>
          <br /> */}
          <button type="submit">Regularize</button>
        </form>
  );
}

export default Atten_Regularize;