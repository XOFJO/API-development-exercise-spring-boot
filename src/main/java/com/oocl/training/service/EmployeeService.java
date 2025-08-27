package com.oocl.training.service;
import com.oocl.training.DAO.EmployeeRepo;
import com.oocl.training.Model.Employee;
import exception.OutsideAgeRangeEmployee;
import exception.Over30YearsOldSalaryLessThan20000;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {

        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new OutsideAgeRangeEmployee("Employee age must be between 18 and 65.");
        }

        if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new Over30YearsOldSalaryLessThan20000("Employees over 30 must have a salary of at least 10000.");
        }
        employee.setStatus(true);
        return employeeRepo.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepo.updateById(id, updatedEmployee);
    }

    public void deleteEmployee(int id) {
        employeeRepo.deleteById(id);
    }
}
