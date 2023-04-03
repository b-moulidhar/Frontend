
import styled from 'styled-components';
import './login.css'
import firebase from '../firebase';
import React, { useState, useEffect } from 'react';
import logo from '../assests/logo.png'

function Login() {
    useEffect(() => {
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
        const container = document.getElementById('container');

        signUpButton.addEventListener('click', () => {
            container.classList.add('right-panel-active');
        });

        signInButton.addEventListener('click', () => {
            container.classList.remove('right-panel-active');
        });
    }, []);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [loginemail, setLoginmail] = useState('');
    const [loginpassword, setLoginPassword] = useState('');

    const loginhandleSubmit = async (event) => {
        event.preventDefault();
        try {
            await firebase.auth().signInWithEmailAndPassword(email, password);
            // Redirect to home page
        } catch (error) {
            console.error(error);
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await firebase.auth().createUserWithEmailAndPassword(email, password);
            // Redirect to home page
        } catch (error) {
            console.error(error);
        }
    };
    return (
        <div className='main'>
            
            <div className='left'>
                <img className="logo" src={logo} alt='logo'/>
                {/* <span className='tesla'>Tesla</span> */}
                <img src='https://tesla-cdn.thron.com/delivery/public/image/tesla/649c549e-12b4-40c6-9294-7e996b8d3ccb/bvlatuR/std/4096x3071/Model-S-Specs-Hero-Desktop-LHD' alt='' />
            </div>
            <div className='right'>

                <div className="container" id="container">
                    <div className="form-container sign-up-container">
                        <form onSubmit={handleSubmit}>
                            <h1>Create Account</h1>
                            <span>or use your email for registration</span>
                            <input
                                type="email"
                                placeholder="Email"
                                value={email}
                                onChange={(event) => setEmail(event.target.value)}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                            />
                            <button type="submit">Sign up</button>
                        </form>
                    </div>
                    <div class="form-container sign-in-container">
                        <form onSubmit={loginhandleSubmit}>
                            <h1>Sign in</h1>
                            <span>or use your account</span>
                            <input
                                type="email"
                                placeholder="Email"
                                value={email}
                                onChange={(event) => setEmail(event.target.value)}
                            />
                            <input
                                type="password"
                                placeholder="Password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                            />
                            <button type="submit">Log in</button>
                        </form>
                    </div>
                    <div className="overlay-container">
                        <div className="overlay">
                            <div className="overlay-panel overlay-left">
                                <h1>Welcome Back!</h1>
                                <p>To keep connected with us please login with your personal info</p>
                                <button className="ghost" id="signIn">Sign In</button>
                            </div>
                            <div class="overlay-panel overlay-right">
                                <h1>Hello, Friend!</h1>
                                <p>Enter your personal details and start journey with us</p>
                                <button className="ghost" id="signUp">Sign Up</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    )
}

export default Login