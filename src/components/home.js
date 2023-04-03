import React from 'react';
import firebase from '../firebase';
import { useHistory } from 'react-router-dom';

const Home = () => {
    const history = useHistory();
  const handleLogout = async () => {
    try {
      await firebase.auth().signOut();
      history.push('/');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className='main'>
      <h1>Welcome to the app!</h1>
      <button onClick={handleLogout}>Log out</button>
    </div>
  );
};

export default Home;