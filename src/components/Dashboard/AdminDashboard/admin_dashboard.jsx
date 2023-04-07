import axios from 'axios';
import { useEffect, useState } from 'react';
import './admin_dashboard.css';

function AdminDashboard(){
    const [count,setCount] = useState(0)
    // useEffect(() => {
    //   axios.get("http://10.191.80.104:7001/seats/total").then((response) => {
    //     setCount(response.data);alert(typeof response.url);
    //   }); 
    // }, []);
    useEffect(() => {
      // Fetch data from API
      axios.get('http://10.191.80.73:7001/seats/available',{
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "X-Role": localStorage.getItem("role"),
          "X-Eid": localStorage.getItem("eid"),
        },

        responseType: "json",
      })
        .then(response => {
          // Update state with data count
          setCount(response.data.length);
          // console.log(response.data)
  // alert(response.data.length);
        })
        .catch(error => {
          console.log(error);
        });
    }, []);
    // useEffect(()=>{
    //     axios.get("http://10.191.80.104:7001/seats/total").then((res)=>{
    //             setCount({
    //                 ...count,counts:res.data.length
    //             }
    //             )
    //             console.log(res.data)
    //             alert(res.data);
    //     })
    // },[])

    function reportGen(evt){
        if(evt=="weekly"){
          console.log(evt);
        }
        else if(evt=="monthly"){
          console.log(evt);
        }
    }
    return (
      <div className="dashboard_container">
        <nav className="navbar fixed-top navbar-light bg-light justify-content-between">
          <div className="navbar-left">
            <a href="#">SMS</a>
          </div>
          <div className="navbar-right">
            <a href="#">Logout</a>
          </div>
        </nav>

      <div className="admin_mainpage">
        
        <h1 className='admin_head'>Admin Dashboard</h1>
        
        <div>
          <div className="admin_cards">
            
            <div className="card" style={{ width: "18rem" }}>
                   <div className="card-body">
                     <h5 className="card-title">Food count</h5>
                     <h6 className="card-subtitle mb-2 text-body-secondary">
                       Food count
                     </h6>
                     <p className="card-text">
                       {count.counts}
                     </p>
                   </div>
                 </div>
            
           
            <div className="card" style={{ width: "18rem" }}>
                   <div className="card-body">
                     <h5 className="card-title">Employee count</h5>
                     <h6 className="card-subtitle mb-2 text-body-secondary">
                       Employee count
                     </h6>
                     <p className="card-text">
                       {count}
                     </p>
                   </div>
                </div>
          </div>

          <div className="admin_dashboard">
                <select onChange={(event)=>reportGen(event.target.value)}>
                 <option value selected>
                   generate report
                 </option>
                 <option value="weekly">weekly</option>
                 <option value="monthly">montly</option>
               </select>
            <a href="/admin/approval">
              <button type="button" className="btn btn-primary a_approval">
                Registration approval
              </button>
            </a>
            <a href="/qrscanner">
              <button type="button" className="btn btn-primary a_approval">
                Scan Qr
              </button>
            </a>
          </div>

        </div>
      </div>
    </div>
      
      
    );
}

export default AdminDashboard;