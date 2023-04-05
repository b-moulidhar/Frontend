import axios from "axios";
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Forget(){

      const [email,setEmail]=useState({email:''})
      const nav = useNavigate()

      // function changeEmail(event){
      //   setEmail({
      //     [event.target.name]: event.target.value
      //   })
      // }

      function handleSubmit(event){
        event.preventDefault();
        if(email.email.trim() === ""){
          alert("Please Enter Your Email");
        
        }
        else{
          axios.post("http://10.191.80.73:7001/reset/mouli@valtech.com",{})
          .then((res)=>{
              console.log(res)
          })
          nav(`/forget/reset/${email.email}`);
        }
      }

    return(
        <form onSubmit={handleSubmit}>
        <div className="main">
                <div>
                  <h1>Reset Password</h1>
                  <div className="second-input">
                  
                    <input type="email" placeholder="Email"  style={{width: "370px"}} className="name" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"  name="email"
                     onChange={(e)=>{setEmail({...email, email:e.target.value})}} required/>
                  </div>
                 <div className="login-button">
                 <button  type="submit" className="login">submit</button>
                 <br></br>
                 </div>
                  <br></br>
                 
                </div>
           </div> 
        </form>

    )
}