import { useState } from "react";
import axios from "axios";

import Navbar from "../Navbar/navbar";
import { useNavigate } from "react-router-dom";
import { useLocation } from 'react-router-dom';


function LoginPage() {
  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const location = useLocation();
  const message = location.state && location.state.message;

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:7001/api/login", { empId, pass });
      const { token, EId, role } = response.data;
      localStorage.setItem("token", token);
      localStorage.setItem("EId", EId);
      localStorage.setItem("role", role);
      // check if token exists before redirecting to dashboard
      if (response.data.token!=undefined) {
        window.location="/dashboard";
      } else {
        setErrorMessage("wrong credentials");
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setErrorMessage(error.response.data.error);
      } else {
        setErrorMessage("User does not exist");
      }
    }
  };
  
  

  return (
    <div>
      {message && <p>{message}</p>}
      {errorMessage && <p>{errorMessage}</p>}
      <label htmlFor="empId">Employee ID</label>
      <input
        id="empId"
        type="text"
        value={empId}
        onChange={(e) => setEmpId(e.target.value)}
      />
      <label htmlFor="pass">Password</label>
      <input
        id="pass"
        type="password"
        value={pass}
        onChange={(e) => setPass(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}


export default function Login() {
  return (
    <div>
      <Navbar />
      <LoginPage />
    </div>
  );
}
