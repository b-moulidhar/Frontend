import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./atten_regularize.css"
import { useParams } from 'react-router-dom';

function Atten_Regularize(){
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [shiftStart, setShiftStart] = useState([]);
  const [shiftEnd, setShiftEnd] = useState([]);
  const {id}= useParams();
  // const [dutyType, setDutyType] = useState("");
  // const [comment, setComment] = useState("");
  // var shiftStart = [];
  // var shiftEnd = [];
  var shift_Start;
  var shift_End;

 

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
        //  shiftEnd = response.data;
        setShiftEnd(response.data)
        //  console.log(shiftEnd)
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []); 

  
  const clickHandler = (e)=>{
    e.preventDefault();
    console.log("hello")
    axios.post(`http://20.253.3.209:7001/attendanceRegularization/${id}`,{
         params:{
          startDate:startDate,
          endDate:endDate,
          stTime:`${shift_Start}-${shift_End}`,
         }
         },{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
        }).then((res)=>{
          console.log(res.data)
        }).catch((err)=>{
          console.log(err)
        })
  }

  const StartShiftTime =(e) => {
    shift_Start = e.target.value
  }
  const EndShiftTime =(e) => {
    shift_End = e.target.value
  }

    return(

        <form >
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
          
          shiftStart.map((start,idx)=>{
            // console.log(start)
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
            // value={shift_End}
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
          
          <button onClick={clickHandler} >Regularize</button>
        </form>
  );
}

export default Atten_Regularize;

