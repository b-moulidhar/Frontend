import React from "react";
import { useState, useEffect } from "react";
import axios from 'axios';
import Dashboard from "./Dashboard/dashboard";
import Manager from "./Manager/manager";

function Combine_Dashboard(){
    const [userId, setUserId] = useState(null);
    const [userRole, setUserRole] = useState(null);
  
    useEffect(() => {
      // Fetch the user ID 
      axios
        .get("")
        .then((response) => setUserId(response.data.id))
        .catch((error) => console.log(error));
    }, []);
  
    useEffect(() => {
      if (userId) {
        // Fetch the user's role 
        axios
          .get(``)
          .then((response) => setUserRole(response.data.role))
          .catch((error) => console.log(error));
      }
    }, [userId]);
  
    if (!userId || !userRole) {
      // Show a loading indicator while fetching the data
      return <p>Loading...</p>;
    }
  
    return (
      <div>
        {userRole === "employee" && <Dashboard/>}
        {userRole === "manager" && <Manager/>}
      </div>
    )
}

export default Combine_Dashboard;