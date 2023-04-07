import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './profile.css';
import { useParams } from 'react-router-dom';
import Navbar from '../Navbar/navbar';

function Profile() {

  const [id,setid] = useState(window.localStorage.getItem("EId"))
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    async function handleLogout() {
        setIsLoggingOut(true);
      
        try {
          const response = await fetch('http://20.253.3.209:7001/api/logout', {
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
  window.location="/bookseat/"+id;
  }

  const [userData, setUserData] = useState({});
  // const eId = useParams();
  useEffect(() => {
    axios.get(`http://20.253.3.209:7001/employee/profileDetailsEmployee/${id}`,{
      headers:{
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role":localStorage.getItem("role"),
          "X-Eid":localStorage.getItem("eid")
      }
  })
  .then(response => {
        setUserData(response.data);
        console.log(response.data);
      })
  .catch(error => {
        console.log(error);
      });
  }, []);

  // useEffect(() => {
  //   async function fetchData() {
  //     try {
  //       const response = await axios.get(`http://10.191.80.102:7001/employee/profileDetailsEmployee/${eId}`);
  //       setUserData(response.data);
  //       console.log(response.data);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   }
  //   fetchData();
  // }, [eId]);

  return (
    <div>
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
                    <a className="nav-link" href={`/dashboard/${id}`}>DashBoard <span className="sr-only"></span></a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="#">Profile</a>
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

      <section className="vh-100" style={{backgroundColor: '#f4f5f7'}}>
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col col-lg-6 mb-4 mb-lg-0">
              <div className="card mb-3" style={{borderRadius: '.5rem'}}>
                <div className="row g-0">
                  <div className="col-md-4 text-center gradient-custom text-white" style={{borderTopLeftRadius: '.5rem', borderBottomLeftRadius: '.5rem'}}>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp" alt="Avatar" className="img-fluid my-5" style={{width: 80}} />
                    <h5>User</h5>
                    <h5>{userData.empName}</h5>
                    {/* <p>{userData.jobTitle}</p> */}
                    <i className="far fa-edit mb-5" />
                  </div>
                  <div className="col-md-8">
                    <div className="card-body p-4">
                      <h6>Information</h6>
                      <hr className="mt-0 mb-4" />
                      <div className="row pt-1">
                        <div className="col-6 mb-3">
                          <h6>Email</h6>
                          <p className="text-muted">{userData.mailId}</p>
                        </div>
                        <div className="col-6 mb-3">
                          <h6>Phone</h6>
                          <p className="text-muted">{userData.phNum}</p>
                        </div>
                      </div>
                      {/* <h6>Projects</h6> */}
                      <hr className="mt-0 mb-4" />
                      <div className="row pt-1">
                        <div className="col-6 mb-3">
                          {/* <h6>Recent</h6> */}
                          {/* <p className="text-muted">{userData.recentProject}</p> */}
                        </div>
                        <div className="col-6 mb-3">
                          {/* <h6>Most Viewed</h6> */}
                          {/* <p className="text-muted">{userData.mostViewedProject}</p> */}
                        </div>
                      </div>
                      {/* <div className="d-flex justify-content-start">
                        <a href={userData.facebookLink}><i className="fab fa-facebook-f fa-lg me-3" /></a>
                        <a href={userData.twitterLink}><i className="fab fa-twitter fa-lg me-3" /></a>
                        <a href={userData.instagramLink}><i className="fab fa-instagram fa-lg" /></a>
                      </div> */}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Profile;
