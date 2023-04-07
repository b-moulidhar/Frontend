import "./dashboard.css";
import { useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../Navbar/navbar";

function Dashboard() {

  const [id,setid] = useState(window.localStorage.getItem("EId"))
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    async function handleLogout() {
        setIsLoggingOut(true);
      
        try {
          const response = await fetch('http://localhost:7001/api/logout', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
          });
      
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

  function seatBook(){
    console.log(id);
  window.location=`/bookseat/${id}`;
  }
  function viewPass(){
    console.log(id);
  window.location=`/viewpass/${id}`;
  }
  return (
    <div className="dashboard_container">
      {/* <Navbar/> */}
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
              <a className="navbar-brand" href="#">SMS</a>
              <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon" />
                {/* {console.log(id)} */}
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
      {/* <div className="mainpage">
              
                <Sidebar/>
      </div>       */}
     

        
        <div className="container">
       
                <div className="dashboard_head">
                  <h3>your name</h3>
                  <p>your seat for today is 1 at ground floor</p>
                </div>
                
                <div class="middle-row col-lg-12 text-center ">
                  <div class="box5 shadow">
                    <span class="numb">0</span>
                    <span class="char">Approved</span>
                  </div>
                  <div class="box6 shadow">
                    <span class="numb">0</span>
                    <span class="char">Pending</span>
                  </div>
                  <div class="box7 shadow">
                    <span class="numb">0</span>
                    <span class="char">Rejected</span>
                  </div>
                </div>
                <div className="dashboard">
                  
                    <button type="button" onClick={seatBook} className="btn btn-primary seat">
                      Book Seat
                    </button>
                  
                 
                    <button type="button" onClick={viewPass} className="btn btn-success seat">
                      View Pass
                    </button>
                  
                </div>

                {/* <div className="dashboard_bottom">
                  <div>
                    <img
                      style={{ margin: "" }}
                      src="https://png.pngtree.com/png-clipart/20210309/original/pngtree-3d-furniture-modern-office-chair-png-image_5892659.jpg"
                      width="50"
                      height="50"
                      alt=""
                    />
                  </div>
                  <div>
                    <p>date:29-03-2022</p>
                    <p>seat number:1005</p>
                    <p>shift time:09:00-18:00</p>
                  </div>
                  <div>
                    <a href="">
                      <button className="btn btn-danger">Cancel</button>
                    </a>
                  </div>
                </div> */}
        </div>
      
    </div>
  );
}

export default Dashboard;