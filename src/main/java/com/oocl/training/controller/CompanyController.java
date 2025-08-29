package com.oocl.training.controller;
import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import com.oocl.training.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCompany(@RequestBody Company company) {
        companyService.postCompany(company);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer size) {
        return companyService.getAllCompanies(page, size);
    }


    @GetMapping("/companies/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

//    @GetMapping("/companies/{id}/employees")
//    public List<Employee> getEmployeesByCompanyById(@PathVariable int id) {
//        return companyService.getEmployeesByCompanyId(id);
//    }

    @PutMapping("/companies/{id}")
    public Company updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
        return companyService.updateCompanyById(id, updatedCompany);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }
}