package com.springbootexample.SpringActuatorExample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.springbootexample.SpringActuatorExample.entity.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee) throws ResourceNotFoundException;
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}