package com.oocl.training.service;
import com.oocl.training.dao.EmployeeMemoryRepository;
import com.oocl.training.model.Employee;
import exception.OutsideAgeRangeEmployee;
import exception.Over30YearsOldSalaryLessThan20000;
import exception.UpdateInactiveEmployee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeMemoryRepository employeeMemoryRepository;

    public EmployeeService(EmployeeMemoryRepository employeeMemoryRepository) {
        this.employeeMemoryRepository = employeeMemoryRepository;
    }

    public Employee addEmployee(Employee employee) {

        if (employee.getAge() < 18 || employee.getAge() > 65) {
            throw new OutsideAgeRangeEmployee("Employee age must be between 18 and 65.");
        }

        if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new Over30YearsOldSalaryLessThan20000("Employees over 30 must have a salary of at least 10000.");
        }
        employee.setStatus(true);
        return employeeMemoryRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeMemoryRepository.findById(id);
    }
    public List<Employee> getAllEmployees() {
        return employeeMemoryRepository.findAll();
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee currentEmployee = employeeMemoryRepository.findById(id);
        if (!currentEmployee.getStatus()) {
            throw new UpdateInactiveEmployee("Cannot update an inactive employee.");
        }
        return employeeMemoryRepository.updateById(id, updatedEmployee);
    }

    public void deleteEmployee(int id) {

        Employee currentEmployee = employeeMemoryRepository.findById(id);
        currentEmployee.setStatus(false);
        employeeMemoryRepository.updateById(id, currentEmployee);
    }
}
