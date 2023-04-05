import axios from 'axios';
import { useEffect, useState } from 'react';
import './admin_dashboard.css';


function AdminDashboard(){
    const [count,setCount] = useState(0)
    const [foodCount,setFoodCount] = useState(0)
    // const currentDate = new Date().toLocaleDateString();
    const dateObj = new Date();
    const year = dateObj.getFullYear();
    const month = dateObj.getMonth() + 1;
    const day = dateObj.getDate();
  
    const currentDate = `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
    
    // const [foodcount,setFoodCount]=useState(0)
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
        }, []);
    

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
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
              <div className="container-fluid">
                <a className="navbar-brand" href="#">
                   Navbar
                 </a>
                <div
                  className="collapse navbar-collapse"
                  id="navbarSupportedContent"
                >
                  <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">ADMIN DASHBOARD</li>
                  </ul>
                </div>
              </div>
            </nav>
      <div className="mainpage">
        <div>
        </div>
        <div>
          {/* <h2 className="status">Status</h2> */}
          {/* <div className="dashboard_head">
            <h3>your name</h3>
            <p>your seat for today is 1 at ground floor</p>
          </div> */}
          <div className=" atten">
            <div className="attenstatus">
            <div className="card" style={{ width: "18rem" }}>
                   <div className="card-body">
                     <h5 className="card-title">Food count</h5>
                     <h6 className="card-subtitle mb-2 text-body-secondary">
                       Food count
                     </h6>
                     <p className="card-text">
                       {foodCount}
                     </p>
                   </div>
                 </div>
            </div>
            <div className="attenstatus">
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
          </div>

          <div className="dashboard">
                <select onChange={(event)=>reportGen(event.target.value)}>
                 <option value selected>
                   generate report
                 </option>
                 <option value="weekly">weekly</option>
                 <option value="monthly">montly</option>
               </select>
            <a href="/admin/approval">
              <button type="button" className="btn btn-primary seat">
                Registration approval
              </button>
            </a>
            <a href="/qrscanner">
              <button type="button" className="btn btn-success seat">
                Scan Qr
              </button>
            </a>
          </div>

          {/* <div className="dashboard_bottom">
            <div>
              <img
                style={{ margin: "" }}
                src="https://png.pngtree.com/png-clipart/20210309/original/pngtree-3d-furniture-modern-office-chair-png-image_5892659.jpg"
                width="50"
                height="50"
                alt=""
              />
            </div>
            <div>
              <p>date:29-03-2022</p>
              <p>seat number:1005</p>
              <p>shift time:09:00-18:00</p>
            </div>
            <div>
              <a href="">
                <button className="btn btn-danger">Cancel</button>
              </a>
            </div>
          </div> */}
        </div>
      </div>
    </div>
      // <div classname="container admin_body">
      //   <div classname="mainpage">
      //     <div classname="admin_container">
      //       <nav className="navbar navbar-expand-lg bg-body-tertiary">
      //         <div className="container-fluid">
      //           <a className="navbar-brand" href="#">
      //             Navbar
      //           </a>
      //           <button
      //             className="navbar-toggler"
      //             type="button"
      //             data-bs-toggle="collapse"
      //             data-bs-target="#navbarSupportedContent"
      //             aria-controls="navbarSupportedContent"
      //             aria-expanded="false"
      //             aria-label="Toggle navigation"
      //           >
      //             <span className="navbar-toggler-icon" />
      //           </button>
      //           <div
      //             className="collapse navbar-collapse"
      //             id="navbarSupportedContent"
      //           >
      //             <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      //               <li className="nav-item">ADMIN DASHBOARD</li>
      //             </ul>
      //           </div>
      //         </div>
      //       </nav>
      //       <div classname="admin">
      //         <div classname="total_count">
      //           <div className="card" style={{ width: "18rem" }}>
      //             <div className="card-body">
      //               <h5 className="card-title">Food count</h5>
      //               <h6 className="card-subtitle mb-2 text-body-secondary">
      //                 Food count
      //               </h6>
      //               <p className="card-text">
      //                 {count.counts}
      //               </p>
      //             </div>
      //           </div>

      //           <div className="card" style={{ width: "18rem" }}>
      //             <div className="card-body">
      //               <h5 className="card-title">Employee count</h5>
      //               <h6 className="card-subtitle mb-2 text-body-secondary">
      //                 Employee count
      //               </h6>
      //               <p className="card-text">
      //                 {count.counts}
      //               </p>
      //             </div>
      //           </div>
      //         </div>
      //         <select name id>
      //           <option value selected>
      //             generate report
      //           </option>
      //           <option value>weekly</option>
      //           <option value>monthly</option>
      //         </select>
      //         <div>registration approval</div>
      //         <div>scan QR</div>
      //       </div>
      //     </div>
      //   </div>
      // </div>
      
    );
}

export default AdminDashboard;