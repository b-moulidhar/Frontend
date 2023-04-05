import { useEffect, useRef, useState } from "react";
import qrcode from "qrcode"
import axios from "axios";
import "./viewPass.css"
// import {QrReader} from "react-qr-reader"
function ViewPass(){
    // const qrRef = useRef(null)
    // const [fileResult, setFileResult] = useState()
    // const [webcamResult, setwebcamResult] = useState()

    // const [text,setText] = useState("");
    const [imgQR, setImageQR] = useState();
    const [data,setData] = useState({
      name:"",
      emp_id:Number,
      email:""
    })

    // const generateQrCode  = async ()=>{
    //     const image = await qrcode.toDataURL(text)
    //     setImageQR(image)
    // }
    useEffect(() => {
      axios.get("https://reqres.in/api/users").then(async(response) => {
        const image = await qrcode.toDataURL(response.data.data[1].email)
        setImageQR(image);
      }); 
      axios.get("https://reqres.in/api/users")
      .then((res)=>{
        console.log(res.data.data[1].first_name)
        setData({
         ...data,name:res.data.data[1].first_name,
                 emp_id:res.data.data[1].id,
                 email:res.data.data[1].email
        })
      })
    }, []);

    // const openDialog = ()=>{
    //     qrRef.current.openImageDialog()
    // }
    // const fileError = (error)=>{
    //     if(error){
    //         console.log(error);

    //     }
    // }

    // const fileScan = (result)=>{
    //     if(result){
    //         setFileResult(result)
    //     }
    // }
    // const webCamError = (error)=>{
    //     if(error){
    //         console.log(error);

    //     }
    // }

    // const webCamScan = (result)=>{
    //     if(result){
    //         setwebcamResult(result)
    //     }
    // }
    return (
      <div className="container mx-auto mt-2">
        <div className="row">
          <h2 className="col-sm-10 badges bg-danger text-center text-white">
            QrCode generator
          </h2>
          <div className="details">
            <div>Name:{data.name} </div>
            <div>Emp Id:{data.emp_id}</div>
            <div>Booked Seat: {localStorage.getItem("seat_name")} </div>
            <div>Booked shift:{data.email} </div>
          </div>
        </div>
        {/* <div className="row">
            <h3 className="col-sm-12">
                Enter text for Qr-code
            </h3>
        </div> */}
        {/* <div className="row">
            <input type="text" className="col-sm-5" value={text} onChange={(e)=>setText(e.target.value)}/>
            <button className="col-sm-2 btn btn-primary btn-sm m-2" onClick={generateQrCode}>Generate Qr-code</button>
        </div> */}
     
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