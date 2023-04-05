import axios from "axios";
import { useEffect, useState } from "react";
import "./register.css";

function Register(){
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const [role,setRole] = useState([])
  const [user,setUser]= useState({name:'',emp_id:'',email:'',role:'',manager:'',password:'',confirmpassword:''})

  useEffect(()=>{
    axios.get("http://10.191.80.73:7001/roleNames")
    .then((res)=>{
        setRole(res.data)
        // console.log(role)
    }).catch((err)=>{
        console.log(err)
    })
  })


  const validatePassword = (password) => {
    password.preventDefault()
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return regex.test(password);
  };

  const handleSubmit = (e) => {
    e.preventDefault()
    var empName;
   var phNum;
   var mailId ;
    var  empId;
    var  pass;
    var  role;

    try {
      const response = axios.post("http://10.191.80.73:7001/saveuser", {empName , empId, phNum, mailId, pass, role })
      .then((res)=>{

      }
        )
      .catch((err)=>console.log(err));
      console.log(response.data)
     
    } catch (error) {
      if (error.response && error.response.status === 401) {
        // setErrorMessage("Invalid credentials");
        console.log(error.response);
      } else {
        // setErrorMessage("An error occurred during login");
        console.log(error.response);

      }
    }
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

    
    return(
      
        <>
            <form className="row g-3 needs-validation" onSubmit={validateSubmit} noValidate>
            
    <div className="container">
  <div className="row">
    <div className="col-lg-3 col-md-2" />
    <div className="col-lg-6 col-md-8 login-box">
      <div className="col-lg-12 login-key">
        <i className="fa fa-key" aria-hidden="true" />
      </div>
      <div className="col-lg-12 login-title">
        Register
      </div>
      <div className="col-lg-12 login-form">
        <div className="col-lg-12 login-form">
          <form>
            <div className="form-group">
              <label className="form-control-label">Name</label>
              <input type="text" className="form-control" name="name" value={user.name} onChange={newUser} required />
            </div>
            <div className="form-group">
              <label className="form-control-label">Email</label>
              <input type="email" className="form-control" value={user.email}  pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" name="email" onChange={newUser} required/>
            </div>
            <div className="form-group">
              <label className="form-control-label">Employee Id</label>
              <input type="number" placeholder="Phone number" className="form-control"  name="emp_id" pattern="(0/91)?[6-9][0-9]{9}" value={user.emp_id}  onChange={newUser} required/> 
            </div>
            <div className="form-group">
              <label className="form-control-label">Phone number</label>
              <input type="number" placeholder="Phone number" className="form-control"  name="pnumber" pattern="(0/91)?[6-9][0-9]{9}" value={user.pnumber}  onChange={newUser} required/> 
            </div>
            <div className="form-group">
            <select  className="selects form-control-label" name="specialization"  value={user.role} onChange={newUser} required >
                <option className="form-control-label" value="" disabled selected>Select Your Role</option>
                    {
                      role.map((roles)=>{
                        <option value={roles}>{roles}</option>
                      })

                    } 
                  </select>
            </div>
            <div className="form-group">
            <select  className="selects form-control-label" name="manager"  value={user.role} onChange={newUser} required >
                <option className="form-control-label" value="" disabled selected>Select Your Manager</option>
                    {
                      role.map((roles)=>{
                        <option value={roles}>{roles}</option>
                      })

                    } 
                  </select>
            </div>
            <div className="form-group">
              <label className="form-control-label">Password</label>
              <input type="password" className="form-control" name="password" pattern="[A-Za-z0-9#@$&]{3,10}"  value={user.password} onChange={handleChange} required/>
            </div>
            <div className="form-group">
              <label className="form-control-label">Confirm Password</label>
              <input type="email" className="form-control" name="confirmpassword" pattern="[A-Za-z0-9#@$&]{3,10}" value={user.confirmpassword} onChange={newUser}  required/>
            </div>
            <div className="col-lg-12 loginbttm">
              <div className="col-lg-6 login-btm login-text">
                {/* Error Message */}
              </div>
              <div className="col-lg-6 login-btm login-button">
                <button type="submit" onClick={handleSubmit} className="btn btn-outline-primary">Sign Up</button>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div className="col-lg-3 col-md-2" />
    </div>
  </div>
  </div>
</form>
</>

    )
}
export default Register;