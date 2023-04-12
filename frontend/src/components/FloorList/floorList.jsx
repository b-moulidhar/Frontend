import React, { useState } from 'react';
import './floorList.css';

import { useParams } from 'react-router-dom';
import Navbar_Admin from '../navbar/navbar_admin';

function FloorList(){

    // We are using useState hook to set and get the value of id.
    // It is initially set to the value of EId from local storage.
    const [id,setid] = useState(window.localStorage.getItem("EId"))

    // isLoggingOut state is used to disable logout button while the user logs out.
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    // handleLogout function is used to logout the user and remove the tokens and user information from local storage.
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

    // Four functions are used to redirect the user to different floors based on their selection.
    const gfloorFunc=()=>{
        window.location=`/gfloor/${id}`
    }
    const ffloorFunc=()=>{
        window.location=`ffloor/${id}`
    }
    const sfloorFunc=()=>{
        window.location=`/sfloor/${id}`
    }
    const tfloorFunc=()=>{
        window.location=`/tfloor/${id}`
    }

    return(
       
        <div className='FloorList_container'> 
         <Navbar_Admin/>
            
        <div className='fList'>
            <h3>Select Floor</h3>
            <div className="floor0">
                
                <a ><button onClick={gfloorFunc} className='floorbtn'>Ground Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/ffloor'><button onClick={ffloorFunc} className='floorbtn'>First Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/sfloor'><button onClick={sfloorFunc} className='floorbtn'>Second Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/tfloor'><button onClick={tfloorFunc} className='floorbtn'>Third Floor</button></a>
            </div>
        </div>
        </div>
        
    )
}
export default FloorList;