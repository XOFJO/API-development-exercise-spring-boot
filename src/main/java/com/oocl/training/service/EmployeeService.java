package com.oocl.training.service;
import com.oocl.training.DAO.EmployeeRepo;
import com.oocl.training.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {

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
