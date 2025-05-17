package com.blagovestkabov.springbootdemo.service;

import com.blagovestkabov.springbootdemo.exception.EmployeeNFException;

import com.blagovestkabov.springbootdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeSrvcImpl implements EmployeeService{

    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {

        if(employee.getEmployeeId() == null ||
                employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    @Override
    public Employee getById(String id) {
        return employees
                .stream()
                .filter(employee -> employee.getEmployeeId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNFException("" +
                        "Employee not found with Id: " + id));
    }

    @Override
    public String deleteById(String id) {
        Employee employee
                = employees
                .stream()
                .filter(e -> e.getEmployeeId().equalsIgnoreCase(id))
                .findFirst()
                .get();

        employees.remove(employee);
        return "Employee deleted with the id: " + id;
    }
}
