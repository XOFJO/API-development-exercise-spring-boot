package com.oocl.training.service;

import com.oocl.training.dao.CompanyMemoryRepository;
import com.oocl.training.dao.CompanyRepository;
import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyService {
    private final CompanyRepository companyDbRepository;


    public CompanyService(CompanyMemoryRepository companyDbRepository) {
        this.companyDbRepository = companyDbRepository;
    }

    public List<Company> getAllCompanies(Integer page, Integer size) {
        List<Company> companies = companyDbRepository.findAll();
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
        companyDbRepository.save(company);

    }

    public Company getCompanyById(int id) {
        return companyDbRepository.findById(id);

    }

//    public List<Employee> getEmployeesByCompanyId(int id) {
//        Company company = companyDbRepository.findById(id);
//        return companyDbRepository.getAllEmployees(company);
//    }

    public Company updateCompanyById(int id, Company updatedCompany) {
        Company company = companyDbRepository.findById(id);
        if (company != null) {
            company = companyDbRepository.updateCompany(id, updatedCompany);
        }
        return company;
    }

    public void deleteCompanyById(int id) {
        Company company = companyDbRepository.findById(id);
        if (company != null) {
            companyDbRepository.deleteCompany(id);
        }
    }
}
