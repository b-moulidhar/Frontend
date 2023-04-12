
import { useEffect, useState } from "react";
import qrcode from "qrcode";


import axios from "axios";
import Navbar from "../navbar/navbar";
function ViewPass() {
  const [imgQR, setImageQR] = useState();
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState([{}]);
  const [profile, setProfile]=useState([{'emp_name':'','s_name':'','st_start':'','st_end':''}])


  const storedData = localStorage.getItem('userId');

  useEffect(() => {
    axios.get(`http://20.253.3.209:7001/viewPass/${localStorage.getItem("EId")}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "X-Role": localStorage.getItem("role"),
        "X-Eid": localStorage.getItem("eid")
    },
    })
      .then((response) => {
        // if (!response.ok) {
        //   throw new Error(`HTTP error: ${response.status}`);
        // }
        setData(JSON.stringify(response.data));
        // console.log(response.data)

      })
      // .then((res) => {
      //   setData(JSON.parse(res.data));
        
        
     
        
      // });
  },[]);
  useEffect(()=>{
    qrcode.toDataURL(data).then((img)=>{

      setImageQR(img);
    })
  
  },[data])

  useEffect(()=>{
    axios.post(`http://20.253.3.209:7001/seats/GettingDetailsOfViwPass/${localStorage.getItem("EId")}`,{},{
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "X-Role": localStorage.getItem("role"),
        "X-Eid": localStorage.getItem("eid")
    }
    }).then((res)=>{
      // console.log(res.data)
      setProfile(res.data)
      
    })
  },[data])
  return (
    <div className="container">
      <Navbar/>
      <br />
      <div className="card" style={{ width: "18rem" }}>
        <div className="card-header">Seat Booking Details</div>
        <ul className="list-group list-group-flush">
          <li className="list-group-item">Name: {profile[0].emp_name}</li>
          <li className="list-group-item">shift timings: {`${profile[0].st_start}-${profile[0].st_end}`}</li>
          <li className="list-group-item">booked seat: {profile[0].s_name}</li>
        </ul>
      </div>
      <br />
      <br />
      {imgQR && (
        <a href={imgQR} download>
          <img src={imgQR} width="20%" alt="qr code pic is here" />
        </a>
      )}
    </div>
  );
}
export default ViewPass;
