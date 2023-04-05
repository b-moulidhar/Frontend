import axios from "axios"
import { useEffect, useState } from "react"
import "./registration_approval.css"

function Registration_Approval(){

    const [users,setUser] = useState([])

    useEffect(()=>{
        axios.get("http://10.191.80.73:7001/registrationApprovalList",{
            headers:{
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role":localStorage.getItem("role"),
                "X-Eid":localStorage.getItem("eid")
            }
        })
        .then((res)=>{
            setUser(res.data)
            // console.log(role)
        }).catch((err)=>{
            console.log(err)
        })
      },[])
      function approve(empid) {
        axios
          .post(
            `http://10.191.80.73:7001/registrationApproval/${empid}`,
            {},
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid"),
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            // window.location.reload();
          })
          .catch((error) => {
            console.error(error);
          });
      }

      function disapprove(empid) {
        // alert(empid);

        axios
          .post(
            `http://10.191.80.73:7001/registrationDisapproval/${empid}`,
            {},
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "X-Role": localStorage.getItem("role"),
                "X-Eid": localStorage.getItem("eid"),
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            // window.location.reload();
          })
          .catch((error) => {
            console.error(error);
          });
      }
 
    return(
        
        <div className='manager'>
        <div>
        </div>
        <div>
        <h2>Employee Approval for SMS</h2>

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
            return <tr>
            <th scope="row"value={user.emp_id} key={user.emp_id}>{user.emp_id}</th>
            <td>{user.emp_name}</td>
            <td>{user.mail_id}</td>
            <td>{user.ph_num}</td>
            {/* <td><button  type="button" className="btn btn-success manager_approve">Approve</button> </td>
            <td><button  type="button" className="btn btn-danger manager_approve">Decline</button> </td> */}
            <td> <button onClick={()=>approve(user.emp_id)} className="btn btn-success manager_approve">Approve </button></td>
            <td> <button onClick={()=>disapprove(user.emp_id)} className="btn btn-success manager_approve">Disapprove </button></td>
            </tr>

            })}
           
           
        </tbody>
        </table>
        

        </div>
    </div>
    
    )
}
export default Registration_Approval;