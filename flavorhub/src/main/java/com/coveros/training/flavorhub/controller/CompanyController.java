package com.coveros.training.flavorhub.controller;

import com.coveros.training.flavorhub.model.Company;
import com.coveros.training.flavorhub.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * REST Controller for managing company information
 */
@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    
    private final CompanyService companyService;
    
    /**
     * Get all companies
     */
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    
    /**
     * Get company by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Create a new company
     */
    @PostMapping
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        Company savedCompany = companyService.saveCompany(company);
        return ResponseEntity.ok(savedCompany);
    }
    
    /**
     * Update an existing company
     */
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
        return companyService.getCompanyById(id)
                .map(existingCompany -> {
                    company.setId(id);
                    Company updatedCompany = companyService.saveCompany(company);
                    return ResponseEntity.ok(updatedCompany);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Delete a company by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(company -> {
                    companyService.deleteCompany(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
