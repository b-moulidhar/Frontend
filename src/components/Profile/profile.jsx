
import { useState, useEffect } from 'react';
import axios from 'axios';

function Profile() {
  const [userData, setUserData] = useState({
    id: '',
    name: '',
    email: '',
    phone: ''
  });
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    axios.get('') // backend API URL
      .then(response => {
        setUserData(response.data);
        setIsLoading(false);
      })
      .catch(error => console.error(error));
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
   
  }

  const handleChange = (event) => {
    const { name, value } = event.target;
    setUserData(prevUserData => ({
      ...prevUserData,
      [name]: value
    }));
  }

  return (
    <div className="container mt-5">
      <h1>Profile</h1>
      {isLoading ? (
        <div>Loading...</div>
      ) : (
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="id">ID</label>
            <input type="text" className="form-control" id="id" name="id" value={userData.id} onChange={handleChange} readOnly />
          </div>
          <div className="form-group">
            <label htmlFor="name">Name</label>
            <input type="text" className="form-control" id="name" name="name" value={userData.name} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input type="email" className="form-control" id="email" name="email" value={userData.email} onChange={handleChange} required />
          </div>
          <div className="form-group">
            <label htmlFor="phone">Phone</label>
            <input type="tel" className="form-control" id="phone" name="phone" value={userData.phone} onChange={handleChange} required />
          </div>
          {/* <button type="submit" className="btn btn-primary">Save</button> */}
        </form>
      )}
    </div>
  );
}

export default Profile;
