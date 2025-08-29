package com.oocl.training.dao;


import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDbRepository implements CompanyRepository{
    JpaCompanyRepository repository;
    public CompanyDbRepository(JpaCompanyRepository repository) {
        this.repository = repository;
    };

    @Override
    public List<Company> findAll() {
        return repository.findAll();
    }
    @Override
    public void save(Company company) {
        repository.save(company);
    }
    @Override
    public Company findById(int id) {
        return repository.findById(id).get();
    }
    @Override
    public Company updateCompany(int id, Company updatedCompany) {
        if (updatedCompany.getId() != id) {
            throw new IllegalArgumentException("ID in path and request body do not match");
        }
        return repository.save(updatedCompany);
    }
    @Override
    public void deleteCompany(int id) {
        repository.deleteById(id);
    }

}
