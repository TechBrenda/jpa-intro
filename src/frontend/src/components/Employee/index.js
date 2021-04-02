import React, {useState, useEffect} from 'react';
import EmployeeList from './EmployeeList';
import {Redirect} from 'react-router-dom';
import axios from 'axios';

const Employee = () => {
    const [redirectToAddEmployee, setRedirectToAddEmployee] = useState(false);
    const [employees, setEmployees] = useState([]);
    const [departments, setDepartments] = useState([]);
    const [jobs, setJobs] = useState([]);
    const [tasks, setTasks] = useState([]);
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        Promise.all([
            axios.get('/api/employees/'),
            axios.get('/api/departments/'),
            axios.get('/api/jobs/'),
            axios.get('/api/projects/'),
            axios.get('/api/tasks/')
        ])
            .then((responses) => {
                setEmployees(responses[0].data.map((d) => ({
                    id: d.id,
                    firstName: d.firstName,
                    lastName: d.lastName,
                    hireDate: d.hireDate,
                    department: d.department.id,
                    job: d.job.id
                })));
                setDepartments(responses[1].data);
                setJobs(responses[2].data);
                setProjects(responses[3].data)
                setTasks(responses[4].data);
            })
            .catch((error) => {
                console.log(error.message);
            });
    },[]);

    const handleEmployeeDelete = (employee) => {
        axios.delete(`/api/employees/${employee.id}`)
            .catch((error) => {
                console.log(error.message);
            });
    };

    return (
        <div>
            {redirectToAddEmployee && <Redirect to={'/employee'} />}
            <h2>Employees</h2>
            <button
                style={{ marginBottom: 20}}
                className={'btn btn-primary'}
                onClick={() => setRedirectToAddEmployee(true)}
            >
                Add Employee
            </button>
            <EmployeeList employees={employees} departments={departments} jobs={jobs} tasks={tasks} projects={projects} onDeleteClick={handleEmployeeDelete} />
        </div>
    );
};

export default Employee;