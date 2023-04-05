import { useState } from "react"

export default function Forget(){

      const [email,setEmail]=useState({email:''})

      // function changeEmail(event){
      //   setEmail({
      //     [event.target.name]: event.target.value
      //   })
      // }

    return(
        <>
        <div className="main">
            <div >
              <div>
                <div>
                  <h1>Reset Password</h1>
                  <div className="second-input">
                  
                    <input type="email" placeholder="Email"  style={{width: "370px"}} className="name" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"  name="email" onChange={(event)=>setEmail({email:event.target.value})} required/>
                  </div>
                  <br></br>
                 
                 <div className="login-button">
                  <a className="btn btn-primary" href="/forget/reset">submit</a><br></br>
                 <br></br>
                 </div>
                </div>
              </div>
             
       
            </div>
           </div> 
        </>

    )
}