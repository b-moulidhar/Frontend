import React, { useEffect } from "react";
import { QrReader } from "react-qr-reader";
import { useState } from "react";

function QrCodeScan(props) {
  const [webcamResult, setwebcamResult] = useState('');
  const [qrdata, setqrdata] = useState([])
  const [token, setToken] = useState(window.localStorage.getItem("token"))
  useEffect(()=>{
    props.data(webcamResult)
    fetch(`http://10.191.80.98:9090/api/admin/validateToken/?token=${webcamResult}`, {
      method: "PUT",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        } 
        return response.json();
      })
      .then((data) => {
        // setShifts(newShifts);
        // console.log(data)
        setqrdata(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[webcamResult])

  useEffect(()=>{
    props.emp(qrdata)
  },[qrdata])



  

  return (
    <div className="card col-sm-3 m-2">
      <div className="card-header m-1 rounded">
        <h3 className="badges bg-secondary rounded text-center">
          Scan QR
        </h3>
      </div>
      <div className="card-body text-center">
        <QrReader
          onResult={(result, error) => {
            if (!!result) {
              setwebcamResult(result?.text);
            }

            if (!!error) {
              console.info(error);
            }
          }}
          style={{ width: "100%" }}
        />
      </div>
        {/* <div className="card-footer rounded mb-1">
          <h6>Webcam Result : {webcamResult} </h6>
        </div> */}
       
    </div>
  );
}

export default QrCodeScan;

