// import React, {useState, useEffect} from "react";
// import "./profile.css";
// import axios from 'axios';

// import {
//   MDBCol,
//   MDBContainer,
//   MDBRow,
//   MDBCard,
//   MDBCardText,
//   MDBCardBody,
//   MDBCardImage,
//   MDBBtn
// } from "mdb-react-ui-kit";
// import Sidebar from "../Sidebar/sidebar";

// export default function ProfilePage() {
//   const [profile, setProfile] = useState({
//      name: "",
//      email:"",
//      emp_id:Number,
//      phone:Number,
//      role:""
//   })
//   useEffect(()=>{
//     axios.get(`http://10.191.80.112:7001/employee/getAllEmployees/`,{
//        headers : {
//          Accept: 'application/json'
//        }
//     })
//     .then((response) => {
//           // setProfile({
//           //   ...profile,name:"abc",
//           //   ...profile,email:response.data.email
//           // })
//       setProfile(JSON.stringify(response.data))})
//       // console.log(JSON.stringify(response.data))})
//     .catch((err) => console.log(err))
//   },[]);
//   return (
//     <div className="profilepage">
//       <div>
//         <Sidebar />
//       </div>
//       <div>
//         <section
//           className="details_profile"
//           style={{ backgroundColor: "#eee" }}
//         >
//           <MDBContainer className="py-5">
//             <MDBRow>
//               <MDBCol lg="4">
//                 <MDBCard className="mb-4">
//                   <MDBCardBody className="text-center">
//                      <MDBCardImage
//                       src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
//                       alt="avatar"
//                       className="rounded-circle"
//                       style={{ width: "150px" }}
//                       fluid
//                     />
//                    <p className="text-muted mb-1">Full Stack Developer</p>
//                     <p className="text-muted mb-4">
//                       Bay Area, San Francisco, CA
//                     </p>
//                     <div className="d-flex justify-content-center mb-2">
//                       <MDBBtn>Follow</MDBBtn>
//                       <MDBBtn outline className="ms-1">
//                         Message
//                       </MDBBtn> 
//                     </div>
//                   </MDBCardBody>
//                 </MDBCard>
//               </MDBCol>
//               <MDBCol lg="8">
//                 <MDBCard className="mb-4">
//                   <MDBCardBody>
//                     <MDBRow>
//                       <MDBCol sm="3">
//                         <MDBCardText>Full Name</MDBCardText>
//                       </MDBCol>
//                       <MDBCol sm="9">
//                         <MDBCardText className="text-muted">
//                           {profile.name}
//                         </MDBCardText>
//                       </MDBCol>
//                     </MDBRow>
//                     <hr />
//                     <MDBRow>
//                       <MDBCol sm="3">
//                         <MDBCardText>Email</MDBCardText>
//                       </MDBCol>
//                       <MDBCol sm="9">
//                         <MDBCardText className="text-muted">
//                           {profile.email}
//                         </MDBCardText>
//                       </MDBCol>
//                     </MDBRow>
//                     <hr />
//                     <MDBRow>
//                       <MDBCol sm="3">
//                         <MDBCardText>Employee Id</MDBCardText>
//                       </MDBCol>
//                       <MDBCol sm="9">
//                         <MDBCardText className="text-muted">
//                           {profile.emp_id}
//                         </MDBCardText>
//                       </MDBCol>
//                     </MDBRow>
//                     <hr />
//                     <MDBRow>
//                       <MDBCol sm="3">
//                         <MDBCardText>Mobile</MDBCardText>
//                       </MDBCol>
//                       <MDBCol sm="9">
//                         <MDBCardText className="text-muted">
//                           {profile.phone}
//                         </MDBCardText>
//                       </MDBCol>
//                     </MDBRow>
//                     <hr />
//                     <MDBRow>
//                       <MDBCol sm="3">
//                         <MDBCardText>Role</MDBCardText>
//                       </MDBCol>
//                       <MDBCol sm="9">
//                         <MDBCardText className="text-muted">
//                           {profile.role}
//                         </MDBCardText>
//                       </MDBCol>
//                     </MDBRow>
//                   </MDBCardBody>
//                 </MDBCard>
//               </MDBCol>
//             </MDBRow>
//           </MDBContainer>
//         </section>
//       </div>
//     </div>
//   );
// }

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './profile.css';

function Profile() {
  const [userData, setUserData] = useState({});

  useEffect(() => {
    axios.get('')
      .then(response => {
        setUserData(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <section className="vh-100" style={{backgroundColor: '#f4f5f7'}}>
        <div className="container py-5 h-100">
          <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col col-lg-6 mb-4 mb-lg-0">
              <div className="card mb-3" style={{borderRadius: '.5rem'}}>
                <div className="row g-0">
                  <div className="col-md-4 text-center gradient-custom text-white" style={{borderTopLeftRadius: '.5rem', borderBottomLeftRadius: '.5rem'}}>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp" alt="Avatar" className="img-fluid my-5" style={{width: 80}} />
                    <h5>{userData.name}</h5>
                    <p>{userData.jobTitle}</p>
                    <i className="far fa-edit mb-5" />
                  </div>
                  <div className="col-md-8">
                    <div className="card-body p-4">
                      <h6>Information</h6>
                      <hr className="mt-0 mb-4" />
                      <div className="row pt-1">
                        <div className="col-6 mb-3">
                          <h6>Email</h6>
                          <p className="text-muted">{userData.email}</p>
                        </div>
                        <div className="col-6 mb-3">
                          <h6>Phone</h6>
                          <p className="text-muted">{userData.phone}</p>
                        </div>
                      </div>
                      <h6>Projects</h6>
                      <hr className="mt-0 mb-4" />
                      <div className="row pt-1">
                        <div className="col-6 mb-3">
                          <h6>Recent</h6>
                          <p className="text-muted">{userData.recentProject}</p>
                        </div>
                        <div className="col-6 mb-3">
                          <h6>Most Viewed</h6>
                          <p className="text-muted">{userData.mostViewedProject}</p>
                        </div>
                      </div>
                      {/* <div className="d-flex justify-content-start">
                        <a href={userData.facebookLink}><i className="fab fa-facebook-f fa-lg me-3" /></a>
                        <a href={userData.twitterLink}><i className="fab fa-twitter fa-lg me-3" /></a>
                        <a href={userData.instagramLink}><i className="fab fa-instagram fa-lg" /></a>
                      </div> */}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Profile;
