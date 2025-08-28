package com.oocl.training.dao;

import com.oocl.training.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> getByGender(String Gender);
    List<Employee> getByAgeBetween(int min, int max);

}
