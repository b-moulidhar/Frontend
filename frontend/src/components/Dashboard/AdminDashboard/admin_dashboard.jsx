// Importing necessary files and libraries
import axios from 'axios';
import { useEffect, useState } from 'react';
import './admin_dashboard.css';

function AdminDashboard(){

  // state variables
  const [id,setid] = useState(window.localStorage.getItem("EId"))
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const [count,setCount] = useState(0)
  const [foodCount,setFoodCount] = useState(0)

  // get the current date in the format 'YYYY-MM-DD'
  const dateObj = new Date();
  const year = dateObj.getFullYear();
  const month = dateObj.getMonth() + 1;
  const day = dateObj.getDate();
  const currentDate = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;

  // logout function
  async function handleLogout() {
    setIsLoggingOut(true);

    try {
      // make a POST request to the logout endpoint
      const response = await fetch('http://20.253.3.209:7001/api/logout', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
        }
      });

      if (response.ok) {
        // remove user information from local storage and redirect to login page
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

  // fetch the count of seats and update the state variable 'count' using useEffect
  useEffect(() => {
    axios.get(`http://20.253.3.209:7001/seatCount/${currentDate}`,{
      headers:{
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "X-Role":localStorage.getItem("role"),
        "X-Eid":localStorage.getItem("eid")
      }
    }).then((response) => {  
      setCount(response.data);
    })
  }, []);

  // fetch the count of food and update the state variable 'foodCount' using useEffect
  useEffect(() => {
    axios.get(`http://20.253.3.209:7001/foodCount/${currentDate}`,{
      headers:{
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        "X-Role":localStorage.getItem("role"),
        "X-Eid":localStorage.getItem("eid")
      }
    }).then((response) => {
      setFoodCount(response.data);
    }).catch(error => {
      console.log(error);
    });
  }, []);

  // function to handle button click for generating weekly or monthly reports
  function reportGen(evt){
    if(evt=="weekly"){
      console.log(evt);
    }
    else if(evt=="monthly"){
      console.log(evt);
    }
  }

  // function to redirect to the report page
  const reportDirect = () => {
    window.location='/report';
  }

    return (
      <div className="dashboard_container">
         <nav className="navbar fixed-top navbar-light bg-light justify-content-between">
          <div className="navbar-left">
            <a href="#">SMS</a>
          </div>
          <div className="navbar-right">
            <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
          </div>
        </nav>

      <div className="admin_mainpage">
        
        <h1 className='admin_head'>Admin Dashboard</h1>
        
        <div>
          <div className="admin_cards">
            
            <div className="card" style={{ width: "18rem" }}>
                   <div className="card-body">
                     <h5 className="card-title">Food count</h5>
                     <h6 className="card-subtitle mb-2 text-body-secondary">
                       Food count
                     </h6>
                     <p className="card-text">
                       {foodCount}
                     </p>
                   </div>
                 </div>
            
           
            <div className="card" style={{ width: "18rem" }}>
                   <div className="card-body">
                     <h5 className="card-title">Employee count</h5>
                     <h6 className="card-subtitle mb-2 text-body-secondary">
                       Employee count
                     </h6>
                     <p className="card-text">
                       {count}
                     </p>
                   </div>
                </div>
          </div>

          <div className="admin_dashboard">
                <button className="btn btn-primary" onClick={reportDirect}>Generate Report</button>
            <a href="/admin/approval">
              <button type="button" className="btn btn-primary a_approval">
                Registration approval
              </button>
            </a>
            <a href="/qrscanner">
              <button type="button" className="btn btn-primary a_approval">
                Scan Qr
              </button>
            </a>
          </div>

        </div>
      </div>
    </div>
      
      
    );
}

export default AdminDashboard;