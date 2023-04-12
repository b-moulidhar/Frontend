import React,{ useState } from 'react';

function Navbar_Manager(){

    // using useState hook to set and get employee ID from local storage
  const [id,setid] = useState(window.localStorage.getItem("EId"));
  // using useState hook to set and get logout status
  const [isLoggingOut, setIsLoggingOut] = useState(false); 

  // handling logout by making a POST request to the server
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
                        <a className="nav-link" href={`/manager/${id}`}>Approval <span className="sr-only"></span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href={`/manager/employeeList/${id}`}>Employee List</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href={`/bookseat/${id}`}>Book Seat</a>
                    </li>
                    </ul>
                    <ul className="navbar-nav ml-auto">
                    <li className="nav-item" >
                        <a className="nav-link ml-auto" href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                    </li>
                    </ul>
                </div>
            </nav>
 
        </>
    )
}

export default Navbar_Manager;