package com.coveros.training.flavorhub.controller;

import com.coveros.training.flavorhub.model.Employee;
import com.coveros.training.flavorhub.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST Controller for managing employees
 */
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;
    
    /**
     * Get all employees
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    /**
     * Get employee by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Get employee by username
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {
        return employeeService.getEmployeeByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new employee
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        // Check if username already exists
        if (employeeService.usernameExists(employee.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }
    
    /**
     * Update an existing employee
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return employeeService.getEmployeeById(id)
                .map(existingEmployee -> {
                    // Check if username is being changed and if it already exists
                    if (!existingEmployee.getUsername().equals(employee.getUsername()) 
                            && employeeService.usernameExists(employee.getUsername())) {
                        return ResponseEntity.badRequest().<Employee>build();
                    }
                    employee.setId(id);
                    Employee updatedEmployee = employeeService.saveEmployee(employee);
                    return ResponseEntity.ok(updatedEmployee);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Delete an employee by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(employee -> {
                    employeeService.deleteEmployee(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
