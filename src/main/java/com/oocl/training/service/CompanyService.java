package com.oocl.training.service;

import com.oocl.training.DAO.CompanyRepo;
import com.oocl.training.Model.Company;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> getAllCompanies() {
       return companyRepo.findAll();
    }

    public void postCompany(Company company) {
        companyRepo.save(company);

    }
}
