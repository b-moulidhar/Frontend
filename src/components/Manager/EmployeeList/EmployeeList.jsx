// import React, { useState } from 'react';

// const EmployeeList = ({ employees }) => {
//   const [query, setQuery] = useState('');

//   const handleInputChange = (event) => {
//     setQuery(event.target.value);
//   };

//   const filteredEmployees = employees.filter((employee) => {
//     return employee.name.toLowerCase().includes(query.toLowerCase());
//   });

//   return (
//     <div>
//       <h2>Employee Table</h2>
//       <form>
//         <input
//           type="text"
//           placeholder="Search employee"
//           value={query}
//           onChange={handleInputChange}
//         />
//       </form>
//       <table>
//         <thead>
//           <tr>
//             <th>ID</th>
//             <th>Name</th>
//           </tr>
//         </thead>
//         <tbody>
//           {filteredEmployees.map((employee) => (
//             <tr key={employee.id}>
//               <td>{employee.id}</td>
//               <td>{employee.name}</td>
//             </tr>
//           ))}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// export default EmployeeList;

import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import DataTable, {defaultThemes} from 'react-data-table-component';

function EmployeeList(){
    const customStyles = {
        headRow: {
          style: {
            backgroundColor: 'blue',
            color: "white"
          }
        },
        headCells: {
          style: {
            fontSize: '16px',
            fontWeight: '600',
            textTransform: 'uppercase'
          }
        },
        cells: {
          style: {
            fontSize:  '15px'
          }
        }
      }
      const column = [
        {
          name: "ID",
          selector: row => row.id,
          sortable: true
        },
        {
          name: "Name",
          selector: row => row.name,
          sortable: true
        },
        {
          name: "Email",
          selector: row => row.email
        },
        {
          name: "City",
          selector: row => row.address.city
        }
      ]
    
      // useEffect(()=> {
      //   const fetchData = async () => {
      //     axios.get('https://jsonplaceholder.typicode.com/users')
      //     .then(res => setRecords(res.data))
      //     .catch(err=>console.log(err));
      //   }
      //   fetchData();
      // },[])
    
      useEffect(() => {
        const fetchData = async () => {
          try {
            const res = await axios.get('https://jsonplaceholder.typicode.com/users');
            setRecords(res.data);
            setFilterRecords(res.data);
          } catch (err) {
            console.log(err);
          }
        };
        fetchData();
      }, []);
    
      const [records,setRecords] = useState([])
      const [filterRecords,setFilterRecords] = useState([])
     
      const handleFilter = (event) => {
        const newData = filterRecords.filter(row => row.name.toLowerCase().includes(event.target.value.toLowerCase()))
        setRecords(newData);
      }
    return(
        <div style={{padding: "50px 10px", backgroundColor:"crimson"}}>
        <div style={{display:'flex', justifyContent: 'right'}}>
          <input type='text' placeholder='Search...' onChange={handleFilter} style={{padding: '6px 10px'}}/>
        </div>
        <DataTable 
        columns={column}
        data={records}
        customStyles={customStyles}
        pagination
        selectableRows
        >
        </DataTable>
      </div>
    )
}

export default EmployeeList;