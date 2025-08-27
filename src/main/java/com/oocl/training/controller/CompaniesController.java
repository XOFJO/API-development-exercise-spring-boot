package com.oocl.training.controller;
import com.oocl.training.Model.Company;
import com.oocl.training.Model.Employee;
import com.oocl.training.service.CompanyService;
import com.oocl.training.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class CompaniesController {
    private final CompanyService companyService;
    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    private final HashMap<Integer, Company> companies = new HashMap<>(Map.of(
            1, new Company(1, "Acme Corporation", List.of(
                    new Employee(1, "John Smith", 32, "Male", 5000.0),
                    new Employee(2, "Jane Johnson", 28, "Female", 6000.0)
            )),
            2, new Company(2, "TechCom Solutions", List.of(
                    new Employee(3, "David Williams", 35,"Male", 5500.0),
                    new Employee(4, "Emily Brown", 23, "Female", 4500.0),
                    new Employee(5, "Michael Jones", 40, "Male", 7000.0)
            )),
            3, new Company(3, "Global Innovators"),
            4, new Company(4, "Stellar Enterprises"),
            5, new Company(5, "Nexus Industries")
    ));
    private int nextCompanyId = 5;

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody Company company) {
        companyService.postCompany(company);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        return companyService.getAllCompanies();
    }


    @GetMapping("/companies/{id}")
    public Company getCompanyById(@PathVariable int id) {
        Company company = companies.get(id);
        return company;
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyById(@PathVariable int id) {
        Company company = companies.get(id);
        List<Employee> employees = company.getEmployees();
        return employees;
    }

    @PutMapping("/companies/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        Company existingCompany = companies.get(id);
        if (existingCompany != null) {
            updatedCompany.setId(id);
            companies.replace(id, updatedCompany);
            return existingCompany;
        }
        return null;
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companies.remove(id);
    }
}