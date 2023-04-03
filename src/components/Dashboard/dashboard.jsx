import Navbar from "../Navbar/navbar";
import Sidebar from "../Sidebar/sidebar";
import "./dashboard.css";

function Dashboard() {
  return (
    <div className="dashboard_container">
      <div className="mainpage">
              <div>
                <Sidebar/>
              </div>
        <div>
        <Navbar/>
          {/* <h2 className="status">Status</h2> */}
          <div className="dashboard_head">
            <h3>your name</h3>
            <p>your seat for today is 1 at ground floor</p>
          </div>
          {/* <div className=" atten">
            <div className="attenstatus">
              <button type="button" className="btn btn-success statusbtn">
                Approved
              </button>
            </div>
            <div className="attenstatus">
              <button type="button" className="btn btn-warning statusbtn">
                Pending
              </button>
            </div>
            <div className="attenstatus">
              <button type="button" className="btn btn-danger statusbtn">
                Rejected
              </button>
            </div>
          </div> */}

                <div class="middle-row col-lg-7 text-center ">
                  <div class="box5 shadow">
                    <span class="numb">0</span>
                    <span class="char">Approved</span>
                  </div>
                  <div class="box6 shadow">
                    <span class="numb">0</span>
                    <span class="char">Pending</span>
                  </div>
                  <div class="box7 shadow">
                    <span class="numb">0</span>
                    <span class="char">Rejected</span>
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

                <div className="dashboard_bottom">
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
                </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
