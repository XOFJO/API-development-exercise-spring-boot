package com.oocl.training.service;

import com.oocl.training.DAO.CompanyRepo;
import com.oocl.training.Model.Company;
import com.oocl.training.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyService {
    private final CompanyRepo companyRepo;

    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> getAllCompanies(Integer page, Integer size) {
        List<Company> companies = companyRepo.findAll();
        if (page != null && size != null && page > 0 && size > 0) {
            int fromIndex = (page - 1) * size;
            int toIndex = Math.min(fromIndex + size, companies.size());

            if (fromIndex >= companies.size()) {
                return new ArrayList<>();
            }

            return companies.subList(fromIndex, toIndex);
        }
        return companies;
    }

    public void postCompany(Company company) {
        companyRepo.save(company);

    }

    public Company getCompanyById(int id) {
        return companyRepo.findById(id);

    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        Company company = companyRepo.findById(id);
        return companyRepo.getAllEmployees(company);
    }

    public Company updateCompanyById(int id, Company updatedCompany) {
        Company company = companyRepo.findById(id);
        if (company != null) {
            company = companyRepo.updateCompany(id, updatedCompany);
        }
        return company;
    }

    public void deleteCompanyById(int id) {
        Company company = companyRepo.findById(id);
        if (company != null) {
            companyRepo.deleteCompany(id);
        }
    }
}
