import React from 'react';
import Sidebar from '../Sidebar/sidebar';
import './floorList.css';

function FloorList(){
    return(
       
        <div className='FloorList_container'> 
            <div>
                <Sidebar/>
            </div>   
        <div className='fList'>
            <h3>Select Floor</h3>
            <div className="floor0">
                
                <a href='/gfloor'><button className='floorbtn'>Ground Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/'><button className='floorbtn'>First Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/'><button className='floorbtn'>Second Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/'><button className='floorbtn'>Third Floor</button></a>
            </div>
        </div>
        </div>
        
    )
}
export default FloorList;