import React from 'react';
import Sidebar from '../Sidebar/sidebar';
import './floorList.css';
import { useParams } from 'react-router-dom';

function FloorList(){
    const {id} = useParams()
    const gfloorFunc=()=>{
        window.location=`/gfloor/${id}`
    }
    const ffloorFunc=()=>{
        window.location=`ffloor/${id}`
    }
    const sfloorFunc=()=>{
        window.location=`/sfloor/${id}`
    }
    const tfloorFunc=()=>{
        window.location=`/tfloor/${id}`
    }
    return(
       
        <div className='FloorList_container'> 
            <div>
                
            </div>   
        <div className='fList'>
            <h3>Select Floor</h3>
            <div className="floor0">
                
                <a ><button onClick={gfloorFunc} className='floorbtn'>Ground Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/ffloor'><button onClick={ffloorFunc} className='floorbtn'>First Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/sfloor'><button onClick={sfloorFunc} className='floorbtn'>Second Floor</button></a>
            </div>
            <div className="floor0">
                
            <a href='/tfloor'><button onClick={tfloorFunc} className='floorbtn'>Third Floor</button></a>
            </div>
        </div>
        </div>
        
    )
}
export default FloorList;