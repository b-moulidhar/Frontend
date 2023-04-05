
import './navbar.css';
import { useState } from 'react';
import axios from 'axios';

function Navbar(){
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    // function handleLogout() {
    //     // Make a request to the backend to invalidate the token
    //     // and remove it from the client-side storage
    //     setIsLoggingOut(true);
    //     localStorage.removeItem("token");
    //     localStorage.removeItem("EId");
    //     localStorage.removeItem("role");
    //     // Redirect to the initial page
    //    window.location="/"
    //   }

    async function handleLogout() {
        setIsLoggingOut(true);
      
        try {
          const response = await axios.post('/api/logout', null, {
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
          });
      
          if (response.status === 200) {
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
    
    return(
        <div>
        <div>
           
           <nav>
                <div class="navbar-left">
                    <a href="#">SMS</a>
                </div>
                <div class="navbar-right">
                    <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                </div>
            </nav>

        </div></div>
    )
}

export default Navbar;
