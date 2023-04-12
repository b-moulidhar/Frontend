import './manager.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Navbar_Manager from '../navbar/navbar_manager';


function Manager(){

  // using useState hook to set and get employee ID from local storage
  const [id,setid] = useState(window.localStorage.getItem("EId")) 
  
  // using useState hook to set and get manager's employee list
  const [managerEmp, setManagerEmp] = useState([]) 
  // using useState hook to set and get logout status
  const [isLoggingOut, setIsLoggingOut] = useState(false); 

  // handling logout by making a POST request to the server
  // async function handleLogout() {
  //   setIsLoggingOut(true);
  //   try {
  //     const response = await fetch('http://20.253.3.209:7001/api/logout', {
  //       method: 'POST',
  //       headers: {
  //         'Content-Type': 'application/json',
  //         'Authorization': `Bearer ${localStorage.getItem('token')}`
  //       }
  //     });
  //     if (response.ok) {
  //       localStorage.removeItem('token');
  //       localStorage.removeItem('EId');
  //       localStorage.removeItem('role');
  //       window.location = '/';
  //     } else {
  //       throw new Error('Logout failed.');
  //     }
  //   } catch (error) {
  //     console.error(error);
  //     setIsLoggingOut(false);
  //   }
  // }

  // fetching attendance approval list using axios and setting the state
  useEffect(()=>{
    axios.get(`http://20.253.3.209:7001/attendanceApprovalList/${localStorage.getItem("EId")}`, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role": localStorage.getItem("role"),
            "X-Eid": localStorage.getItem("eid")
        }
    })
    .then((response) => {
        setManagerEmp(response.data)
        console.log(response.data[0])
    })
    .catch(err => console.log("Error ", err))
  },[id])

  // approving attendance by making a PUT request to the server
  function approve(atid){
    axios.put(`http://20.253.3.209:7001/attendanceApproval/${atid}`, {}, {
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

  // disapproving attendance by making a PUT request to the server
  function disapprove(atid){
    alert(atid)
    axios.delete(`http://20.253.3.209:7001/disapproveAttendance/${atid}`, {}, {
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "X-Role": localStorage.getItem("role"),
            "X-Eid": localStorage.getItem("eid")
        }
    })
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    });
  }
    

    return (
      <div className="manager">
        <Navbar_Manager/>

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
              {managerEmp.map((emp, idx) => (
                <tr key={idx}>
                  <th scope="row">{emp.at_id}</th>
                  <td>{emp.start_date}</td>
                  <td>{emp.end_date}</td>
                  <td>{emp.shift_start}</td>
                  <td>{emp.shift_end}</td>
                  <td>
                    {!emp.approved && (
                      <button
                        type="button"
                        className="btn btn-success manager_approve"
                        onClick={() => approve(emp.at_id)}
                      >
                        Approve
                      </button>
                    )}
                  </td>
                  <td>
                    {!emp.approved && (
                      <button
                        type="button"
                        className="btn btn-danger manager_approve"
                        onClick={() => disapprove(emp.at_id)}
                      >
                        Decline
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
}
export default Manager;

