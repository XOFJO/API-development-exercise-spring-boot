package com.oocl.training.dao;

import com.oocl.training.model.Company;
import com.oocl.training.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyRepo {
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
    private int nextCompanyId = 6;


    public List<Company> findAll() {
        return companies.values().stream().toList();
    }

    public void save(Company company) {
        company.setId(nextCompanyId);
        companies.put(nextCompanyId, company);
        nextCompanyId++;
    }

    public Company findById(int id) {
        return companies.get(id);
    }

    public List<Employee> getAllEmployees(Company company) {
        return company.getEmployees();
    }

    public Company updateCompany(int id, Company updatedCompany) {
        Company existingCompany = companies.get(id);
        if (existingCompany != null) {
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setEmployees(updatedCompany.getEmployees());
            companies.put(id, existingCompany);
        }
        return existingCompany;
    }

    public void deleteCompany(int id) {
        companies.remove(id);
    }
}
