package com.blagovestkabov.springbootdemo.service;

import com.blagovestkabov.springbootdemo.entity.EmployeeEntity;
import com.blagovestkabov.springbootdemo.model.Employee;
import com.blagovestkabov.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplV2 implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {

        if(employee.getEmployeeId() == null ||
                employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,entity);
        employeeRepository.save(entity);

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> employeeEntityList
                = employeeRepository.findAll();

        List<Employee> employees
                = employeeEntityList
                .stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                })
                .collect(Collectors.toList());

        return employees;
    }

    @Override
    public Employee getById(String id) {
        EmployeeEntity employeeEntity
                = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity,employee);
        return employee;
    }

    @Override
    public String deleteById(String id) {
        employeeRepository.deleteById(id);
        return "Employee deleted with the id: " + id;
    }
}
