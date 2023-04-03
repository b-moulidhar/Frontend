import React, { useState } from "react";
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
      alert("Not allowed to book for more than 7 days");
    }
  }

  // var date = new Date();

  return (
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
        <option value="" disabled>--Select--</option>
        <option value="Morning">9:00AM - 6:00PM</option>
        <option value="Afternoon">2:00PM - 10:00PM</option>
        <option value="night">10:00PM - 6:00AM</option>
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
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={toDate}
            min={todayDate()}
            onChange={(e) => setToDate(e.target.value)}
            className="form-input"
          />
          <label htmlFor="to-date-input">To Date:</label>
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
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input"
          />
          <label htmlFor="to-date-input">To Date:</label>
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
  );
}

export default BookSeat;
