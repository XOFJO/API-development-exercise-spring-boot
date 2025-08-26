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
    private int nextCompanyId = 0;

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody Company company) {
        company.setId(nextCompanyId);
        companies.put(nextCompanyId, company);
        nextCompanyId++;
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        List<Company> allCompanies = new ArrayList<>(companies.values());

        if (page != null && size != null) {
            int startIndex = (page - 1) * size;
            int endIndex = Math.min(startIndex + size, allCompanies.size());

            if (startIndex >= allCompanies.size()) {
                return new ArrayList<>();
            }

            return allCompanies.subList(startIndex, endIndex);
        }

        return allCompanies;
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