import Sidebar from "../Sidebar/sidebar";
// import Card from "../Card/card"
import './notify.css';

function Notify(){
    return(
            <div className='notify'>
        
       <div>
        <Sidebar/>
       </div>
        <div>
            
        <div class="middle-row col-lg-7 text-center">
        <div class="box5 shadow">
            <span class="numb">
                0
            </span>
            <span class="char">
                Approved
            </span>
        </div>
        <div class="box6 shadow">
            <span class="numb">
                0
            </span>
            <span class="char">
                Pending
            </span>
        </div>
        <div class="box7 shadow">
            <span class="numb">
                0
            </span>
            <span class="char">
                Rejected
            </span>
            
        </div>
    </div>
            {/* <Card title="Approved" body="0"/> */}
            {/* <Card/> */}


            </div>
        </div>
        
    )
}

export default Notify;