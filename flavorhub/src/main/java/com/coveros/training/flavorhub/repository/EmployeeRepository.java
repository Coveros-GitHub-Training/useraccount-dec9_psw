package com.coveros.training.flavorhub.repository;

import com.coveros.training.flavorhub.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing Employee entities
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Find an employee by username
     */
    Optional<Employee> findByUsername(String username);
    
    /**
     * Check if an employee exists with the given username
     */
    boolean existsByUsername(String username);
}
