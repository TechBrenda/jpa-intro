import React from 'react';
import {Link} from 'react-router-dom';

const EmployeeList = ({employees, departments, jobs, projects, tasks, onDeleteClick}) => {
    return (
        <table className={'table'}>
            <thead>
            <tr>
                <th>Name</th>
                <th>Department</th>
                <th>Job</th>
                <th>Hire Date</th>
                <th>Projects</th>
                <th>Tasks</th>
                <th />
            </tr>
            </thead>
            <tbody>
            {employees.map((employee) => {
                const department = departments.find((dept) => dept.id === employee.department);
                const job = jobs.find((job) => job.id === employee.job);
                const empProj = projects.filter((project) => project.employees.find((e) => e.id === employee.id));
                const empTask = tasks.filter((task) => task.employee.id === employee.id);
                return (
                    <tr key={employee.id}>
                        <td><Link to={'/employee/' + employee.id}>{`${employee.firstName} ${employee.lastName}`}</Link></td>
                        <td>{department && department.name}</td>
                        <td>{job && job.designation}</td>
                        <td>{employee.hireDate}</td>
                        <td>
                            {empProj && empProj.length > 0 && (
                            <ul>
                                {empProj.map((project) => (
                                    <li key={project.id}>{project.title}</li>
                                ))}
                            </ul>
                            )}
                        </td>
                        <td>
                            {empTask && empTask.length > 0 && (
                            <ul>
                                {empTask.map((task) => (
                                    <li key={task.id}>{task.summary}</li>
                                ))}
                            </ul>
                            )}
                        </td>
                        <td>
                            <button
                                className={'btn btn-outline-danger'}
                                onClick={() => onDeleteClick(employee)}
                                disabled={employee.job === 1}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                );
            })}
            </tbody>
        </table>
    );
};

export default EmployeeList;