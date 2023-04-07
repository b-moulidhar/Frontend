
import { useParams } from 'react-router-dom';
import './navbar.css';
import { useState } from 'react';

function Navbar(){

    const id = useParams()
    const [isLoggingOut, setIsLoggingOut] = useState(false);
    const profile = `/profile/${id}`

    async function handleLogout() {
        setIsLoggingOut(true);
      
        try {
          const response = await fetch('/api/logout', {
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
       
    return(
        <div>
        <div>
           
           {/* <nav>
                <div class="navbar-left">
                    <a href="#">SMS</a>
                </div>
                <div class="navbar-right">
                    <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                </div>
            </nav> */}

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
                    <a className="nav-link" href={profile}>Profile</a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="/atten_regularize/:id">Regularization</a>
                  </li>
                </ul>
                <ul className="navbar-nav ml-auto">
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="/notify">Notification</a>
                  </li>
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                  </li>
                </ul>
              </div>
            </nav>

        </div></div>
    )
}

export default Navbar;