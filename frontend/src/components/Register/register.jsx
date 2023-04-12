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
    axios.get("http://20.253.3.209:7001/roleNames")
    .then((res)=>{
        setRole(res.data);
        console.log(res.data)
      }).catch((err)=>{ 
        console.log(err)
      })
    axios.get("http://20.253.3.209:7001/gettingAllManagernames")
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
    
      const response = axios.post("http://20.253.3.209:7001/saveuser", {empName , phNum, mailId, empId, pass, role, managerName })
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
     const confirmPass=()=>{

      if(user.password===user.confirmpassword){
        alert("passwords match");
      }else{
        alert("passwords did not match");
      }

     }

    
    return (
      <>
        {/* <form className="row g-3 needs-validation" onSubmit={validateSubmit} noValidate> */}
        <section className="vh-100 bg-image" style={{backgroundImage: 'url("https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp")'}}>
  <div className="mask d-flex align-items-center h-100 gradient-custom-3">
    <div className="container h-100">
      <div className="row d-flex justify-content-center align-items-center h-100">
        <div className="col-12 col-md-9 col-lg-7 col-xl-6">
          <div className="card" style={{borderRadius: 15}}>
            <div className="card-body p-5">
              <h2 className="text-uppercase text-center mb-5">
                Create an account
              </h2>
              <form>
                <div className="form-outline mb-4">
                <input type="text" className="form-control form-control-lg" placeholder="Name" name="name" id="name" value={user.name} onChange={(e)=>setUser({...user,name:e.target.value})} required />
                  <label className="form-label" htmlFor="name">
                    Your Name
                  </label>
                </div>
                <div className="form-outline mb-4">
                <input type="email" className="form-control form-control-lg" placeholder="Email" id="email" value={user.email}  pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" name="email" onChange={(e)=>setUser({...user,email:e.target.value})} required/>  
                <label className="form-label" htmlFor="email">
                    Your Email
                  </label>
                </div>
                <div className="form-outline mb-4">
                <input type="number" placeholder="Employee Id" id="emp_id" className="form-control form-control-lg"  name="emp_id" pattern="(0/91)?[6-9][0-9]{9}" value={user.emp_id}  onChange={(e)=>setUser({...user,emp_id:e.target.value})} required/>
                <label className="form-label" htmlFor="empid">
                    Employee Id
                  </label>
                </div>
                <div className="form-outline mb-4">
                <input type="number" placeholder="Phone number" id="phnum" className="form-control"  name="pnumber" pattern="(0/91)?[6-9][0-9]{9}" value={user.pnumber}  onChange={(e)=>setUser({...user,pnumber:e.target.value})} required/>
                  <label className="form-label" htmlFor="phnum">
                    Phone Number
                  </label>
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label" htmlFor="role">
                    Select your role
                  </label><br />
                  <select id="role" className="selects form-control-label" name="role"  value={user.role} onChange={(e)=>setUser({...user,role:e.target.value})} required >
                <option className="form-control-label" value="" disabled selected>Select Your Role</option>
                    {
                      //  console.log( role)
                      role.map((roles,idx)=>{
                        return <option key={idx} value={roles}>{roles}</option>
                      })

                    } 
                  </select>
                </div>
                <div className="form-outline mb-4">
                  <label className="form-label" htmlFor="mngr">
                    Select your Manager
                  </label><br />
                  <select id="mngr" className="selects form-control-label" name="manager"  value={user.manager} onChange={(e)=>setUser({...user,manager:e.target.value})} required >
                  <option className="form-control-label" value="" disabled selected>Select Your Manager</option>
                    {
                        // console.log( manager)
                      manager.map((managers,idx)=>{
                        return <option key={idx} value={managers}>{managers}</option>
                      })

                    } 
                  </select>
                </div>
                <div className="form-outline mb-4">
                <input type="password"  className="form-control" name="password" pattern="[A-Za-z0-9#@$&]{3,10}"  value={user.password} onInput={(e)=>setUser({...user,password:e.target.value})} onBlur={handleChange} required/>
                 <label className="form-label" htmlFor="pswd">
                    Password
                  </label>
                </div>
                <div className="form-outline mb-4">
                <input type="password" id="cnfpswd" className="form-control" name="confirmpassword" pattern="[A-Za-z0-9#@$&]{3,10}" value={user.confirmpassword} onChange={(e)=>setUser({...user,confirmpassword:e.target.value})} onBlur={confirmPass}  required/>                  
                <label className="form-label" htmlFor="cnfpswd">
                    Confirm Password
                  </label>
                </div>
                {/* <div className="form-check d-flex justify-content-center mb-5">
                  <input className="form-check-input me-2" type="checkbox" defaultValue id="form2Example3cg" />
                  <label className="form-check-label" htmlFor="form2Example3g">
                    I agree all statements in{'{'}" "{'}'}
                    <a href="#!" className="text-body">
                      <u>Terms of service</u>
                    </a>
                  </label>
                </div> */}
                <div className="d-flex justify-content-center">
                  <button type="button" onClick={handleSubmit} className="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                    Register
                  </button>
                </div>
                <p className="text-center text-muted mt-5 mb-0">
                  Have already an account?
                  <a href="/" className="fw-bold text-body">
                    <u>Login here</u>
                  </a>
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

      </>
    );
}
export default Register;