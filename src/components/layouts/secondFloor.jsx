import React, { useEffect, useState } from "react";
import "./ground.css"

const GroundFloor = () => {
  const [seats,setSeats]= useState([
    { id: 1, name: "2001", booked: false, selected:false},
    { id: 2, name: "2002", booked: false, selected:false},
    { id: 3, name: "2003", booked: false, selected:false},
    { id: 4, name: "2004", booked: false, selected:false},
    { id: 5, name: "2005", booked: false, selected:false},
    { id: 6, name: "2006", booked: false, selected:false},
    { id: 7, name: "2007", booked: false, selected:false},
    { id: 8, name: "2008", booked: false, selected: false},
    { id: 9, name: "2009", booked: false, selected:false},
    { id: 10, name: "2010", booked: false, selected:false},
    { id: 11, name: "2011", booked: false, selected:false},
    { id: 12, name: "2012", booked: false, selected:false},
    { id: 13, name: "2013", booked: false, selected:false},
    { id: 14, name: "2014", booked: false, selected:false},
    { id: 15, name: "2015", booked: false, selected:false},
    { id: 16, name: "2016", booked: false, selected:false},
  ]);

  const [selected,setSelected] = useState({})
  
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

      <h2>Second Floor</h2>

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

export default GroundFloor;
