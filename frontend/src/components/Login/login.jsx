import { useEffect, useState } from "react";
import "./login.css";
import axios from "axios";

// Define the Login component
export default function Login() {
  // Define state variables for the employee ID, password, and error message
  const [empId, setEmpId] = useState("");
  const [pass, setPass] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  // Handle the form submission when the user clicks the "Log in" button
  const handleLogin = (e) => {
    e.preventDefault();
    try {
      // Make a POST request to the login API with the employee ID and password
      const res = axios
        .post("http://20.253.3.209:7001/api/login", { empId, pass })
        .then((res) => {
          // If the API call is successful, store the returned token, employee ID, and role in local storage
          const { token, EId, role } = res.data;
          localStorage.setItem("token", token);
          localStorage.setItem("EId", EId);
          localStorage.setItem("role", role);
          console.log(token);
          // Redirect to the appropriate page based on the user's role
          if (res.data.token != undefined) {
            if (localStorage.getItem("role") === "Manager") {
              window.location = "/manager/" + EId;
            } else if (localStorage.getItem("role") === "Employee") {
              window.location = "/dashboard/" + EId;
            } else if (localStorage.getItem("role") === "Admin") {
              window.location = "/admin/" + EId;
            }
          } else {
            // If the API call is successful but the token is undefined, show an alert with an error message
            alert("invalid credentials");
          }

          console.log("posted", res.data);
        })
        .catch((err) => console.log(err));
      // console.log(response.data)
    } catch (error) {
      // If the API call fails, set an error message based on the HTTP response status
      if (error.response && error.response.status === 401) {
        setErrorMessage("Invalid credentials");
        console.log(error.response);
      } else {
        setErrorMessage("An error occurred during login");
        console.log(error.response);
      }
    }
  };

  // Handle the "Create new" button click by redirecting to the registration page
  const registerPage = () => {
    window.location = "/register";
  };


  return (
    <div className="login-container">
    <section
      className="h-100 gradient-form"
    >
      <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
          <div className="col-xl-10">
            <div className="card rounded-3 text-black">
              <div className="row g-0">
                <div className="col-lg-6">
                  <div className="card-body p-md-5 mx-md-4">
                    <div className="text-center">
                      <img
                        src="https://left-hand-corner.000webhostapp.com/images/smslogo.png"
                        style={{ width: 80 }}
                        alt="logo"
                      />
                      <h4 className="mt-1 mb-5 pb-1">Valtech</h4>
                    </div>
                    <form className="login-form">
                      <p>Please login to your account</p>
                      <div className="form-outline mb-4">
                        <input
                          type="text"
                          className="form-control"
                          value={empId}
                          id="user"
                          min="1000"
                          max="9999"
                          required
                          onChange={(e) => setEmpId(e.target.value)}
                        />
                        <label className="form-label login-label" htmlFor="user">
                          Employee ID
                        </label>
                      </div>
                      <div className="form-outline mb-4">
                        <input
                          type="password"
                          className="form-control "
                          id="pswd"
                          value={pass}
                          required
                          onChange={(e) => setPass(e.target.value)}
                        />
                        <label className="form-label login-label" htmlFor="pswd">
                          Password
                        </label>
                      </div>

                      <div className="text-center pt-1 mb-5 pb-1">
                        <button
                          onClick={handleLogin}
                          className="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
                          type="button"
                        >
                          Log in
                        </button>
                        <a className="text-muted" href="/forget">
                          Forgot password?
                        </a>
                      </div>
                      <div className="d-flex align-items-center justify-content-center pb-4">
                        <p className="mb-0 me-2">Don't have an account?</p>
                        <button
                          onClick={registerPage}
                          type="button"
                          className="btn btn-outline-danger"
                        >
                          Create new
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
                <div className="col-lg-6 d-flex align-items-center gradient-custom-2">
                  <div className="text-white px-3 py-4 p-md-5 mx-md-4">
                    <h4 className="mb-4">We are more than just a company</h4>
                    <p className="small mb-0">
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                      sed do eiusmod tempor incididunt ut labore et dolore magna
                      aliqua. Ut enim ad minim veniam, quis nostrud exercitation
                      ullamco laboris nisi ut aliquip ex ea commodo consequat.
                    </p>
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
