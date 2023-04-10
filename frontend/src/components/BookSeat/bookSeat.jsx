import React, { useEffect, useState } from "react";
import "./bookSeat.css";
import axios from "axios";
import { useParams } from "react-router-dom";



function BookSeat() {
  const [id,setid] = useState(window.localStorage.getItem("EId"))
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const [branchName, setBranchName] = useState("");
  const [buildingName, setBuildingName] = useState("");
  const [Date, setDate] = useState("");
  const [differenceDay,setDifferenceDay] = useState(0)
  const [toDate, setToDate] = useState("");
  const [firstDate, setFirstDate] = useState("");
  const [meal, setMeal] = useState(false);
  var startTime = [];
  var endTime = [];
  var timeRange = []
  const [shiftTiming, setShiftTiming] = useState("");
  const [request, setRequest] = useState("");
  const [timeRange1, setTimeRange] = useState([]);
  const concatenate=()=>{
    startTime.map((time,idx)=>{
       return timeRange[idx] = `${time}-${endTime[idx]}`
      })
    }

    async function handleLogout() {
      setIsLoggingOut(true);
    
      try {
        const response = await fetch('http://20.253.3.209:7001/api/logout', {
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


    useEffect(() => {
      axios.get('http://20.253.3.209:7001/shiftStart',{
        headers:{
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role":localStorage.getItem("role"),
            "X-Eid":localStorage.getItem("eid")
        }
    }).then(function (response) {
           startTime = response.data;
          return axios.get('http://20.253.3.209:7001/shiftEnd',{
            headers:{
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role":localStorage.getItem("role"),
                "X-Eid":localStorage.getItem("eid")
            }
        });
        })
        .then(function (response) {
           endTime = response.data;
          // const timeRange = startTime + ' - ' + endTime;
          // setTimeRange(timeRange);
          concatenate();
          setTimeRange(timeRange)
        })
        .catch(function (error) {
          console.log(error);
        });
    }, []);

    

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
      alert("Not allowed to book for more than 7 days");
    }
    setToDate(e.target.value)
  }
   const nextPage=()=>{
    if(request==="Daily"){
      localStorage.setItem("from_date",firstDate);
      localStorage.setItem("to_date",firstDate);
      window.location=`/floorlist/${id}`;
    }else if(request==="Weekly"){
      localStorage.setItem("from_date",firstDate);
      localStorage.setItem("to_date",toDate);
      window.location=`/floorlist/${id}`;
    }

      localStorage.setItem("lunch",meal);
      localStorage.setItem("shift_timing",shiftTiming)

   }

  return (
    <div>
       <nav className="navbar fixed-top navbar-light bg-light justify-content-between">
          <div className="navbar-left">
            <a href="#">SMS</a>
          </div>
          <div className="navbar-right">
            <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
          </div>
        </nav>
    <form onSubmit={handleSubmit} className="seat-booking-form">
      <label htmlFor="branch-name-input" className="form-label">Branch Name:</label>
      <select
        id="branch-name-input"
        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input bookseat"
        required
      >
        <option value="Bangalore">Bangalore</option>
      </select>

      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"
        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input bookseat"
        required
      >
        <option value="JP Nagar">JP Nagar</option>
      </select>
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"
        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input bookseat"
        required
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
        className="form-input bookseat"
        required
      >
        <option value="" disabled>--Select--</option>
        {
          // console.log(timeRange1)
          
          timeRange1.map((start,idx)=>{
            return <option key={idx} value={start}>{start}</option>
            // return console.log(start);
          })
        }
        
      </select>
      
        {/* <option value="" disabled>--Select--</option>
        <option value="Morning">9:00AM - 6:00PM</option>
        <option value="Afternoon">2:00PM - 10:00PM</option>
        <option value="night">10:00PM - 6:00AM</option> */}
      {/* </select> */}
      <label htmlFor="meal-name-input">Meal:</label>
      <select
        id="meal-name-input"
        value={meal}
        onChange={(e) => setMeal(e.target.value)}
        className="form-input bookseat"
        required
      >
        <option value="select">Select</option>
        <option value={true}>Yes</option>
        <option value={false}>No</option>
      </select>

      {request === "Daily" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input bookseat"
            required
          />
          <label htmlFor="to-date-input">To Date:</label>
          <input
            id="to-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            // onChange={(e) => setToDate(e.target.value)}
            // onChange={sameDate}
            className="form-input bookseat"
            required
          />
        </>
      )}

      {request === "Weekly" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input bookseat"
            required
          />
          <label htmlFor="to-date-input">To Date:</label>
          <input
            id="to-date-input"
            type="date"
            min={todayDate()}
            value={toDate}
            // onChange={(e) => setToDate(e.target.value)}
            // onChange={(e) => setToDate(e.target.value)}
            onChange={(e) => weeklydate(e)}
            className="form-input bookseat"
            required
          />
        </>
      )}

      <button onClick={nextPage} className=" col-8 btn btn-primary">Next</button>
    </form>
    </div>
  );
}

export default BookSeat;
