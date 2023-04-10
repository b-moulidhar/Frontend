import './notify.css';
import { useEffect, useState } from 'react';
import axios from 'axios';

function Notify(){
  const [id,setid] = useState(window.localStorage.getItem("EId"));
  const [isLoggingOut, setIsLoggingOut] = useState(false);
  const [message, setMessage] = useState('');


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
    function start(){
      const toastLiveExample = document.getElementById('liveToast')
      const toastTrigger = document.getElementById('liveToastBtn')

      if (toastTrigger) {
        const toastBootstrap = window.bootstrap.Toast.getOrCreateInstance(toastLiveExample)
        toastTrigger.addEventListener('click', () => {
          toastBootstrap.show()
        })
      }
    }


    useEffect(()=>{
      const toastTrigger = document.getElementById('liveToastBtn')
      setTimeout(() => {
        toastTrigger.click()
      }, 500);
      start()
      


    },[])
    useEffect(()=>{
      axios.get(`http://20.253.3.209:7001/seats/notificationAboutSeat/${id}`,{
        headers:{
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role":localStorage.getItem("role"),
            "X-Eid":localStorage.getItem("eid")
        }
    }).then(function (response) {
        setMessage(response.data)
         console.log(response.data);
    })
    },[])
    return(
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
              <a className="navbar-brand" href="#">SMS</a>
              <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon" />
              </button>
              
              <div className="collapse navbar-collapse" id="navbarNavDropdown">
                <ul className="navbar-nav mr-auto">
                  <li className="nav-item active">
                    <a className="nav-link" href={`/dashboard/${id}`}>DashBoard <span className="sr-only"></span></a>
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
                    <a className="nav-link ml-auto" href="#">Notification</a>
                  </li>
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                  </li>
                </ul>
              </div>
            </nav>
                <div>
                  <button  type="button" className="btn btn-primary" id="liveToastBtn" style={{display:"none"}}>Show live toast</button>
                  <div className="toast-container position-fixed center-0 end-0 p-3">
                    <div id="liveToast" className="toast" role="alert" aria-live="assertive" aria-atomic="true">
                      <div className="toast-header">
                        <img src="..." className="rounded me-2" alt="..." />
                        <strong className="me-auto">Notification</strong>
                        <small>11 mins ago</small>
                        <button type="button" className="btn-close" data-bs-dismiss="toast" aria-label="Close" />
                      </div>
                      <div className="toast-body">
                        {message}
                      </div>
                    </div>
                  </div>
                </div>

            </div>
    )
}

export default Notify;