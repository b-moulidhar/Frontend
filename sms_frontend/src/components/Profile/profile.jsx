// import React, {useState, useEffect} from "react";
// import "./profile.css";
// import axios from 'axios';

// import {
//   MDBCol, MDBContainer, MDBRow, MDBCard, MDBCardText, MDBCardBody, MDBCardImage, MDBTypography, MDBIcon
// } from "mdb-react-ui-kit";
// import Sidebar from "../Sidebar/sidebar";
// import { useParams } from "react-router-dom";

// export default function ProfilePage() {
  
//   const {EId} = useParams();
//   const [employeeDetails, setEmployeeDetails] = useState({
//      name: "",
//      email:"",
//      emp_id:Number,
//      phone:Number,
//      role:""
//   })
//   useEffect(()=>{
//     axios.get(`http://10.191.80.103:7001/employee/getAllEmployees/${EId}`,{
//       //  headers : {
//       //    Accept: 'application/json'
//       //  }
//     })
//     .then((response) => {
//           // setProfile({
//           //   ...profile,name:response.data.emp_name,
//           //   ...profile,email:response.data.mail_id,
//           //   ...profile,phone:response.data.ph_num
//           // })
//           console.log(response.data.emp_name)
//       //setProfile(JSON.stringify(response.data))
//     })
//       // console.log(JSON.stringify(response.data))})
//     .catch((err) => console.log(err))
//   },[EId]);
//   return (
//     <div className="profilepage">
//       <div className="profile_side">
//         <Sidebar />
//       </div>
//       <div className="profile_contents">
//       <section className="vh-100" style={{ backgroundColor: '#f4f5f7' }}>
//       <MDBContainer className="py-5 h-100">
//         <MDBRow className="justify-content-center align-items-center h-100">
//           <MDBCol lg="6" className="mb-4 mb-lg-0">
//             <MDBCard className="mb-3" style={{ borderRadius: '.5rem' }}>
//               <MDBRow className="g-0">
//                 <MDBCol md="4" className="gradient-custom text-center text-white"
//                   style={{ borderTopLeftRadius: '.5rem', borderBottomLeftRadius: '.5rem' }}>
//                   <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
//                     alt="Avatar" className="my-5" style={{ width: '80px' }} fluid />
//                   <MDBTypography tag="h5">Marie Horwitz</MDBTypography>
//                   <MDBCardText>Web Designer</MDBCardText>
//                   <MDBIcon far icon="edit mb-5" />
//                 </MDBCol>
//                 <MDBCol md="8">
//                   <MDBCardBody className="p-4">
//                     <MDBTypography tag="h6">Information</MDBTypography>
//                     <hr className="mt-0 mb-4" />
//                     <MDBRow className="pt-1">
//                       <MDBCol size="6" className="mb-3">
//                         <MDBTypography tag="h6">Email</MDBTypography>
//                         <MDBCardText className="text-muted">info@example.com</MDBCardText>
//                       </MDBCol>
//                       <MDBCol size="6" className="mb-3">
//                         <MDBTypography tag="h6">Phone</MDBTypography>
//                         <MDBCardText className="text-muted">123 456 789</MDBCardText>
//                       </MDBCol>
//                     </MDBRow>

//                     <MDBTypography tag="h6">Information</MDBTypography>
//                     <hr className="mt-0 mb-4" />
//                     <MDBRow className="pt-1">
//                       <MDBCol size="6" className="mb-3">
//                         <MDBTypography tag="h6">Email</MDBTypography>
//                         <MDBCardText className="text-muted">info@example.com</MDBCardText>
//                       </MDBCol>
//                       <MDBCol size="6" className="mb-3">
//                         <MDBTypography tag="h6">Phone</MDBTypography>
//                         <MDBCardText className="text-muted">123 456 789</MDBCardText>
//                       </MDBCol>
//                     </MDBRow>

//                     <div className="d-flex justify-content-start">
//                       <a href="#!"><MDBIcon fab icon="facebook me-3" size="lg" /></a>
//                       <a href="#!"><MDBIcon fab icon="twitter me-3" size="lg" /></a>
//                       <a href="#!"><MDBIcon fab icon="instagram me-3" size="lg" /></a>
//                     </div>
//                   </MDBCardBody>
//                 </MDBCol>
//               </MDBRow>
//             </MDBCard>
//           </MDBCol>
//         </MDBRow>
//       </MDBContainer>
//     </section>
//       </div>
//     </div>
//   );
// }


import { useState, useEffect } from 'react';
import axios from 'axios';

function Profile() {
  const [userData, setUserData] = useState({
    id: '',
    name: '',
    email: '',
    phone: ''
  });
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    axios.get('') // replace with the backend API URL
      .then(response => {
        setUserData(response.data);
        setIsLoading(false);
      })
      .catch(error => console.error(error));
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    // handle form submit logic
  }

  const handleChange = (event) => {
    const { name, value } = event.target;
    setUserData(prevUserData => ({
      ...prevUserData,
      [name]: value
    }));
  }

  return (
    <div className="container mt-5">
      <h1>Profile</h1>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="id">ID</label>
            <input type="text" className="form-control" id="id" name="id" value={userData.id} onChange={handleChange} readOnly />
          </div>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input type="text" className="form-control" id="name" name="name" value={userData.name} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input type="email" className="form-control" id="email" name="email" value={userData.email} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="phone">Phone</label>
            <input type="tel" className="form-control" id="phone" name="phone" value={userData.phone} onChange={handleChange} required />
          </div>
          {/* <button type="submit" className="btn btn-primary">Save</button> */}
        </form>
      )}
    </div>
  );
}

export default Profile;
