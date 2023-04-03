
import './navbar.css';


function Navbar(){
    
    return(
        <div>
            
        <nav className="navbar navbar-expand-md">
    
            <div className="mx-auto order-0">
                <a className="navbar-brand mx-auto app_name" href="#">SMS</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
                    <span className="navbar-toggler-icon"></span>
                </button>
            </div>
    
        </nav>
        </div>
    )
}

export default Navbar;