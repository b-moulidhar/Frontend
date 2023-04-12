import axios from "axios"
import { useEffect, useState } from "react"
import "./registration_approval.css"
import Navbar_Admin from "../navbar/navbar_admin";

function Registration_Approval(){

    const [id,setid] = useState(window.localStorage.getItem("EId"))
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    const [users,setUser] = useState([]);

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


    useEffect(()=>{
        axios.get("http://20.253.3.209:7001/registrationApprovalList",{
            headers:{
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role":localStorage.getItem("role"),
                "X-Eid":localStorage.getItem("eid")
            }
        })
        .then((res)=>{
            setUser(res.data)
            console.log(res.data)
        }).catch((err)=>{
            console.log(err)
        })
    },[])

function approve(empid){
    axios.put(`http://20.253.3.209:7001/registrationApproval/${empid}`, {}, {
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
function disapprove(empid){
    alert(empid)
    axios.delete(`http://20.253.3.209:7001/registrationDisapproval/${empid}`, {}, {
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
        
        <div className='reges_approval'>
             <Navbar_Admin/>
            <div>
                <h2 style={{marginTop:"4rem"}}>Employee Approval for SMS</h2>
                <table className="table1">
                    <thead>
                        <tr>
                            <th scope="col">Employee ID</th>
                            <th scope="col">Employee Name</th>
                            <th scope="col">Employee Email</th>
                            <th scope="col">Employee Phone Number</th>
                            <th scope="col">Approve</th>
                            <th scope="col">Decline</th>   
                        </tr>
                    </thead>
                    <tbody>
                        {users.map((user)=>{
                            return (
                                <tr key={user.emp_id}>
                                    <th scope="row" value={user.emp_id}>{user.emp_id}</th>
                                    <td>{user.emp_name}</td>
                                    <td>{user.mail_id}</td>
                                    <td>{user.ph_num}</td>
                                    <td>
                                        <button onClick={()=>approve(user.emp_id)} className="btn btn-success manager_approve">Approve </button>
                                    </td>
                                    <td>
                        <button type="button" onClick={() => disapprove(user.emp_id)} className="btn btn-danger manager_approve">
                            Disapprove
                        </button>
</td>

                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    )
}
export default Registration_Approval;
