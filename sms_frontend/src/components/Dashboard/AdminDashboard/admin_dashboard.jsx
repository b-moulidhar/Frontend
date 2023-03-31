import axios from 'axios';
import { useEffect, useState } from 'react';
import './admin_dashboard.css';

function AdminDashboard() {
  const [count, setCount] = useState({ counts: 0 });

  useEffect(() => {
    axios.get("http://10.191.80.112:7001/seatCount/2023-03-30%2010%3A55%3A27.676107").then((res) => {
      setCount({
        ...count,
        counts: res.data.data.length
      });
      console.log(res.data);
    });
  }, []);

  function reportGen(evt) {
    if (evt === "weekly") {
      console.log("weekly");
    } else if (evt === "monthly") {
      console.log("monthly");
    }
  }

  return (
    <div className="dashboard_container">
      <div className="mainpage">
        <div></div>
        <div>
          <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
              <div className="navbar-brand">
                admin dashboard
              </div>
            </div>
          </nav>
          <div className="atten">
            <div className="attenstatus">
              <div className="card" style={{ width: "18rem" }}>
                <div className="card-body">
                  <h5 className="card-title">Food count</h5>
                  <h6 className="card-subtitle mb-2 text-body-secondary">
                    Food count
                  </h6>
                  <p className="card-text">{count.counts}</p>
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
                  <p className="card-text">{count.counts}</p>
                </div>
              </div>
            </div>
          </div>
          <div className="dashboard">
            <select onChange={(event) => reportGen(event.target.value)}>
              <option value selected>
                generate report
              </option>
              <option value="weekly">weekly</option>
              <option value="monthly">monthly</option>
            </select>
            <a href="/admin/approval">
              <button type="button" className="btn btn-success seat">
                Registration Approval
              </button>
            </a>
            <br />
            <a href="/qrscanner">
              <button type="button" className="btn btn-success seat">
                scan Qr
              </button>
            </a>
          </div>
        </div>
        <div className="dashboard">
          <a href="/bookseat">
            <button type="button" className="btn btn-primary seat">
              Book Seat
            </button>
          </a>
          <a href="/viewpass">
            <button type="button" className="btn btn-success seat">
              View Booking
            </button>
          </a>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
