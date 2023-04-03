import "./registration_approval.css"

function Registration_Approval(){
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
            <th scope="col">Approve</th>
            <th scope="col">Decline</th>
            
            </tr>
        </thead>
        <tbody>
            <tr>
            <th scope="row">1</th>
            <td>Team1</td>
            <td>team1@test.com</td>
            <td><button  type="button" className="btn btn-success manager_approve">Approve</button> </td>
            <td><button  type="button" className="btn btn-danger manager_approve">Decline</button> </td>
            </tr>
           
           
        </tbody>
        </table>
        

        </div>
    </div>
    
    )
}
export default Registration_Approval;