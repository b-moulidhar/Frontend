import axios from 'axios';
import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import './reset.css';

function SignIn() {
  const [input, setInput] = useState({});
  const [errors, setErrors] = useState({});
  const { email } = useParams();

  function handleChange(event) {
    setInput({
      ...input,
      [event.target.name]: event.target.value,
    });
  }

  function handleSubmit(event) {
    event.preventDefault();
    alert(input.otp)
    alert(input.password)
    axios.post(`http://10.191.80.104:7001/reset/newpass/${email}`,{}, {
      params: {
        otp: input.otp,
        pass: input.password,
      }
      
    })
    .then(response => {
      // setFoodCount(response.data);
      console.log(response.data);
    })
    .catch(error => {
      console.log(error);
    });
  }
    // })
    // .then((res) => {
    //   console.log(res.data);
    //   if (res.data === "changed") {
    //     window.location = "/";
    //   } else {
    //     setErrors({ ...errors, password: "Password is not updated." });
    //   }
    // })
    // .catch((err) => {
    //   setErrors({ ...errors, password: "Something went wrong. Please try again." });
    //   console.log(err);
    // });
  // }
  
  
    return (
      <div className="Auth-form-container">
        <form className="Auth-form" >
          <div className="Auth-form-content">
          <h3 className="Auth-form-title">Sign In</h3>
  
          <div class="form-group mt-3">
            <label for="password">OTP</label>
            <input 
              type="text" 
              name="otp" 
              value={input.otp}
              onChange={handleChange}
      
              className="form-control mt-1" 
              placeholder="Enter otp" 
              id="password" />
  
              <div className="text-danger">{errors.otp}</div>
          </div>
          <div class="form-group mt-3">
            <label for="password">Password:</label>
            <input 
              type="password" 
              name="password" 
              value={input.password}
              onChange={handleChange}
      
              className="form-control mt-1" 
              placeholder="Enter password" 
              id="password" />
  
              <div className="text-danger">{errors.password}</div>
          </div>
          <div class="form-group mt-3">
            <label for="password">confirm password</label>
            <input 
              type="password" 
              name="confirm_password" 
              value={input.confirm_password}
              onChange={handleChange}
      
              className="form-control mt-1" 
              placeholder="Enter password" 
              id="password" />
  
              <div className="text-danger">{errors.password}</div>
          </div>
          <div className="d-grid gap-2 mt-3">
            <button type="submit"  onClick={handleSubmit} className="btn btn-primary">reset</button>
          </div>
          </div>
        </form>
      </div>
    );
}

  
export default SignIn;