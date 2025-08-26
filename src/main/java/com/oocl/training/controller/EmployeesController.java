package com.oocl.training.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/")
public class EmployeesController {
    private Map<Integer, Employee> employees = new HashMap<>();
    private int nextEmployeeId = 5;


    public EmployeesController() {
        employees.put(1, new Employee(1, "John", 25, "Male", 8000));
        employees.put(2, new Employee(2, "Lily", 20, "Female", 8000));
        employees.put(3, new Employee(3, "Bob", 30, "Male", 9000));
        employees.put(4, new Employee(4, "Alice", 28, "Female", 10000));
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(nextEmployeeId);
        employees.put(nextEmployeeId, employee);
        nextEmployeeId++;
        return employee;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employees.get(id);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        List<Employee> allEmployees = new ArrayList<>(employees.values());

        if (gender != null) {
            allEmployees = allEmployees.stream()
                    .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                    .collect(Collectors.toList());
        }

        if (page != null && size != null) {
            int startIndex = (page - 1) * size;
            int endIndex = Math.min(startIndex + size, allEmployees.size());

            if (startIndex >= allEmployees.size()) {
                return new ArrayList<>();
            }

            return allEmployees.subList(startIndex, endIndex);
        }

        return allEmployees;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        Employee existingEmployee = employees.get(id);
        if (existingEmployee != null) {
            existingEmployee.setAge(updatedEmployee.getAge());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return existingEmployee;
        }
        return null;
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employees.remove(id);
    }
}