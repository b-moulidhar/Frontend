import { useState, useEffect } from "react";
import Sidebar from "../Sidebar/sidebar";
import './manager.css';
import axios from 'axios';
import EmployeeList from "./EmployeeList/EmployeeList";
import Navbar from "../Navbar/navbar";
import { useParams } from "react-router";


function Manager(){
    const [managerEmp, setManagerEmp] = useState([])
    const [employees, setEmployees] = useState([])
    let id=useParams()

    useEffect(()=>{
        //axios.get("http://10.191.80.104:7001/seats/total")
        axios.get(`http://10.191.80.104:7001/attendanceApprovalList/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid")
            }
            })
        .then((response) => {
            setManagerEmp(response.data.data)
            console.log(response.data.data)
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
    
    // useEffect(()=>{
    //     axios.get("https://example.com/employees")
    //     .then((response) => {
    //         setEmployees(response.data)
    //     })
    //     .catch(err => console.log("Error ", err))
    // },[])

    

    return(
       
        
        <div className='manager'>
           
        <div>
        <Sidebar/>
        </div>
        <div>
        <Navbar/>

        <h2>Manager Dashboard</h2>
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
            <a href="/bookseat"><button>Book Seat</button></a>
        <EmployeeList employees={employees} />
        </div>
        
    </div>
    
    )
}
export default Manager;