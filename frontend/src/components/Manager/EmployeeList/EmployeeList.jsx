import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

// This is a functional component that receives a prop called "employees"
const EmployeeList = ({ employees }) => {

  // This is a state variable called "data" initialized with an array with an empty object
  const [data, setData] = useState([{}]);

  // This is a state variable called "id" initialized with the value of the "EId" key from local storage
  const [id,setid] = useState(window.localStorage.getItem("EId"))

  // This is a state variable called "isLoggingOut" initialized with the value of "false"
  const [isLoggingOut, setIsLoggingOut] = useState(false);

  // This is an async function called "handleLogout" that performs a logout request to the server
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

  // This is a useEffect hook that is called when the component is mounted
  useEffect(() => {
    try {
      // This is a GET request to retrieve all the employees that are under the manager with the ID stored in the "id" state variable
      axios
        .get(
          `http://20.253.3.209:7001/employee/getAllEmployeesUnderTheManager/${id}`,
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid"),
            },
          }
        )
        .then((text) => {
          // This sets the "data" state variable with the data returned by the server
          setData(text.data)
        }); 
    } catch (err) {
      console.log(err);
    }
    // This empty array as the second argument means that the useEffect hook will only be called when the component is mounted, and not on subsequent renders
  }, []);

  return (
    <div>
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
         <a className="nav-link" href="#">Employee List</a>
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
 
      <h2 style={{textAlign:"center"}}>Employee Table</h2>
     
      <table className="table table-light table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
          </tr>
        </thead>
       
        <tbody>
          {data.map((item) => {
            return (
              <tr key={item.id}>
                <td>{item.e_id}</td>
                <td>{item.emp_name}</td>
                <td>{item.mail_id}</td>
                <td>{item.ph_num}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeList;


