import React, { useEffect, useState } from "react";
import "./ground.css"
import axios from "axios";

const FirstFloor = () => {
  const [seats,setSeats]= useState([
    { id: 1, name: "1001", booked: false, selected:false},
    { id: 2, name: "1002", booked: false, selected:false},
    { id: 3, name: "1003", booked: false, selected:false},
    { id: 4, name: "1004", booked: false, selected:false},
    { id: 5, name: "1005", booked: false, selected:false},
    { id: 6, name: "1006", booked: false, selected:false},
    { id: 7, name: "1007", booked: false, selected:false},
    { id: 8, name: "1008", booked: false, selected: false},
    { id: 9, name: "1009", booked: false, selected:false},
    { id: 10, name: "1010", booked: false, selected:false},
    { id: 11, name: "1011", booked: false, selected:false},
    { id: 12, name: "1012", booked: false, selected:false},
    { id: 13, name: "1013", booked: false, selected:false},
    { id: 14, name: "1014", booked: false, selected:false},
    { id: 15, name: "1015", booked: false, selected:false},
    { id: 16, name: "1016", booked: false, selected:false},
  ]);


  const [selected,setSelected] = useState({})
  
  useEffect(()=>{
    axios.get("http://localhost:7001/seats/available/2023-04-03",{
        headers:{
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role":localStorage.getItem("role"),
            "X-Eid":localStorage.getItem("eid")
        }
    })
    .then((res)=>{
        // setUser(res.data)
        console.log(res)
    }).catch((err)=>{
        console.log(err)
    })
  },[])

  const handleSeatClick = (name) => {
    console.log(name)
    //logic for deselection
    setSelected({
      seatId: name,
    })
  };

  const sendData = ()=>{
    if(selected.seatId!=null){
      localStorage.setItem("seat_name", selected.seatId)
      window.location="/viewpass"
    }else{
      alert("please select a seat")
    }
  }

  return (
    <div className="seat-booking-app">
      <div>
      <h1>Seat Matrix</h1>

      <h2>First Floor</h2>

      </div>

      <div className="seat-map">
        {seats.map((seat) => (
          seat.booked ? <div><label>  
            {seat.name}
            <input
            id={seat.id}
            className="seat booked"
            disabled
            >
            {/* {console.log(seat.booked)} */}
          </input>
            </label> </div>: <div>
          <label >
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

          
          
            // }
))}
      </div>
      <br />
      <div id="legend">
            <label>
             Available seats
              <input
              className="seat"
              name="test"
              type="radio"
              disabled
              />
            </label>
            <label >
             selected seats
              <input
              className="seat select"
              name="test"
              type="radio"
              disabled
              />
            </label>    
            <label >
             Booked seats
              <input
              className="seat booked"
              name="test"
              type="radio"
              disabled
              />
            </label>    
        <div>Your Chosen Seats
       {!seats.booked && <div>{selected.seatId}</div>}
       </div>
        {/* {
     seats.map((seat)=>(
        <div className='seat-map'>
         <div className='seat'> {seat.id}</div>
        </div>
    )
    )
    } */}
    <button onClick={sendData} className="btn btn-warning">submit</button>
      </div>
      
    </div>
  );
};

export default FirstFloor;
