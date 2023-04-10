import { useEffect, useRef, useState } from "react";
import qrcode from "qrcode"
import axios from "axios";
import "./viewPass.css"
import { useParams } from "react-router";
// import {QrReader} from "react-qr-reader"
function ViewPass(){
  const [id,setid] = useState(window.localStorage.getItem("EId"))
  const [isLoggingOut, setIsLoggingOut] = useState(false);
    // const [text,setText] = useState("");
    // const {id} = useParams();
    const [imgQR, setImageQR] = useState();
    // const [data,setData] = useState({
    //   name:"",
    //   emp_id:Number,
    //   email:""
    // })
    // const [empName, setEmpName] = useState('');
    // const [code, setCode] = useState('');
    const [empData, setEmpData] = useState([{}]);
  
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
      let imgData = "5414098754"
          // if (typeof imgData === "string") {
            const image = qrcode.toDataURL(imgData);
            setImageQR(image);
      // const fetchPasscode = async () => {
      //   const response = await axios.get(`http://10.191.80.104:7001/viewPass/${id}`, {
      //     headers:{
      //         Authorization: `Bearer ${localStorage.getItem("token")}`,
      //         "X-Role":localStorage.getItem("role"),
      //         "X-Eid":localStorage.getItem("eid")
      //     }
      // });
      // console.log( typeof response.data)
      //   let imgData = JSON.stringify(response.data)
      //   console.log(imgData)
      //   const image = qrcode.toDataURL(imgData)
      //    setImageQR(image);
      // };
      // const fetchPasscode =  () => {
        try {
           axios.get(`http://20.253.3.209:7001/viewPass/1`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid")
            }
          }).then((res)=>{
              console.log(res.data)
          })
          let imgData = "5414098754"
          // if (typeof imgData === "string") {
            const image = qrcode.toDataURL(imgData);
            setImageQR(image);
          // } else {
          //   throw new Error("Invalid response data type");
          // }
        } catch (error) {
          console.error(error);
        }
      // };
      
      const fetchEmpData = async () => {
        const response = await axios.post(`http://20.253.3.209:7001/seats/GettingDetailsOfViwPass/${id}`, {},{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
      });
      // setEmpData(response.data);
      console.log(response.data);
      console.log(empData)
      };
      // fetchPasscode();
      fetchEmpData();
    }, []);
 
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
      <div className="container mx-auto mt-2">
        <div className="row">
          <h2 className="col-sm-10 badges  text-center text-black QR-gene">
            QrCode generator
          </h2>
          <div className="details">
            {/* <div>Name:{empData[0].emp_name} </div>
            <div>Emp Id:{empData[0].emp_id}</div>
            <div>Booked Seat: {empData[0].s_name} </div>
            <div>Booked shift:{empData[0].st_start}-{empData[0].st_end} </div> */}
          </div>
        </div>
       
        <div className="card col-sm-3 m-2">
        <div className="card-header m-1 rounded text-center">
          <h3 className="badges bg-secondary rounded text-center text-light ">
            Qrcode Image
          </h3>
        </div>
        <div className="card-body text-center">
          {'{'}imgQR &amp;&amp; (
          <a href="{imgQR}" download>
            <img src="{imgQR}" width="100%" alt="qr code pic is here" />
          </a>
          ){'}'}
        </div>
      </div>

      </div>
      </div>
      
    );
}
export default ViewPass;

