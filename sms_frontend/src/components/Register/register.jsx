import { useState } from "react";
import './register.css'

function Register(){
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const validatePassword = (password) => {
    password.preventDefault()
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return regex.test(password);
  };

  function handleChange(event){
    const { value } = event.target;
    setPassword(value);
    if (!validatePassword(value)) {
      setError('Password must contain at least 8 characters, including at least one letter and one number');
    } else {
      setError(null);
    }
  };

    const [user,setUser]= useState({name:'',emp_id:'',email:'',role:'',manager:'',password:'',confirmpassword:''})

    function newUser(event){
        setUser({
          [event.target.name]:event.target.value
        })
    }
    function validateSubmit(event){
      event.preventDefault()

        const confirmpassword= user.confirmpassword;
        const password = user.password;

         if((password=='') || (confirmpassword=='')) {
           alert("password should not be empty")
          }
          else if(confirmpassword==password){
           alert("registered successfully")
         }
         else{
            alert("Password and ConfirmPassword are not same");
           
         }

    }

function PasswordValidation() {
  
}
    return(
      
        <div className="register">
            <form className="row g-3 needs-validation" onSubmit={PasswordValidation} noValidate>
            <div className="main">
            <div >
              <div>
                <div>
                  <h1 style={{color:"white"}}>Registration</h1>
                  <div>
                    
                    <input type="text" placeholder="name" className="name" pattern="[A-Za-z]{5,15}" name="name" value={user.name} onChange={newUser} required/>
                  </div>
                  <div className="second-input">
                  
                    <input type="email" placeholder="Email" className="name" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" value={user.email}  name="email" onChange={newUser} required/>
                  </div>
                
                  <div className="second-input">
                  
                    <input type="text" placeholder="Phone number" className="name"  name="pnumber" pattern="(0/91)?[6-9][0-9]{9}" value={user.pnumber}  onChange={newUser} required/>
                  </div>
                  
                  <br></br>
                 <select   name="specialization"  value={user.role} onChange={newUser} required >
                <option value="" disabled selected>Select Your Role</option>
                    <option value="Role 1">Role 1</option>
                    <option value="Role 2">Role 2</option>
                    <option value="Role 3">Role 3</option>
                    <option value="Role 4">Role 4</option> 
                  </select>
                 <select   name="specialization"  value={user.manager} onChange={newUser} required >
                <option value="" disabled selected>Select Your Manager</option>
                    <option value="Manager 1">Manager 1</option>
                    <option value="Manager 2">Manager 2</option>
                    <option value="Manager 3">Manager 3</option>
                    <option value="Manager 4">Manager 4</option> 
                  </select>
                    <br></br>
                    <br></br> 

                  <div className="second-input">
                  
                    <input type="password" placeholder="Password" className="name" name="password" pattern="[A-Za-z0-9#@$&]{3,10}"  value={user.password} onChange={handleChange} required/>
                    {error && <div style={{ color: 'red' }}>{error}</div>}
                  </div>
                  <div className="second-input">
                  
                    <input type="password" placeholder=" Confirm Password" className="name" name="confirmpassword" pattern="[A-Za-z0-9#@$&]{3,10}" value={user.confirmpassword} onChange={newUser}  required/>
                  </div>   

                
                 
                 <div className="login-button">
                 <a href="/login"><button  >submit</button></a><br></br>
                 <br></br>
                 {/* <button ><Link to={"/"}>Login Page</Link></button> */}
                 </div>
                </div>
              </div>
             
       
            </div>
           </div>
</form>

        </div>
    )
}
export default Register;