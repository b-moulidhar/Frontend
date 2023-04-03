import React, { useState } from 'react';
import axios from 'axios';

function Atten_Regularize(){
  const [date, setDate] = useState("");
  const [timeIn, setTimeIn] = useState("");
  const [timeOut, setTimeOut] = useState("");
  const [dutyType, setDutyType] = useState("");
  const [comment, setComment] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    try {
      const response = await axios.post("", {
        date,
        timeIn,
        timeOut,
        dutyType,
        comment
      });
      
      console.log(response.data); 
    } 
    
    catch (error) {
      console.error(error);
    }
  };

    return(

        <form onSubmit={handleSubmit}>
          <label>
            Date:
            <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
          </label>
          <br />
          <label>
            Time In:
            <input type="time" value={timeIn} onChange={(e) => setTimeIn(e.target.value)} />
          </label>
          <br />
          <label>
            Time Out:
            <input type="time" value={timeOut} onChange={(e) => setTimeOut(e.target.value)} />
          </label>
          <br />
          <label>
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
          </label>
          <br />
          <label>
            Employee Comments:
            <textarea value={comment} onChange={(e) => setComment(e.target.value)} />
          </label>
          <br />
          <button type="submit">Regularize</button>
        </form>
  );
}

export default Atten_Regularize;