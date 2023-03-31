import { useState } from "react";
import axios from "axios";

import Navbar from "../Navbar/navbar";
import { useNavigate } from "react-router-dom";


function LoginPage() {
  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");


  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:7001/api/login", { empId, pass });
      const { token, EId, role } = response.data;
      localStorage.setItem("token", token);
      localStorage.setItem("EId", EId);
      localStorage.setItem("role", role);
      // redirect to the home page or any other page
      window.location="/dashboard";
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setErrorMessage("Invalid credentials");
      } else {
        setErrorMessage("An error occurred during login");
      }
    }
  };

  return (
    <div>
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
)}
export default function Login()