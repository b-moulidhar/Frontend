import React, { useState } from 'react';

const EmployeeList = ({ employees }) => {
  const [query, setQuery] = useState('');

  const handleInputChange = (event) => {
    setQuery(event.target.value);
  };

  const filteredEmployees = employees.filter((employee) => {
    return employee.name.toLowerCase().includes(query.toLowerCase());
  });

  return (
    <div>
      <h2>Employee Table</h2>
      <form>
        <input
          type="text"
          placeholder="Search employee"
          value={query}
          onChange={handleInputChange}
        />
      </form>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          {filteredEmployees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.name}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeList;
