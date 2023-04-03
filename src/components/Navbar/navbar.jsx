
import './navbar.css';
import { useState } from 'react';

function Navbar(){
    const [isLoggingOut, setIsLoggingOut] = useState(false);

    function handleLogout() {
        // Make a request to the backend to invalidate the token
        // and remove it from the client-side storage
        setIsLoggingOut(true);
        localStorage.removeItem("token");
        // Redirect to the initial page
       window.location="/"
      }
    
    return(
        <div>
        <div>
            
            {/* <nav className="navbar navbar-expand-md">
    
                <div className="mx-auto order-0">
                    <a className="navbar-brand mx-auto app_name ml-auto" href="#">SMS</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <button >Logout</button>
                </div>
    
            </nav> */}.
           <nav>
                <div class="navbar-left">
                    <a href="#">SMS</a>
                </div>
                <div class="navbar-right">
                    <a href="#" onClick={handleLogout} disabled={isLoggingOut}>Logout</a>
                </div>
            </nav>

        </div></div>
    )
}

export default Navbar;
