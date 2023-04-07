// import React, { useEffect, useState } from "react";
// import "./bookSeat.css";
// import axios from "axios";
// import Api from "../Api/api";

// function BookSeat() {
//   const [branchName, setBranchName] = useState("");
//   const [buildingName, setBuildingName] = useState("");
//   const [Date, setDate] = useState("");
//   const [differenceDay,setDifferenceDay] = useState(0)
//   const [toDate, setToDate] = useState("");
//   const [firstDate, setFirstDate] = useState("");
//   const [meal, setMeal] = useState("");
//   var startTime = [];
//   var endTime = [];
//   // const [fromDate, setfromDate] = useState('');

//   const [shiftTiming, setShiftTiming] = useState("");
//   const [request, setRequest] = useState("");

//     useEffect(()=>{
//       axios
//         .get("http://10.191.80.73:7001/shiftStart", {
//           headers: {
//             Authorization: `Bearer ${localStorage.getItem("token")}`,
//             "X-Role": localStorage.getItem("role"),
//             "X-Eid": localStorage.getItem("eid"),
//           },

//           responseType: "json",
//         })
//         .then((res) => {
//           console.log(res.data);
//           // startTime = res.data
//         })
//         .catch((err) => {
//           console.log(err);
//         });

//       axios.get("http://10.191.80.73:7001/shiftEnd",{
//         headers: {
//           Authorization: `Bearer ${localStorage.getItem("token")}`,
//           "X-Role": localStorage.getItem("role"),
//           "X-Eid": localStorage.getItem("eid"),
//         },

//         responseType: "json",
//       })
//       .then((response)=>{
//         console.log(response.data)
//         // endTime = response.data
//       })
//     },[])
//   const handleSubmit = (e) => {
//     e.preventDefault();
//   };

//   function todayDate() {
//     const now = new window.Date();
//     const year = now.getFullYear();
//     const month = now.getMonth() + 1;
//     const day = now.getDate();
//     return `${year}-${month.toString().padStart(2, "0")}-${day
//       .toString()
//       .padStart(2, "0")}`;
//   }

//   function weeklydate(e) {
//     let startDate = new window.Date(firstDate);
//     let lastDate = new window.Date(e.target.value);
//     const diffTime = Math.abs(startDate - lastDate);
//     const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
//     setDifferenceDay(diffDays)
//     if(diffDays>7){
//       alert("Not allowed to book for more than 7 days");
//     }
//   }
//    const nextPage=()=>{
//     window.location="/floorlist"
//    }

//   // var date = new Date();

//   return (
//     <form onSubmit={handleSubmit} className="seat-booking-form">
//       <label htmlFor="branch-name-input">Branch Name:</label>
//       <select
//         id="branch-name-input"
//         value={branchName}
//         onChange={(e) => setBranchName(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="Bangalore">Bangalore</option>
//       </select>

//       <label htmlFor="building-name-input">Building Name:</label>
//       <select
//         id="building-name-input"
//         value={buildingName}
//         onChange={(e) => setBuildingName(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="JP Nagar">JP Nagar</option>
//       </select>
//       <label htmlFor="request-input">Select type Of Requests:</label>
//       <select
//         id="request-input"
//         value={request}
//         onChange={(e) => setRequest(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="select">Select</option>
//         <option value="Daily">Daily</option>
//         <option value="Weekly">Weekly</option>
//       </select>
//       <label htmlFor="shift-timing-input">Shift Start Timing:</label>
//       <select
//         id="shift-timing-input"
//         value={shiftTiming}
//         onChange={(e) => setShiftTiming(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="" disabled>--Select--</option>
//         {
//           startTime.map((start)=>{
//             return <option value={start}>{start}</option>

//           })
//         }
        
