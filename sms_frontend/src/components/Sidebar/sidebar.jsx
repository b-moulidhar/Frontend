import React, { useState } from 'react';
import {
    FaHome,
    FaUserAlt,
    FaBars,
    FaElementor,
    FaBell,
    FaStickyNote,
    FaPowerOff

} from "react-icons/fa";
import { NavLink } from 'react-router-dom';

function Sidebar({children}){
    const[isOpen,setIsOpen] = useState(false)
    const toggle = () => setIsOpen(!isOpen);
    const menuItem=[
        {
            path:"/dashboard",
            name:"Dashboard",
            icon:<FaHome/>
        },
        {
            path:"/profile/:id",
            name:"Profile",
            icon:<FaUserAlt/>
        }
        ,
        {
            path:"/manager",
            name:"Manager",
            icon:<FaElementor/>
        },
        {
            path:"/notify",
            name:"Notification",
            icon:<FaBell/>
        },
        {
            path:"/atten_regularize",
            name:"Atten_Regularize",
            icon:<FaStickyNote/>
        },
        {
            path:"/",
            name:"Signout",
            icon:<FaPowerOff/>
        }
    ]
    return(
        <>
            <div className='sidebar_container'>
                <div style={{width: isOpen ? "250px" : "50px"}} className='sidebar'>
                    <div className='top_section'>
                        <h1 style={{display: isOpen ? "block" : "none"}} className="logo">SMS</h1>
                       <div style={{marginLeft: isOpen ? "50px" : "0px"}} className="bars">
                            <FaBars onClick={toggle}/>
                       </div>
                    </div>
                {
                    menuItem.map((item, index)=>(
                        <NavLink to={item.path} key={index} className="link" activeclassName="active">
                            <div className="icon">{item.icon}</div>
                            <div style={{display: isOpen ? "block" : "none"}} className="link_text">{item.name}</div>
                        </NavLink>
                    ))
                }
                </div>
                {/* <main>{children}</main> */}
            </div>
        </>
    )
}
export default Sidebar;