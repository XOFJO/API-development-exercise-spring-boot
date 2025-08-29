package com.oocl.training.dao;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CompanyRepository {

    public List<Company> findAll();

    public void save(Company company);

    public Company findById(int id);

    public Company updateCompany(int id, Company updatedCompany);

    public void deleteCompany(int id);
}
