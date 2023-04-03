import './App.css';
import {BrowserRouter,Routes,Route,Link} from "react-router-dom"
import Register from './components/Register/register';
import Login from './components/Login/login';
import Forget from './components/Forget/forget';
import Reset from './components/Reset/reset';
import Dashboard from './components/Dashboard/dashboard';
import Navbar from './components/Navbar/navbar';
import Profile from './components/Profile/profile';
import Sidebar from './components/Sidebar/sidebar';
import Notify from './components/Notify/notify';
import FloorList from './components/FloorList/floorList';
import BookSeat from './components/BookSeat/bookSeat';
import AdminDashboard from './components/Dashboard/AdminDashboard/admin_dashboard';
import GroundFloor from './components/layouts/ground';
import QrCodeScan from './components/QR/qrscanner';
import QrCodeGen from './components/QR/qrgenerator';
import ViewPass from './components/ViewPass/viewPass';
import Registration_Approval from './components/Registration_Approval/registration_approval';
import Manager from './components/Manager/manager';
import Atten_Regularize from './components/Atten_Regularize/atten_regularize';



function App() {
  return (

    <>
    <BrowserRouter>
    {/* <Link to="register"><button type="button" class="btn btn-primary btn-lg">register</button></Link> */}
   
    <Routes>
    <Route path="/" element={<Login/>}/>     
    <Route path="/register" element={<Register/>}/>    
    <Route path="/forget" element={<Forget/>}/>    
    <Route path="/forget/reset" element={<Reset/>}/>    
    <Route path="/dashboard/:id" element={<Dashboard/>}/>    
    <Route path="/admin" element={<AdminDashboard/>}/>    
    <Route path="/navbar" element={<Navbar/>}/>    
    <Route path="/profile/:id" element={<Profile/>}/>    
    {/* <Route path="/sidebar" element={<Sidebar/>}/>     */}
    <Route path="/notify" element={<Notify/>}/>    
    <Route path="/manager" element={<Manager/>}/>    
    <Route path="/floorLIst" element={<FloorList/>}/>    
    <Route path="/viewpass" element={<ViewPass/>}/>    
    <Route path="/bookseat" element={<BookSeat/>}/>    
    <Route path="/qrscanner" element={<QrCodeScan/>}/>    
    <Route path="/qrgenerator" element={<QrCodeGen/>}/>    
    <Route path="/gfloor" element={<GroundFloor/>}/>    
    <Route path="/admin/approval" element={<Registration_Approval/>}/>    
    <Route path="/atten_regularize" element={<Atten_Regularize/>}/>    

    </Routes>
    </BrowserRouter>
    </>
  );
}

export default App;
