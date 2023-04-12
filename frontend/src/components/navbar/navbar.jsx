import React, { useState } from 'react';

function Navbar(){

    const [id,setid] = useState(window.localStorage.getItem("EId"))
    const [isLoggingOut, setIsLoggingOut] = useState(false);

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

    return(
        <>
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
                    <a className="nav-link ml-auto" href={`/notify/${id}`}>Notification</a>
                  </li>
                  <li className="nav-item" >
                    <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                  </li>
                </ul>
              </div>
            </nav>
        </>
    )
}

export default Navbar;