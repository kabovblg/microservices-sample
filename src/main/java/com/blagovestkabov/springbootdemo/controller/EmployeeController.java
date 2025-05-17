package com.blagovestkabov.springbootdemo.controller;

import com.blagovestkabov.springbootdemo.model.Employee;
import com.blagovestkabov.springbootdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Qualifier("employeeSrvcImpl")
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        return employeeService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable String id) {
        return employeeService.deleteById(id);
    }
}
