import React, { useEffect, useState } from "react";
import "./ground.css"

const GroundFloor = () => {
  const seats=  [
    { id: 1, name: "0001", booked: false },
    { id: 2, name: "0002", booked: false },
    { id: 3, name: "0003", booked: false },
    { id: 4, name: "0004", booked: false },
    { id: 5, name: "0005", booked: false },
    { id: 6, name: "0006", booked: false },
    { id: 7, name: "0007", booked: false },
    { id: 8, name: "0008", booked: true },
    { id: 9, name: "0009", booked: false },
    { id: 10, name: "0010", booked: false },
    { id: 11, name: "0011", booked: false },
    { id: 12, name: "0012", booked: false },
    { id: 13, name: "0013", booked: false },
    { id: 14, name: "0014", booked: false },
    { id: 15, name: "0015", booked: false },
    { id: 16, name: "0016", booked: false },
  ];

  let [dumseat,Modiseat] = useState([]);

  useEffect(()=>{
    Modiseat(...dumseat,dumseat = seats)
  },[])

  const handleSeatClick = (id) => {
    console.log(id)
    const updatedSeats = dumseat.map((seat) => {
      if (seat.id === id) {
        seats.map((seat1)=>{
            if
            (seat1.booked==true){
                // return seat;
                console.log("hello")
                // return { ...seat, booked: seat.booked };
            }else{
                return { ...seat, booked: !seat.booked };
            }
            console.log(seat1)
        })   
    }
    //   } else {
    //     return seat;
    //   }
    });
    // Modiseat(updatedSeats);
  };

  return (
    <div className="seat-booking-app">
      <h1>Seat Matrix</h1>

      <h2>Ground Floor</h2>

      <div className="seat-map">
        {dumseat.map((seat) => (
          <div
            key={seat.id}
            className={`seat ${seat.booked ? "booking" : ""}`}
            onClick={() => handleSeatClick(seat.id)}>
            {seat.name}
            {/* {console.log(seat.booked)} */}
          </div>
            // }
))}
      </div>
      <br />
      <div id="legend">
        <div class="floor_seat"></div> <div class="txt">Available</div>
        <div class="seat taken"></div> <div class="txt">Taken</div>
        <div class="seat selected"></div>
       {!seats.booked && <div>{seats.name}</div>}
        <div class="txt">Your Chosen Seats</div>
        {/* {
     seats.map((seat)=>(
        <div className='seat-map'>
         <div className='seat'> {seat.id}</div>
        </div>
    )
    )
    } */}
      </div>
    </div>
  );
};

export default GroundFloor;
