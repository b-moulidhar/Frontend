import { useEffect, useRef, useState } from "react";
import qrcode from "qrcode"
import axios from "axios";
import "./viewPass.css"
import { useParams } from "react-router";
// import {QrReader} from "react-qr-reader"
function ViewPass(){
    // const qrRef = useRef(null)
    // const [fileResult, setFileResult] = useState()
    // const [webcamResult, setwebcamResult] = useState()

    // const [text,setText] = useState("");
    const {id} = useParams();
    const [imgQR, setImageQR] = useState();
    // const [data,setData] = useState({
    //   name:"",
    //   emp_id:Number,
    //   email:""
    // })
    // const [empName, setEmpName] = useState('');
    // const [code, setCode] = useState('');
    const [empData, setEmpData] = useState([{}]);
  
    useEffect(() => {
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
      const fetchPasscode = async () => {
        try {
          const response = await axios.get(`http://10.191.80.104:7001/viewPass/${id}`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid")
            }
          });
         
          let imgData = JSON.stringify(response.data)
          if (typeof imgData === "string") {
            const image = qrcode.toDataURL(imgData);
            setImageQR(image);
          } else {
            throw new Error("Invalid response data type");
          }
        } catch (error) {
          console.error(error);
        }
      };
      
      const fetchEmpData = async () => {
        const response = await axios.post(`http://10.191.80.104:7001/seats/GettingDetailsOfViwPass/${id}`, {},{
          headers:{
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role":localStorage.getItem("role"),
              "X-Eid":localStorage.getItem("eid")
          }
      });
      setEmpData(response.data);
      console.log(response.data);
      console.log(empData)
      };
      fetchPasscode();
      fetchEmpData();
    }, []);
 
    return (
      <div className="container mx-auto mt-2">
        <div className="row">
          <h2 className="col-sm-10 badges bg-danger text-center text-white">
            QrCode generator
          </h2>
          <div className="details">
            <div>Name:{empData[0].emp_name} </div>
            <div>Emp Id:{empData[0].emp_id}</div>
            <div>Booked Seat: {empData[0].s_name} </div>
            <div>Booked shift:{empData[0].st_start}-{empData[0].st_end} </div>
          </div>
        </div>
       
        <div class="card col-sm-3 m-2">
          <div class="card-header m-1 rounded text-center">
            <h3 class="badges bg-secondary rounded text-center text-light">
              Qrcode Image
            </h3>
          </div>
          <div class="card-body text-center">
            {imgQR && (
              <a href={imgQR} download>
                <img src={imgQR} width="100%" alt="qr code pic is here" />
              </a>
            )}
          </div>
        </div>
      </div>
    );
}
export default ViewPass;