//       </select>
//       <label htmlFor="shift-timing-input">Shift End Timing:</label>
//       <select
//         id="shift-timing-input"
//         value={shiftTiming}
//         onChange={(e) => setShiftTiming(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="" disabled>--Select--</option>
//         <option value="Morning">9:00AM - 6:00PM</option>
//         <option value="Afternoon">2:00PM - 10:00PM</option>
//         <option value="night">10:00PM - 6:00AM</option>
//       </select>
//       <label htmlFor="meal-name-input">Meal:</label>
//       <select
//         id="meal-name-input"
//         value={meal}
//         onChange={(e) => setMeal(e.target.value)}
//         className="form-input"
//         required
//       >
//         <option value="select">Select</option>
//         <option value="yes">Yes</option>
//         <option value="no">No</option>
//       </select>

//       {request === "Daily" && (
//         <>
//           {" "}
//           <label htmlFor="from-date-input"> From Date:</label>
//           <input
//             id="from-date-input"
//             type="date"
//             value={toDate}
//             min={todayDate()}
//             onChange={(e) => setToDate(e.target.value)}
//             className="form-input"
//             required
//           />
//           <label htmlFor="to-date-input">To Date:</label>
//           <input
//             id="to-date-input"
//             type="date"
//             value={toDate}
//             min={todayDate()}
//             // onChange={(e) => setToDate(e.target.value)}
//             onChange={(e) => setToDate(e.target.value)}
//             className="form-input"
//             required
//           />
//         </>
//       )}

//       {request === "Weekly" && (
//         <>
//           {" "}
//           <label htmlFor="from-date-input"> From Date:</label>
//           <input
//             id="from-date-input"
//             type="date"
//             min={todayDate()}
//             onChange={(e) => setFirstDate(e.target.value)}
//             className="form-input"
//             required
//           />
//           <label htmlFor="to-date-input">To Date:</label>
//           <input
//             id="to-date-input"
//             type="date"
//             min={todayDate()}
//             // onChange={(e) => setToDate(e.target.value)}
//             // onChange={(e) => setToDate(e.target.value)}
//             onChange={(e) => weeklydate(e)}
//             className="form-input"
//             required
//           />
//         </>
//       )}

//       <button onClick={nextPage} className="btn btn-primary">Next</button>
//     </form>
//   );
// }

// export default BookSeat;


import React, { useEffect, useState } from "react";
import "./bookSeat.css";
import axios from "axios";
import { useParams } from "react-router-dom";



function BookSeat() {
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
  const {id} = useParams()
  // const [fromDate, setfromDate] = useState('');

  const [shiftTiming, setShiftTiming] = useState("");
  const [request, setRequest] = useState("");

 
  

    const [timeRange1, setTimeRange] = useState([]);
    const concatenate=()=>{
      startTime.map((time,idx)=>{
            return timeRange[idx] = `${time}-${endTime[idx]}`

      })
    }
    useEffect(() => {
      axios.get('http://10.191.80.104:7001/shiftStart',{
        headers:{
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role":localStorage.getItem("role"),
            "X-Eid":localStorage.getItem("eid")
        }
    }).then(function (response) {
           startTime = response.data;
          return axios.get('http://10.191.80.104:7001/shiftEnd',{
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
          // console.log(timeRange)
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
  //  const sameDate=()=>{
  //   setToDate(firstDate);
  //   console.log(to)
  //  }

  // var date = new Date();

  return (
    <form onSubmit={handleSubmit} className="seat-booking-form">
      <label htmlFor="branch-name-input">Branch Name:</label>
      <select
        id="branch-name-input"
        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input"
        required
      >
        <option value="Bangalore">Bangalore</option>
      </select>

      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"
        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input"
        required
      >
        <option value="JP Nagar">JP Nagar</option>
      </select>
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"
        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input"
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
        className="form-input"
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
        className="form-input"
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
            className="form-input"
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
            className="form-input"
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
            className="form-input"
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
            className="form-input"
            required
          />
        </>
      )}

      <button onClick={nextPage} className="btn btn-primary">Next</button>
    </form>
  );
}

export default BookSeat;
