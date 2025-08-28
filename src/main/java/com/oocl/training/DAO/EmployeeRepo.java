package com.oocl.training.DAO;

import com.oocl.training.Model.Company;
import com.oocl.training.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepo {
    private Map<Integer, Employee> employees = new HashMap<>();
    private int nextEmployeeId = 5;
    public EmployeeRepo() {
        employees.put(1, new Employee(1, "John", 25, "Male", 8000));
        employees.put(2, new Employee(2, "Lily", 20, "Female", 8000));
        employees.put(3, new Employee(3, "Bob", 30, "Male", 9000));
        employees.put(4, new Employee(4, "Alice", 28, "Female", 10000));
    }

    public Employee save(Employee employee) {
        employee.setId(nextEmployeeId);
        employees.put(nextEmployeeId, employee);
        nextEmployeeId++;
        return employee;
    }


    public Employee findById(int id) {
        return employees.get(id);
    }

    public List<Employee> findAll() {
        return employees.values().stream().toList();
    }

    public Employee updateById(int id, Employee updatedEmployee) {
        Employee existingEmployee = employees.get(id);
        if (existingEmployee != null) {
            employees.replace(id, updatedEmployee);
            updatedEmployee.setId(id);
            return updatedEmployee;
        }
        return null;
    }

    public void deleteById(int id){
        employees.remove(id);
    }

    public void clearAll() {
        employees.clear();
        nextEmployeeId = 1;
    }
}

