package com.oocl.training.dao;

import com.oocl.training.model.Employee;

import java.util.List;

public class EmployeeDbRepository implements EmployeeRepository {
    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return List.of();
    }

    @Override
    public Employee updateById(int id, Employee updatedEmployee) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
