package com.blagovestkabov.springbootdemo.service;

import com.blagovestkabov.springbootdemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAll();

    Employee getById(String employeeId);

    String deleteById(String employeeId);
}
