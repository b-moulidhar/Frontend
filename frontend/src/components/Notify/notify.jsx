import './notify.css';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Navbar from '../navbar/navbar';

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
                <Navbar/>
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