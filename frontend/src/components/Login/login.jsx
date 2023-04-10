import { useEffect, useState } from "react";
import "./login.css";
import axios from "axios";

// Define the Login component
export default function Login() {
  // Define state variables for the employee ID, password, and error message
  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [quote,setQuote] = useState([])

  var randomNum = Math.floor(Math.random()*100)

  // const setAuthToken = token => {
  //   if (token) {
  //     axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  //   } else {
  //     delete axios.defaults.headers.common['Authorization'];
  //   }
  // };

  // const setAuthToken = token => {
  //   if (token) {
  //     axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  //   } else {
  //     delete axios.defaults.headers.common['Authorization'];
  //   }
  // };

  useEffect(()=>{
    axios.get("https://type.fit/api/quotes").then((res)=>{
      setQuote(res.data[randomNum])
      console.log(quote)
    })
  },[])

  const handleLogin = (e) => {
    e.preventDefault();
    try {
      const res = axios.post("http://20.253.3.209:7001/api/login", { empId, pass })
      .then((res)=>{
        
        const { token, EId, role } = res.data;
        localStorage.setItem("token", token);
        localStorage.setItem("EId", EId);
        localStorage.setItem("role", role);
        console.log(token);
        // setAuthToken(tkn)
        // redirect to the home page or any other page
        if(res.data.token!=undefined){
          if(localStorage.getItem("role")==="Manager"){
            window.location="/manager/"+EId;
          }else if(localStorage.getItem("role")==="Employee"){
            window.location="/dashboard/"+EId;
          }else if(localStorage.getItem("role")==="Admin"){
            window.location="/admin/"+EId;

          }

          console.log("posted", res.data);
        })
        .catch((err) => console.log(err));
      // console.log(response.data)
    } catch (error) {
      // If the API call fails, set an error message based on the HTTP response status
      if (error.response && error.response.status === 401) {
        setErrorMessage("Invalid credentials");
        console.log(error.response);
      } else {
        setErrorMessage("An error occurred during login");
        console.log(error.response);
      }
    }
  };

  const registerPage = ()=>{
    window.location="/register"
  }
  
//  useEffect(() => {
    //   axios.get("http://10.191.80.112:7001/api/login").then((response) => {
    //     console.log(response.data)
    //   }); 
    // }, []);

// export default api;

   const [state,setState] = useState({uid:'',password:''});
   const[dig,setDig] = useState({dig:Number})

   function loginFunc(event){
    setState({
      [event.target.name]: event.target.value,
    });
    
   }

   function checkVal(event){
    setDig({
      ...dig,dig: event.target.value
    });
    console.log(dig.length)
    if(dig.length<5){
      alert("do not exceed more than 4 characters")
    }

   }
    return (
      

      <section className="h-100 gradient-form" style={{backgroundColor: '#eee'}}>
  <div className="container py-5 h-100">
    <div className="row d-flex justify-content-center align-items-center h-100">
      <div className="col-xl-10">
        <div className="card rounded-3 text-black">
          <div className="row g-0">
            <div className="col-lg-6">
              <div className="card-body p-md-5 mx-md-4">
                <div className="text-center">
                  <img src="https://left-hand-corner.000webhostapp.com/images/smslogo.png" style={{width: 80}} alt="logo" />
                  <h4 className="mt-1 mb-5 pb-1">We are The Sequre Team</h4>
                </div>
                <form>
                  <p>Please login to your account</p>
                  <div className="form-outline mb-4">
                  <input type="text" className="form-control" value={empId} id="user" required onChange={(e) => setEmpId(e.target.value)}/>
                    <label className="form-label" htmlFor="user">
                      Username
                    </label>
                  </div>
                  <div className="form-outline mb-4">
                  <input type="password" className="form-control" id="pswd" value={pass} required  onChange={(e) => setPass(e.target.value)}/>
                    <label className="form-label" htmlFor="pswd">
                      Password
                    </label>
                  </div>

                  <div className="text-center pt-1 mb-5 pb-1">
                    <button onClick={handleLogin} className="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="button">
                      Log in
                    </button>
                    <a className="text-muted" href="/forget">
                      Forgot password?
                    </a>
                  </div>
                  <div className="d-flex align-items-center justify-content-center pb-4">
                    {/* <p className="mb-0 me-2">Don't have an account?</p> */}
                    <button onClick={registerPage} type="button" className="btn btn-outline-danger">
                      Create new
                    </button>
                  </div>
                </form>
              </div>
            </div>
            <div className="col-lg-6 d-flex align-items-center gradient-custom-2">
              <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                <h4 className="mb-4">Hey, Valtechie!!!</h4>
                <p className="small mb-0 quote">
                  {quote.text}
                <p>-{quote.author}</p>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

      // </div>
    );
}
