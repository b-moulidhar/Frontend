import { useEffect, useState } from "react";
import Navbar from "../Navbar/navbar";
import { Link } from "react-router-dom";

import "./login.css";
import axios from "axios";

export default function Login(){
  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleLogin = (e) => {
    e.preventDefault()
    console.log("hello");
    try {
      const response = axios.post("http://10.191.80.102:7001/api/login", { empId, pass })
      .then((res)=>{
        
        const { token, EId, role } = res.data;
        localStorage.setItem("token", token);
        localStorage.setItem("EId", EId);
        localStorage.setItem("role", role);
        // redirect to the home page or any other page
        if(res.data.token!=undefined){
          if(localStorage.getItem("role")==="Manager"){
            window.location="/manager/"+EId;
          }else if(localStorage.getItem("role")==="Employee"){

            window.location="/dashboard/"+EId;
          }else if(localStorage.getItem("role")==="admin"){
            window.location="/admin/"+EId;

          }
        }else{
          alert("invalid credentials")
        }
 
        console.log("posted",res.data)})
      .catch((err)=>console.log(err));
      // console.log(response.data)
     
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setErrorMessage("Invalid credentials");
        console.log(error.response);
      } else {
        setErrorMessage("An error occurred during login");
        console.log(error.response);

      }
    }
  };
  
//  useEffect(() => {
    //   axios.get("http://10.191.80.112:7001/api/login").then((response) => {
    //     console.log(response.data)
    //   }); 
    // }, []);

// export default api;

   const [state,setState] = useState({uid:'',password:''});
   const[dig,setDig] = useState({dig:Number})

   function loginFunc(event){
    setState({
      [event.target.name]: event.target.value,
    });
    
   }

   function checkVal(event){
    setDig({
      ...dig,dig: event.target.value
    });
    console.log(dig.length)
    if(dig.length<5){
      alert("do not exceed more than 4 characters")
    }

   }
    return (
      <div>
      {/* <Navbar/> */}
      
    <div className="container">
  <div className="row">
    <div className="col-lg-3 col-md-2" />
    <div className="col-lg-6 col-md-8 login-box">
      <div className="col-lg-12 login-key">
        <i className="fa fa-key" aria-hidden="true" />
      </div>
      <div className="col-lg-12 login-title">
        SMS
      </div>
      <div className="col-lg-12 login-form">
        <div className="col-lg-12 login-form">
          <form>
            <div className="form-group">
              <label className="form-control-label">USERNAME</label>
              <input type="text" className="form-control" value={empId} required onChange={(e) => setEmpId(e.target.value)}/>
              <span>{errorMessage&& <span className="danger">*{errorMessage}</span>}</span>
            </div>
            <div className="form-group">
              <label className="form-control-label">PASSWORD</label>
              <input type="password" className="form-control" i value={pass} required  onChange={(e) => setPass(e.target.value)}/>
            </div>
            <div className="col-lg-12 loginbttm">
              <div className="col-lg-6 login-btm login-text">
                {/* Error Message */}
              </div>
              <div className="col-lg-6 login-btm login-button">
                <button type="submit" className="btn btn-outline-primary"  onClick={handleLogin}>LOGIN</button>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div className="col-lg-3 col-md-2" />
    </div>
  </div>
</div>



      </div>
    );
}