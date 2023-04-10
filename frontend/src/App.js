import './App.css';
import {BrowserRouter,Routes,Route,Link, useLocation, Outlet, Navigate,} from "react-router-dom"
import Register from './components/Register/register';
import Login from './components/Login/login';
import Forget from './components/Forget/forget';
import Reset from './components/Reset/reset';
import Dashboard from './components/Dashboard/dashboard';
import Profile from './components/Profile/profile';
import Notify from './components/Notify/notify';
import FloorList from './components/FloorList/floorList';
import BookSeat from './components/BookSeat/bookSeat';
import AdminDashboard from './components/Dashboard/AdminDashboard/admin_dashboard';
import GroundFloor from './components/layouts/ground';
import QrCodeScan from './components/QR/qrscanner';
import QrCodeGen from './components/QR/qrgenerator';
import ViewPass from './components/ViewPass/viewPass';
import Manager from './components/Manager/manager'
import Registration_Approval from './components/Registration_Approval/registration_approval';
import FirstFloor from './components/layouts/firstFloor';
import SecondFloor from './components/layouts/secondFloor';
import ThirdFloor from './components/layouts/thirdFloor';
import Manager_Approval from './components/Manager/Manager_Approval/manager_approval';
import EmployeeList from './components/Manager/EmployeeList/EmployeeList';
import Report from './components/report/report';
import Atten_Regularize from './components/Atten_Regularize/atten_regularize';



function App() {

  // const [isLoggedIn, setIsLoggedIn] = useState(false);
  // useEffect(()=>{
  //   let token = localStorage.getItem("token");
  //   console.log(token)
  //   if(token===undefined){
  //     setIsLoggedIn(false)
  //   }else{
  //     setIsLoggedIn(true)

  //   }
  // })
  // const ProtectedRoute=({ element, isLoggedIn })=> {
  //   return isLoggedIn ? element : <Navigate to="/"/>;
  // }
  
  return (

    <>
    <BrowserRouter>
    {/* <Link to="register"><button type="button" class="btn btn-primary btn-lg">register</button></Link> */}
   
    <Routes>
    <Route path="/" element={<Login/>}/>    
    <Route path="/register" element={<Register/>}/>    
    <Route path="/forget" element={<Forget/>}/>    
    <Route path="/forget/reset/:email" element={<Reset/>}/>    
    <Route path="/dashboard/:id" element={<Dashboard/>}/>    
    <Route path="/admin/:id" element={<AdminDashboard/>}/> 
    <Route path="/dashboard/:id" element={<Dashboard/>}/>    
    <Route path="/profile/:id" element={<Profile/>}/>    
    <Route path="/notify/:id" element={<Notify/>}/>    
    <Route path="/manager/:id" element={<Manager/>}/>    
    <Route path="/floorlist/:id" element={<FloorList/>}/>    
    <Route path="/viewpass/:id" element={<ViewPass/>}/>    
    <Route path="/bookseat/:id" element={<BookSeat/>}/>    
    <Route path="/qrscanner" element={<QrCodeScan/>}/>    
    <Route path="/qrgenerator" element={<QrCodeGen/>}/>    
    <Route path="/gfloor/:id" element={<GroundFloor/>}/>    
    <Route path="/ffloor" element={<FirstFloor/>}/>    
    <Route path="/sfloor" element={<SecondFloor/>}/>    
    <Route path="/tfloor" element={<ThirdFloor/>}/>    
    <Route path="/report" element={<Report/>}/>    
    <Route path="/admin/approval" element={<Registration_Approval/>}/>    
    <Route path="/manager/approval" element={<Manager_Approval/>}/>    
    <Route path="/atten_regularize/:id" element={<Atten_Regularize/>}/>    
    <Route path="/manager/employeeList/:id" element={<EmployeeList/>}/>    
    </Routes>
     {/* <Routes>
        <Route path="/"  element={<Login />} isLoggedIn={isLoggedIn} />
        <Route path="/manager/:id" element={<ProtectedRoute element={<Dashboard />} isLoggedIn={isLoggedIn} />} />
        <Route path="/admin" element={<ProtectedRoute element={<AdminDashboard />} isLoggedIn={isLoggedIn} />} />
      </Routes> */}
    </BrowserRouter>
    </>
  );
}

export default App;
