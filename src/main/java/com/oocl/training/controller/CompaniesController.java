package com.oocl.training.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class CompaniesController {
    private Map<Integer, Company> companies = new HashMap<>();
    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody Company company) {
        int newId = companies.size() + 1;
        company.setId(newId);
        companies.put(newId, company);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return new ArrayList<>(companies.values());
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
            existingCompany.setName(updatedCompany.getName());
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