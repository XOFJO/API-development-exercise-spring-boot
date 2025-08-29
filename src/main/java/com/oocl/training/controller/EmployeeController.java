package com.oocl.training.controller;
import com.oocl.training.model.Employee;
import com.oocl.training.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.oocl.training.controller.mapper.EmployeeMapper;
import com.oocl.training.controller.dto.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody Employee employee) {
        return employeeMapper.toResponse(employeeService.addEmployee(employee));
    }

    @GetMapping("/employees/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable int id) {

        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @GetMapping("/employees")
    public List<EmployeeResponse> getEmployees(@RequestParam(required = false) String gender,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        List<EmployeeResponse> allEmployees = employeeService.getAllEmployees()
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());

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
    public EmployeeResponse updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
        return employeeMapper.toResponse(employeeService.updateEmployee(id, updatedEmployee));
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

}