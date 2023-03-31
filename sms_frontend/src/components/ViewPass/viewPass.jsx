import { useRef, useState } from "react";
import qrcode from "qrcode";
// import {QrReader} from "react-qr-reader"
function ViewPass(){
    const qrRef = useRef(null)
    const [fileResult, setFileResult] = useState()
    const [webcamResult, setwebcamResult] = useState()

    const [text,setText] = useState("");
    const [imgQR, setImageQR] = useState();

    const generateQrCode  = async ()=>{
        const image = await qrcode.toDataURL(text)
        setImageQR(image)
    }

    const openDialog = ()=>{
        qrRef.current.openImageDialog()
    }
    const fileError = (error)=>{
        if(error){
            console.log(error);

        }
    }

    const fileScan = (result)=>{
        if(result){
            setFileResult(result)
        }
    }
    const webCamError = (error)=>{
        if(error){
            console.log(error);

        }
    }

    const webCamScan = (result)=>{
        if(result){
            setwebcamResult(result)
        }
    }
    return (
        <div className="container mx-auto mt-2">
             <div className="row">
            <h2 className="col-sm-10 badges bg-danger text-center text-white">QrCode generator</h2>
        </div>
        <div className="row">
            <h3 className="col-sm-12">
                Enter text for Qr-code
            </h3>
        </div>
        <div className="row">
            <input type="text" className="col-sm-5" value={text} onChange={(e)=>setText(e.target.value)}/>
            <button className="col-sm-2 btn btn-primary btn-sm m-2" onClick={generateQrCode}>Generate Qr-code</button>
        </div>
      <div class="card col-sm-3 m-2">
        <div class="card-header m-1 rounded text-center">
          <h3 class="badges bg-secondary rounded text-center text-light">
            Qrcode Image
          </h3>
        </div>
        <div class="card-body text-center">
        {imgQR && (<a href ={imgQR} download><img src={imgQR} width="100%" alt = "qr code pic is here"/></a>)}
        </div>
      </div>
        </div>
    );
}
export default ViewPass;