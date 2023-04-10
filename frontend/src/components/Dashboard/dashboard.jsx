// Importing necessary files and libraries
import "./dashboard.css";
import { useState, useEffect } from "react";
import axios from "axios";

function Dashboard() {
  
  // Setting up state variables using useState hook
  const [id,setid] = useState(window.localStorage.getItem("EId"))
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const [seatMsg, setSeatMsg] = useState("");
  const [eName, setEName] = useState("")

  // Function to handle logout
  async function handleLogout() {
    setIsLoggingOut(true);
  
    try {
      // Making a POST request to logout API with required headers
      const response = await fetch('http://20.253.3.209:7001/api/logout', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });

      // If response is OK, removing user data from local storage and redirecting to login page
      if (response.ok) {
        localStorage.removeItem('token');
        localStorage.removeItem('EId');
        localStorage.removeItem('role');
        window.location = '/';
      } else {
        throw new Error('Logout failed.');
      }
    } catch (error) {
      console.error(error);
      setIsLoggingOut(false);
    }
  }

  // Making API call to get seat notification using useEffect hook
  useEffect(()=>{
    axios.get(`http://20.253.3.209:7001/seats/notificationAboutSeat/${id}`,{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role":localStorage.getItem("role"),
          "X-Eid":localStorage.getItem("eid")
      }
  }).then(function (response) {
        // Setting the notification message in state variable
        setSeatMsg(response.data)
       console.log(response.data);
  })
  },[])  

  // Making API call to get employee profile details using useEffect hook
  useEffect(() => {
    axios.get(`http://20.253.3.209:7001/employee/profileDetailsEmployee/${id}`,{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role":localStorage.getItem("role"),
          "X-Eid":localStorage.getItem("eid")
      }
  })
  .then(response => {
        // Setting the employee name in state variable
        setEName(response.data);
        console.log(response.data);
      })
  .catch(error => {
        console.log(error);
      });
  }, []);    

  // Function to redirect to seat booking page
  function seatBook(){
    window.location="/bookseat/"+id;
  }

  // Function to redirect to view booking page
  function viewPass(){
    window.location="/viewpass/"+id;
  }

  // Rendering the dashboard 

  return (
    <div className="dashboard_container">
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
              <a className="navbar-brand" href="#">SMS</a>
              <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon" />
              </button>
              <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mr-auto">
                  <li className="nav-item active">
                    <a className="nav-link" href="#">DashBoard <span className="sr-only"></span></a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href={`/profile/${id}`}>Profile</a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href={`/atten_regularize/${id}`}>Regularization</a>
                  </li>
                </ul>
                <ul className="navbar-nav ml-auto">
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href={`/notify/${id}`}>Notification</a>
                  </li>
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                  </li>
                </ul>
              </div>
            </nav>
        
        <div className="homepage">
                <div className="dashboard_top">
                  <h3>Hi <span style={{color:'Blue', fontSize:'18px'}}>{eName.empName}</span> </h3>
                  <p>{seatMsg}</p>
                </div>
                
                <div className="dashboard">
                    <button type="button" onClick={seatBook} className="btn btn-success seat">
                      Book Seat
                    </button>
                 
                    <button type="button" onClick={viewPass} className="btn btn-success seat">
                      View Booking
                    </button>
                </div>
        </div>
      
    </div>
  );
}

export default Dashboard;