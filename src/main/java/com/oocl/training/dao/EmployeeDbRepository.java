package com.oocl.training.dao;

import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDbRepository implements EmployeeRepository {
    JpaEmployeeRepository repository;
    public EmployeeDbRepository(JpaEmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee updateById(int id, Employee updatedEmployee) {
        if (updatedEmployee.getId() != id) {
            throw new IllegalArgumentException("ID in path and request body do not match");
        }
        return repository.save(updatedEmployee);
    }

    @Override
    public void deleteById(int id){
        repository.deleteById(id);
    }

    @Override
    public void clearAll() {
        repository.deleteAll();

    }
}
