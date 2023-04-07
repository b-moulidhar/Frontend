import firebase from 'firebase/app';
import 'firebase/auth';

const firebaseConfig = {
    apiKey: "AIzaSyDKnBBxhJGWUHdbQzg1bQ6u7_oD63y1ZCE",
    authDomain: "tesla-147a4.firebaseapp.com",
    projectId: "tesla-147a4",
    storageBucket: "tesla-147a4.appspot.com",
    messagingSenderId: "649448339600",
    appId: "1:649448339600:web:b6b13b2df04e7cb3004015",
    measurementId: "G-SN3H1TETD9"
};

firebase.initializeApp(firebaseConfig);

export default firebase;

