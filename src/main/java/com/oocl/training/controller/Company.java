package com.oocl.training.controller;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private List<Employee> employees;

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }
}
