import React from 'react';
import TextInput from '../common/TextInput';
import SelectInput from '../common/SelectInput';

const EmployeeForm = ({employee, departments, jobs, errors, onChange, onSave, saving}) => {
    return (
        <form onSubmit={onSave}>
            <h2>{employee.id ? 'Edit' : 'Add'} Employee</h2>
            {errors.onSave && (
                <div className={'alert alert-danger'} role={'alert'}>
                    {errors.onSave}
                </div>
            )}

            <TextInput name={'firstName'} label={'First Name'} value={employee.firstName} onChange={onChange} error={errors.firstName} />

            <TextInput name={'lastName'} label={'Last Name'} value={employee.lastName} onChange={onChange} error={errors.lastName} />

            <SelectInput name={'department'} label={'Department'} value={employee.department || ''}
                         defaultOption={'Select Department'} onChange={onChange} error={errors.department}
                         options={departments.map((dept) => ({
                             value: dept.id,
                             text: dept.name
                         }))}
            />

            <SelectInput name={'job'} label={'Job'} value={employee.job || ''}
                         defaultOption={'Select Job'} onChange={onChange} error={errors.job}
                         options={jobs.map((job) => ({
                             value: job.id,
                             text: job.designation
                         }))}
            />

            <TextInput name={'hireDate'} label={'Hire Date'} value={employee.hireDate} onChange={onChange} error={errors.hireDate} />

            <button type={'submit'} disabled={saving} className={'btn btn-primary'}>
                {saving ? 'Saving...' : 'Save'}
            </button>
        </form>
    )
};

export default EmployeeForm;