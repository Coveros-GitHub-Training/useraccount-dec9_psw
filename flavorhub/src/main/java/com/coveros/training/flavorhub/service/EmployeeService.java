package com.coveros.training.flavorhub.service;

import com.coveros.training.flavorhub.model.Employee;
import com.coveros.training.flavorhub.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing employees
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    /**
     * Get all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    /**
     * Get employee by ID
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    
    /**
     * Get employee by username
     */
    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
    
    /**
     * Save or update an employee
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    /**
     * Delete an employee by ID
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    
    /**
     * Check if username already exists
     */
    public boolean usernameExists(String username) {
        return employeeRepository.existsByUsername(username);
    }
}
