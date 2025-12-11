package com.coveros.training.flavorhub.service;

import com.coveros.training.flavorhub.model.Company;
import com.coveros.training.flavorhub.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing company information
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    
    /**
     * Get all companies
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    
    /**
     * Get company by ID
     */
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }
    
    /**
     * Save or update a company
     */
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
    
    /**
     * Delete a company by ID
     */
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
