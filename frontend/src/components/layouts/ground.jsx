import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import "./floors.css";
import axios from 'axios';
import { useParams } from 'react-router-dom';

function GroundFloor() {
  
 // Define state variables using the useState hook
  const [seats, setSeats] = useState([]);
  const [data, setData] = useState([]);
  const [seatBooked,setSeatBooked] = useState([{}])
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const gfloorPat = /^[0-9]?[0-9]?[0-9]$/
  const [newSeats,setNewSeats] = useState([])
  // const {id} = useParams()
  const [id,setid] = useState(window.localStorage.getItem("EId"))
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const storedData = localStorage.getItem('from date');
  let seatTemp = []

  async function handleLogout() {
    setIsLoggingOut(true);
  
    try {
      const response = await fetch('http://10.191.80.102:7001/api/logout', {
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


// Use the useEffect hook to fetch data from the server 
   useEffect(() => {
     axios.get("http://20.253.3.209:7001/seats/total", {

      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
    })
      .then((res) => {
        // console.log(res.data)
        setData(res.data);
        // setSeatBooked(JSON.parse(text))
      });      
  },[]);
  useEffect(()=>{
    axios.get("http://20.253.3.209:7001/seats/booked/2023-04-07",{

    headers: {
      Authorization: "Bearer " + token,
      "Content-Type": "application/json",
    },
  }).then((res)=>{
      console.log(res.data)
      // setSeatBooked(res.data)
  })
  },[]);
  
  useEffect(()=>{
    
    data.map((seats,idx)=>{
      if(gfloorPat.test(seats)){
        // console.log(seats)
        const seatName = `${seats}`;
          const isBooked = seatBooked.some((seat) =>seat === seatName);
          console.log(isBooked);
          seatTemp.push({
            id: idx,
            name: seatName,
            booked: isBooked,
            selected: false,
          });

          setNewSeats(seatTemp)

      }
    //  return console.log(seats);
 
    })
  },[data])
  const [selected, setSelected] = useState({});
// Define a function to handle the click event on a seat
  const handleSeatClick = (name) => {
    // console.log(name);
    //logic for deselection
    // setSelected({
    //   seatId: name,
    //   // floorId: "GF",
    // });
  };

  const sendData = () => {
    if (selected.seatId != null) {
      localStorage.setItem("seat_name", selected.seatId);
      axios.post(`http://20.253.3.209:7001/seats/create/${localStorage.getItem("EId")}?sname=0${localStorage.getItem("seat_name")}&sttime=${localStorage.getItem("shift_timing")}&from=${localStorage.getItem("from_date")}&to=${localStorage.getItem("to_date")}`,{},{
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
      // window.location = "/viewpass/3";
    } else {
      alert("please select a seat");
    }
  };

  return (
   
    <div className="seat-booking-app">
       <nav className="navbar fixed-top navbar-light bg-light justify-content-between">
          <div className="navbar-left">
            <a href="#">SMS</a>
          </div>
          <div className="navbar-right">
            <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
          </div>
        </nav>
      <div>
        <h1>Ground Floor</h1>
      </div>
       {/* Map over the seats array and render the seats */}
      <div className="seat-map">
      
        {newSeats.map((seat) =>
       
          seat.booked ? (
           <div>
              <p>
                {seat.name}
                <input id={seat.id} className="seat booked" disabled>
                </input>
              </p>
            </div>
          ) : (
            <div>
              <label>
                {seat.name}
                <input
                  className="seat"
                  name="test"
                  // checked={}
                  value={seat.name}
                  onClick={() => handleSeatClick(seat.name)}
                  type="radio"
                />
              </label>
            </div>
          )         
         ) }
      </div>
      <br />
      <div id="legend">
        <label>
          Available seats
          <input className="seat" name="test" type="radio" disabled />
        </label>
        <label>
          selected seats
          <input
            className="seat select"
            name="test"
            type="radio"
            disabled
          />{" "}
        </label>{" "}
        <label>
          Booked seats
          <input className="seat booked" name="test" type="radio" disabled />
        </label>
        <div>
          Your Chosen Seats
          {!seats.booked && <div>{selected.seatId}</div>}
        </div>
       
      </div>
      
      <button onClick={sendData} className="btn btn-warning select_Nextbtn">
        Submit
      </button>
     
      
    </div>
  );
}



export default GroundFloor;
