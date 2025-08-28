package com.oocl.training.controller;
import com.oocl.training.model.Employee;
import com.oocl.training.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/")
public class EmployeesController {
    private final EmployeeService employeeService;
    public EmployeesController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        List<Employee> allEmployees = employeeService.getAllEmployees();

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
        return employeeService.updateEmployee(id, updatedEmployee);

    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}