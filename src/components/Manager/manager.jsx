import { useState, useEffect } from "react";
import Sidebar from "../Sidebar/sidebar";
import './manager.css';
import axios from 'axios';
import EmployeeList from "./EmployeeList/EmployeeList";
import Navbar from "../Navbar/navbar";


function Manager(){
    const [managerEmp, setManagerEmp] = useState([])
    const [employees, setEmployees] = useState([])
   
    useEffect(()=>{
        //axios.get("http://10.191.80.104:7001/seats/total")
        axios.get("https://reqres.in/api/users")
        .then((response) => {
            setManagerEmp(response.data.data)
            console.log(response.data.data)
        })
        .catch(err => console.log("Error ", err))
    },[])

    const handleApprove = (emp) => {
        // Send the data to the backend using axios.post
        axios.post("http://10.191.80.104:7001/attendanceApproval", emp)
            .then(response => {
                // Update the state to reflect the approved employee
                setManagerEmp(prevEmps => prevEmps.map(prevEmp => {
                    if (prevEmp.id === emp.id) {
                        return {
                            ...prevEmp,
                            approved: true
                        };
                    } else {
                        return prevEmp;
                    }
                }));
            })
            .catch(err => console.log("Error ", err))
    };

    const handleDecline = (emp) => {
        // Remove the employee from the state
        setManagerEmp(prevEmps => prevEmps.filter(prevEmp => prevEmp.id !== emp.id));
    };
    
    useEffect(()=>{
        axios.get("https://example.com/employees")
        .then((response) => {
            setEmployees(response.data)
        })
        .catch(err => console.log("Error ", err))
    },[])

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
            <th scope="col">Employee ID</th>
            <th scope="col">Name</th>
            <th scope="col">Start Date</th>
            <th scope="col">End Date</th>
            <th scope="col">Shift Start</th>
            <th scope="col">Shift End</th>
            <th scope="col">Approve</th>
            <th scope="col">Decline</th>
            
            </tr>
        </thead>
        <tbody>
            {managerEmp.map((emp,idx)=>(
                <tr key={idx}>
                    <th scope="row">{idx+1}</th>
                    <td>{emp.first_name}</td>
                    <td>{emp.last_name}</td>
                    <td>{emp.avatar}</td>
                    <td>{emp.email}</td>
                    <td>{emp.shiftEnd}</td>
                    <td>
                        {!emp.approved && (
                            <button 
                                type="button" 
                                className="btn btn-success manager_approve" 
                                onClick={() => handleApprove(emp)}>
                                    Approve
                                </button>
                        )}
                    </td>
                    <td>
                        {!emp.approved && (
                            <button 
                                type="button" 
                                className="btn btn-danger manager_approve" 
                                onClick={() => handleDecline(emp)}>
                                    Decline
                            </button>
                        )}
                    </td>
                </tr>
            ))}
        </tbody>
        </table>
            <a href="/bookseat"><button>Book Seat</button></a>
        {/* <EmployeeList employees={employees} /> */}
        <EmployeeList/>
        </div>
        
    </div>
    
    )
}
export default Manager;