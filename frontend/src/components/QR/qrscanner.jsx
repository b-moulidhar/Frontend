import React, { useEffect } from "react";
import { QrReader } from "react-qr-reader";
import { useState } from "react";
import Navbar_Admin from "../navbar/navbar_admin";

function QrCodeScan() {
  const [webcamResult, setwebcamResult] = useState('');
  const [qrdata, setqrdata] = useState([])
  const [token, setToken] = useState(window.localStorage.getItem("token"))
  const [data,setData] =useState()
  // useEffect(()=>{
  //   // props.data(webcamResult)
  //   fetch(`http://10.191.80.98:9090/api/admin/validateToken/?token=${webcamResult}`, {
  //     method: "PUT",
  //     headers: {
  //       Authorization: `Bearer ${token}`,
  //     },
  //   })
  //     .then((response) => {
  //       if (!response.ok) {
  //         throw new Error("Network response was not ok");
  //       } 
  //       return response.json();
  //     })
  //     .then((data) => {
  //       // setShifts(newShifts);
  //       // console.log(data)
  //       setqrdata(data)
        
  //     })
  //     .catch((error) => {
  //       console.error("There was an error deleting the shift:", error);
  //     });
  // },[webcamResult])

  // useEffect(()=>{
  //   // props.emp(qrdata)
  // },[qrdata])


  return (
    <div>
       <Navbar_Admin/>
      <QrReader
        onResult={(result, error) => {
          if (!!result) {
            setData(result?.text);
          }

          if (!!error) {
            console.info(error);
          }
        }}
        style={{ width: '100%' }}
      />
      <p>{data}</p>
    </div>
  );
}

export default QrCodeScan;

