package com.coveros.training.flavorhub.repository;

import com.coveros.training.flavorhub.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Company entities
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
