import React, { useState } from "react";
import axios from "axios";
import "./report.css";
// import "./App.css";

function Report() {
  const [id, setid] = useState(window.localStorage.getItem("EId"));
  const [isLoggingOut, setIsLoggingOut] = useState(false);

  const [reportOption, setReportOption] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [employeeId, setEmployeeId] = useState("");
  const [stId, setStId] = useState("");
  const [sDate, setSDate] = useState("");
  const [eDate, setEDate] = useState("");

  async function handleLogout() {
    setIsLoggingOut(true);

    try {
      const response = await fetch("http://20.253.3.209:7001/api/logout", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });

      if (response.ok) {
        localStorage.removeItem("token");
        localStorage.removeItem("EId");
        localStorage.removeItem("role");
        window.location = "/";
      } else {
        throw new Error("Logout failed.");
      }
    } catch (error) {
      console.error(error);
      setIsLoggingOut(false);
    }
  }

  // Define the options with associated values
  const shiftOptions = [
    { label: "-- Select Shift ID --", value: "" },
    { label: "Shift 1", value: "1" },
    { label: "Shift 2", value: "2" },
    { label: "Shift 3", value: "3" },
  ];

  const handleReportOptionChange = (event) => {
    setReportOption(event.target.value);
  };

  const formatDate = (date) => {
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hours = "00";
    const minutes = "00";
    const seconds = "00";
    return `${year}-${month < 10 ? "0" + month : month}-${
      day < 10 ? "0" + day : day
    } ${hours}:${minutes}:${seconds}`;
  };

  const handleStartDateChange = (event) => {
    const selectedDate = new Date(event.target.value);
    const formattedDate = formatDate(selectedDate);
    setSDate(formattedDate);
    setStartDate(event.target.value);
  };

  const handleEndDateChange = (event) => {
    const selectedDate = new Date(event.target.value);
    const formattedDate = formatDate(selectedDate);
    setEDate(formattedDate);
    setEndDate(event.target.value);
  };

  const handleEmployeeIdChange = (event) => {
    setEmployeeId(event.target.value);
  };

  const handleStIdChange = (event) => {
    setStId(event.target.value);
  };

  const handleGenerateReportClick = () => {
    if (reportOption === "shift") {
      axios
        .get(
          `http://10.191.80.73:7001/seats/booked/byshift/report?stId=${stId}&startDate=${sDate}&endDate=${eDate}`,
          {
            responseType: "blob",
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid"),
            },
          }
        )
        .then((response) => {
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", "seats_booked_by_shift.pdf");
          document.body.appendChild(link);
          link.click();
        });
    } else if (reportOption === "date") {
      axios
        .get(
          `http://10.191.80.99:7001/seats/booked/report?startDate=${sDate}&endDate=${eDate}`,
          {
            responseType: "blob",
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid"),
            },
          }
        )
        .then((response) => {
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", "seats_booked.pdf");
          document.body.appendChild(link);
          link.click();
        });
    } else if (reportOption === "employee") {
      axios
        .get(
          `http://10.191.80.99:7001/seats/booked/byemployee/report?empId=${employeeId}&startDate=${sDate}&endDate=${eDate}`,
          {
            responseType: "blob",
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
              "X-Role": localStorage.getItem("role"),
              "X-Eid": localStorage.getItem("eid"),
            },
          }
        )
        .then((response) => {
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", "seats_booked_by_employee.pdf");
          document.body.appendChild(link);
          link.click();
        });
    }
  };

  return (
    <div>
      <nav className="navbar fixed-top navbar-light bg-light justify-content-between">
        <div className="navbar-left">
          <a href="#">SMS</a>
        </div>
        <div className="navbar-right">
          <a href="#" onClick={handleLogout} disabled={isLoggingOut}>
            Logout
          </a>
        </div>
      </nav>

      <div className="report">
        <h3>Generate Report</h3>
        <div className="shift">
          <label htmlFor="reportOption"></label>
          <select
            id="reportOption"
            name="reportOption"
            value={reportOption}
            onChange={handleReportOptionChange}
            placeholder="Select Shifts"
          >
            <option value="">-- Select Report Option --</option>
            <option value="shift">Seats Booked By Shift</option>
            <option value="date">Seats Booked By Date Range</option>
            <option value="employee">Seats Booked By Employee</option>
          </select>
        </div>
        {reportOption === "shift" && (
          <div className="shiftId">
            <label htmlFor="stId"></label>
            <select
              id="stId"
              name="stId"
              value={stId}
              onChange={handleStIdChange}
            >
              {shiftOptions.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>
        )}
        <div className="form_report">
          <div className="details">
            <label htmlFor="startDate">Start Date:</label>
            <input
              type="date"
              id="startDate"
              name="startDate"
              value={startDate}
              onChange={handleStartDateChange}
            />
          </div>
          <div className="details">
            <label htmlFor="endDate">End Date:</label>
            <input
              type="date"
              id="endDate"
              name="endDate"
              value={endDate}
              onChange={handleEndDateChange}
            />
          </div>
        </div>
        {reportOption === "employee" && (
          <div className="employeedetails">
            <label htmlFor="employeeId">Employee ID:</label>
            <input
              type="text"
              id="employeeId"
              name="employeeId"
              value={employeeId}
              onChange={handleEmployeeIdChange}
            />
          </div>
        )}
        <button onClick={handleGenerateReportClick}>Generate Report</button>
      </div>
    </div>
  );
}

export default Report;
