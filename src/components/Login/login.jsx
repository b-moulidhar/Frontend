import { useState } from "react";
// import Navbar from "../Navbar/navbar";
import axios from 'axios';
import "./login.css";
import { useLocation } from "react-router-dom";

export default function Login(){

  //  const [state,setState] = useState({uid:'',password:''});
  // //  const[dig,setDig] = useState({dig:Number});
  //  const navigate = useNavigate();

  //  function loginFunc(event){
  //   const name = event.target.name;
  //   const value = event.target.value;

  //   setState((prevState) => ({
  //     ...prevState,
  //     [name]: value
  //   }));  
  //  }

  //  function checkVal(event){
  //   setDig({
  //     ...dig,dig: event.target.value
  //   });
  //   console.log(dig.length)
  //   if(dig.length<5){
  //     alert("do not exceed more than 4 characters")
  //   }

  //  }

  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const location = useLocation();
  const message = location.state && location.state.message;

  const handleLogin = (e) => {
    e.preventDefault()
    try {
      const response = axios.post("http://10.191.80.73:7001/api/login", { empId, pass })
      .then((res)=>{
        
        const { token, EId, role } = res.data;
        localStorage.setItem("token", token);
        localStorage.setItem("EId", EId);
        localStorage.setItem("role", role);
        // redirect to the home page or any other page
        if(res.data.token!=undefined){
          window.location="/dashboard/"+EId;
        }else{
          // alert("invalid credentials")
          setErrorMessage("wrong credentials");
        }
        console.log("posted",res.data)})
      .catch((err)=>console.log(err));
      console.log(response.data)
     
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setErrorMessage(error.response.data.error);
        // console.log(error.response);
      } else {
        setErrorMessage("User Does not exist");
        // console.log(error.response);
      }
    }
  };


    return (
      <div className="login_back">  
      <form>
      {message && <p>{message}</p>}        
      {errorMessage && <p>{errorMessage}</p>}
        <div className="main">
        <div className="sub-main">
       <div>
        
         <div>
           <h1>Login Page</h1>
           <br/>
           <div>
             {/* <label htmlFor="empId">Employee ID</label> */}
             <input type="number" id="empId" placeholder="Employee Id" className="name" min="1000" max="9999"    value={empId} onChange={(e) => setEmpId(e.target.value)} required/>
           </div>
           <div className="second-input">
             {/* <label htmlFor="pass">Password <br/></label> */}
             <input type="password" id="pass" placeholder="Password" className="name"  pattern="[A-Za-z0-9#@$&]{3,10}" value={pass}  onChange={(e) => setPass(e.target.value)} required />
           </div>
          <div className="login-button">
          <button  type="submit" className="login" onClick={handleLogin}>Login</button>
          </div>
           
            <p className="link1">
            <a href="/forget" className="a1">Forgot password ?</a> <br></br>
            <a href="/register" className="a2">Register</a>             
            </p>
            
           
 
         </div>
       </div>
       

     </div>
    </div>

      </form>
      </div>
    );
}
