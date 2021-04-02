import React, {useState, useEffect} from 'react';
import axios from 'axios';
import EmployeeForm from './EmployeeForm';

const ManageEmployee = (props) => {
    const newEmployee = {
        firstName: '',
        lastName: '',
        department: 0,
        job: 0,
        hireDate: ''
    };
    const [employee, setEmployee] = useState(newEmployee);
    const [departments, setDepartments] = useState([]);
    const [jobs, setJobs] = useState([]);
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        const id = props.match.params.id;
        if (id) {
            axios.get(`/api/employees/${id}`)
                .then((response) => {
                    setEmployee({
                        id: response.data.id,
                        firstName: response.data.firstName,
                        lastName: response.data.lastName,
                        hireDate: response.data.hireDate,
                        department: response.data.department.id,
                        job: response.data.job.id
                    });
                })
                .catch((error) => {
                    console.log(`Error retrieving employee with ID ${id}: ${error.message}`);
                });
        }
        axios.get('/api/departments/')
            .then((response) => {
                setDepartments(response.data);
            })
            .catch((error) => {
                console.log(`Error retrieving departments: ${error.message}`);
            });
        axios.get('/api/jobs/')
            .then((response) => {
                setJobs(response.data);
            })
            .catch((error) => {
                console.log(`Error retrieving jobs: ${error.message}`);
            });
    },[props.match.params.id]);

    const handleChange = (event) => {
        const {name, value} = event.target;
        setEmployee((prevEmployee) => ({
            ...prevEmployee,
            [name]: (name === 'department' || name === 'job') ? parseInt(value, 10) : value
        }));
    };

    const formIsValid = () => {
        const {firstName, lastName, department, job, hireDate} = employee;
        const errorList = {};

        if (!firstName) {
            errorList.firstName = 'First name is required.';
        }
        if (!lastName) {
            errorList.lastName = 'Last name is required.';
        }
        if (!department) {
            errorList.department = 'Department selection is required.';
        }
        if (!job) {
            errorList.job = 'Job is required.';
        }
        if (!hireDate) {
            errorList.hireDate = 'Hire date is required.';
        }

        setErrors(errorList);
        return Object.keys(errorList).length === 0;
    };

    const handleSave = (event) => {
        event.preventDefault();
        if (!formIsValid()) return;
        setSaving(true);
        const id = props.match.params.id ? props.match.params.id : '';
        axios({
            method: id !== '' ? 'put' : 'post',
            url: `/api/employees/${id}`,
            data: {
                id: employee.id,
                firstName: employee.firstName,
                lastName: employee.lastName,
                hireDate: employee.hireDate,
                department: departments.find((dept) => dept.id === employee.department),
                job: jobs.find((job) => job.id === employee.job)
            }
        }).then(() => {
            props.history.push('/employees');
        }).catch((error) => {
            console.log(`Error while saving employee: ${error.message}`, employee);
        });
    };

    return (
        <EmployeeForm
            employee={employee}
            departments={departments}
            jobs={jobs}
            errors={errors}
            onChange={handleChange}
            onSave={handleSave}
            saving={saving}
        />
    );
};

export default ManageEmployee;