// import { useState } from "react"
// import Sidebar from "../Sidebar/sidebar"
// import "./bookSeat.css";
// import axios from 'axios';

// export default function BookSeat(){

//     let approve = () => {
//         axios.post("",place)
//         .then(response => {
//             setPlace({
//                 request:'',
//             fromdate:Date,
//             todate:Date,
//             shifttime: "",
//             optFood:false
//             })
//             console.log(response.data)
//         })
//         .catch(err => console.log("Error ", err))    
//     }
       
    
//     const [place,setPlace] = useState({
//             request:'',
//             fromdate:Date,
//             todate:Date,
//             shifttime: "",
//             optFood:false
//     })



//     function getRequest(event){
//             setPlace({
//                 ...place,[event.target.name]:event.target.value,
//             })
//             console.log(place.optFood)
//     }

//     // function foodOption(event){
//     //     setOptFood({
//     //         [event.target.name]:event.target.value
//     //     })
//     // }

//     function sendData(){
//         console.log(place.optFood)
//         approve();
//     }
//     return(
//         <div className="bookseat_head">
//             <div>
//                 <Sidebar/>
//             </div>
//         <div className="bookseat_container">
//         <select   name="request"  value={place.request} onInput={getRequest} required>
//                     <option value="">Select type of requests</option>
//                     <option value="weekly">weekly</option>
//                     <option value="daily">daily</option>
//                   </select>
//                   <br />
// 		<br/>
//         <label htmlFor="" ></label>
//         <input type="date" name="fromdate" id="fromdate" placeholder="From Date"  required/> &nbsp;&nbsp;<br /><br /><input type="date" name="todate" id="todate" required/>
//         <br />
//         <br />
//                 <select name="shifttime"  value={place.shifttime} onInput={getRequest} required>
//                     <option value="">shift time</option>
//                     <option value="time1">9:00am to 6:00pm</option> 
//                     <option value="time2">10:00am to 6:00pm</option> 
//                     <option value="time3">2:00pm to 10:00pm</option> 
//                     <option value="time4">2:00am to 6:00</option>  
//                   </select>
//                   <br />
//                   <br />
//                   <p>Would you like to opt for lunch at Office? (It would help us arrange the lunch for you)</p>
//                    <select name="optFood" value={place.optFood} onInput={getRequest} required>
//                    <option value="" disabled selected>Select Option</option>
//                     <option value={true}>Yes</option>
//                     <option value={false}>No</option>
//                    </select>
//                     <br/>
//                     <br/>
//                   <a href="/floorList" className="seatbook_next" onClick={sendData} type='submit'>Next</a>
//         </div>
//         </div>
//     )
// }

import React, { useState } from "react";
import Sidebar from "../Sidebar/sidebar";
import "./bookSeat.css";

function BookSeat() {
  const [branchName, setBranchName] = useState("");
  const [buildingName, setBuildingName] = useState("");
  const [Date, setDate] = useState("");
  const [differenceDay,setDifferenceDay] = useState(0)
  const [toDate, setToDate] = useState("");
  const [firstDate, setFirstDate] = useState("");
  const [meal, setMeal] = useState("");
  // const [fromDate, setfromDate] = useState('');

  const [shiftTiming, setShiftTiming] = useState("");
  const [request, setRequest] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  function todayDate() {
    const now = new window.Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const day = now.getDate();
    return `${year}-${month.toString().padStart(2, "0")}-${day
      .toString()
      .padStart(2, "0")}`;
  }

  function weeklydate(e) {
    let startDate = new window.Date(firstDate);
    let lastDate = new window.Date(e.target.value);
    const diffTime = Math.abs(startDate - lastDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    setDifferenceDay(diffDays)
    if(diffDays>7){
      alert("Not allowed to book for more than & days")
    }
  }

  // var date = new Date();

  return (
    <div className="bookseat">
        <div>
            <Sidebar/>
        </div>
    <form onSubmit={handleSubmit} className="seat-booking-form">
      <label htmlFor="branch-name-input">Branch Name:</label>
      <select
        id="branch-name-input"
        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input"
      >
        <option value="Bangalore">Bangalore</option>
      </select>

      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"
        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input"
      >
        <option value="JP Nagar">JP Nagar</option>
      </select>
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"
        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input"
      >
        <option value="select">Select</option>
        <option value="Daily">Daily</option>
        <option value="Weekly">Weekly</option>
      </select>
      <label htmlFor="shift-timing-input">Shift Timing:</label>
      <select
        id="shift-timing-input"
        value={shiftTiming}
        onChange={(e) => setShiftTiming(e.target.value)}
        className="form-input"
      >
        <option value="">--Select--</option>
        <option value="Morning">Morning</option>
        <option value="Afternoon">Afternoon</option>
        <option value="Evening">Evening</option>
        <option value="Night">Night</option>
      </select>
      <label htmlFor="meal-name-input">Meal:</label>
      <select
        id="meal-name-input"
        value={meal}
        onChange={(e) => setMeal(e.target.value)}
        className="form-input"
      >
        <option value="select">Select</option>
        <option value="yes">Yes</option>
        <option value="no">No</option>
      </select>

      {request === "Daily" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> To Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={toDate}
            min={todayDate()}
            onChange={(e) => setToDate(e.target.value)}
            className="form-input"
          />
          <label htmlFor="to-date-input">From Date:</label>
          <input
            id="to-date-input"
            type="date"
            value={toDate}
            min={todayDate()}
            // onChange={(e) => setToDate(e.target.value)}
            onChange={(e) => setToDate(e.target.value)}
            className="form-input"
          />
        </>
      )}

      {request === "Weekly" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> To Date:</label>
          <input
            id="from-date-input"
            type="date"
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input"
          />
          <label htmlFor="to-date-input">From Date:</label>
          <input
            id="to-date-input"
            type="date"
            min={todayDate()}
            // onChange={(e) => setToDate(e.target.value)}
            // onChange={(e) => setToDate(e.target.value)}
            onChange={(e) => weeklydate(e)}
            className="form-input"
          />
        </>
      )}

      <button className="btn btn-primary">
        <a href="/floorList">Next</a>
      </button>
    </form>
    </div>
  );
}

export default BookSeat;
