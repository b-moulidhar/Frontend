import axios from "axios";
import { useEffect, useState } from "react";
import "./register.css";

function Register(){
  const ex = ["emp1","emp2","emp3"]
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  let [role,setRole] = useState([])
  let [manager,setManager] = useState([])
  const [user,setUser]= useState({name:'',emp_id:Number,email:'',pnumber:'',role:'',manager:'',password:'',confirmpassword:''})

  useEffect(()=>{
    axios.get("http://10.191.80.73:7001/roleNames")
    .then((res)=>{
        setRole(res.data);
        console.log(res.data)
      }).catch((err)=>{ 
        console.log(err)
      })
    axios.get("http://10.191.80.73:7001/gettingAllManagernames")
    .then((res)=>{
      setManager(res.data);
      }).catch((err)=>{ 
        console.log(err)
      })
    },[])
    
   

  const validatePassword = (password) => {
    // password.preventDefault()
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
    return regex.test(password);
  };

  function handleSubmit(e) {
    e.preventDefault()
    
    var empName= user.name;
   var phNum = user.pnumber;
   var mailId = user.email;
    var  empId=user.emp_id;
    var  pass=user.password;
    var  role=user.role;
    var managerName = user.manager
     console.log(user);
    
      const response = axios.post("http://10.191.80.73:7001/saveuser", {empName , phNum, mailId, empId, pass, role, managerName })
      response.then((res)=>{
          // console.log(res.status);
          if(res.status===200){
            alert("registered succesfully");
            window.location="/"
          }
      }
        )
      .catch((err)=>console.log(err));
      console.log(response.data)
      // var data = {
      //   empName:user.name,
      //   phNum:user.pnumber,
      //   mailId:user.email,
      //   empId:Number(user.emp_id),
      //   pass:user.password,
      //   role:user.role,
      //   managerName:user.manager
      //  }

      // fetch("")
     
  
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


    // function newUser(event){
    //     setUser({
    //       [event.target.name]:event.target.value
    //     })
    // }
    function validateSubmit(event){
      event.preventDefault()
      console.log("validated")

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
            {/* <form className="row g-3 needs-validation" onSubmit={validateSubmit} noValidate> */}
            <form className="row g-3 needs-validation" method="post">
            
    <div className="container">
  <div className="col-8 col-md-10 col-sm-8 col-xl-12 col-lg-12 row">
    <div className="col-lg-3 col-md-2" />
    <div className="col-lg-6 col-md-8 login-box">
      <div className="col-lg-12 login-key">
        <i className="fa fa-key" aria-hidden="true" />
      </div>
      <div className="col-lg-12 login-title">
        Register
      </div>
      <div className="col-xl-12 col-lg-12 col-8 col-md-10 col-sm-8 login-form">
        <div className="col-8 col-md-10 col-sm-8 col-xl-12 col-lg-12 login-form">
          <form>
            <div className="form-group">
              <label className="form-control-label">Name</label>
              <input type="text" className="form-control" name="name" value={user.name} onChange={(e)=>setUser({...user,name:e.target.value})} required />
            </div>
            <div className="form-group">
              <label className="form-control-label">Email</label>
              <input type="email" className="form-control" value={user.email}  pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" name="email" onChange={(e)=>setUser({...user,email:e.target.value})} required/>
            </div>
            <div className="form-group">
              <label className="form-control-label">Employee Id</label>
              <input type="number" placeholder="Phone number" className="form-control"  name="emp_id" pattern="(0/91)?[6-9][0-9]{9}" value={user.emp_id}  onChange={(e)=>setUser({...user,emp_id:e.target.value})} required/> 
            </div>
            <div className="form-group">
              <label className="form-control-label">Phone number</label>
              <input type="number" placeholder="Phone number" className="form-control"  name="pnumber" pattern="(0/91)?[6-9][0-9]{9}" value={user.pnumber}  onChange={(e)=>setUser({...user,pnumber:e.target.value})} required/> 
            </div>
            <div className="form-group">
            <select  className="selects form-control-label" name="role"  value={user.role} onChange={(e)=>setUser({...user,role:e.target.value})} required >
                <option className="form-control-label" value="" disabled selected>Select Your Role</option>
                    {
                      //  console.log( role)
                      role.map((roles,idx)=>{
                        return <option key={idx} value={roles}>{roles}</option>
                      })

                    } 
                  </select>
            </div>
            <div className="form-group">
            <select  className="selects form-control-label" name="manager"  value={user.manager} onChange={(e)=>setUser({...user,manager:e.target.value})} required >
                <option className="form-control-label" value="" disabled selected>Select Your Manager</option>
                    {
                        // console.log( manager)
                      manager.map((managers,idx)=>{
                        return <option key={idx} value={managers}>{managers}</option>
                      })

                    } 
                  </select>
            </div>
            <div className="form-group">
              <label className="form-control-label">Password</label>
              <input type="password" className="form-control" name="password" pattern="[A-Za-z0-9#@$&]{3,10}"  value={user.password} onInput={(e)=>setUser({...user,password:e.target.value})} onChange={handleChange} required/>
            </div>
            <div className="form-group">
              <label className="form-control-label">Confirm Password</label>
              <input type="password" className="form-control" name="confirmpassword" pattern="[A-Za-z0-9#@$&]{3,10}" value={user.confirmpassword} onChange={(e)=>setUser({...user,confirmpassword:e.target.value})}  required/>
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