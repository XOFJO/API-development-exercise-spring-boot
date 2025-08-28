package com.oocl.training.dao;

import com.oocl.training.model.Employee;

import java.util.List;

public interface EmployeeRepository {


    public Employee save(Employee employee);
    public Employee findById(int id);
    public List<Employee> findAll();
    public Employee updateById(int id, Employee updatedEmployee);
    public void deleteById(int id);
    public void clearAll();


}
