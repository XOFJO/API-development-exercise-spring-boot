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
        company.setId(companies.size() + 1);
        companies.put(companies.size() + 1, company);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return new ArrayList<>(companies.values());
    }

}