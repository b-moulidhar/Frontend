import Sidebar from "../Sidebar/sidebar";
import './manager.css';
import EmployeeList from "./EmployeeList/EmployeeList";
import axios from 'axios';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '../Navbar/navbar'


function Manager(){
  const [id,setid] = useState(window.localStorage.getItem("EId"))

     const [managerEmp, setManagerEmp] = useState([])
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
    // let id=useParams()  

    useEffect(()=>{
        //axios.get("http://10.191.80.104:7001/seats/total")
        axios.get(`http://10.191.80.104:7001/attendanceApprovalList/${id.id}`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid")
            }
            })
        .then((response) => {
            // setManagerEmp(response.data)
            console.log(response.data)
        })
        .catch(err => console.log("Error ", err))
    },[])

    function approve(atid){
        axios.put(`http://10.191.80.104:7001/attendanceApproval/${atid}`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid")
            }
        })
        .then(response => {
            console.log(response.data);
            // window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
    }
    function disapprove(atid){
        alert(atid)
        axios.put(`http://10.191.80.104:7001/disapproveAttendance/${atid}`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid")
            }
        })
        .then(response => {
            console.log(response.data);
            // window.location.reload();
        })
        .catch(error => {
            console.error(error);
        });
    }
    

    return(
       
        
        <div className='manager'>
           
        {/* <div>
        <Sidebar/>
        </div> */}
        <div>

        {/* <Navbar/> */}
  <nav className="navbar navbar-expand-lg navbar-light bg-light">
   
  <a className="navbar-brand" href="#">SMS</a>
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon" />
  </button>
  <div className="collapse navbar-collapse" id="navbarNavDropdown">
    <ul className="navbar-nav mr-auto">
      <li className="nav-item active">
        <a className="nav-link" href="#">Approval <span className="sr-only"></span></a>
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



        <h2>Manager Dashboard</h2>
        <div>
            <table className="table1">
        <thead>
            <tr>
            <th scope="col">Attendence ID</th>
            <th scope="col">Start Date</th>
            <th scope="col">End Date</th>
            <th scope="col">Shift Start</th>
            <th scope="col">Shift End</th>
            <th scope="col">Approve</th>
            <th scope="col">Decline</th>
            
            </tr>
        </thead>
        <tbody>
            {/* <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>12</td>
            <td>22</td>
            <td>09:00am</td>
            <td>18:00pm</td>
        </tr>    */}
            {managerEmp.map((emp,idx)=>(
                <tr key={idx}>
                    <th scope="row">{emp.atid}</th>
                    <td>{emp.startdate}</td>
                    <td>{emp.endDate}</td>
                    <td>{emp.shiftStart}</td>
                    <td>{emp.shiftEnd}</td>
                    <td>
                        {!emp.approved && (
                            <button 
                                type="button" 
                                className="btn btn-success manager_approve" 
                                onClick={() => approve(emp.atid)}>
                                    Approve
                                </button>
                        )}
                    </td>
                    <td>
                        {!emp.approved && (
                            <button 
                                type="button" 
                                className="btn btn-danger manager_approve" 
                                onClick={() => disapprove(emp.atid)}>
                                    Decline
                            </button>
                        )}
                    </td>
                </tr>
            ))}
        </tbody>
        </table>
        </div>
        
            {/* <a href="/bookseat/:id"><button>Book Seat</button></a>
            <a href="/manager/manager_approval/"><button>Approve Attendance</button></a>
            <a href="/manager/employeeList"><button>Employee List</button></a>
         */}
        </div>
        
    </div>
    
    )
}
export default Manager;