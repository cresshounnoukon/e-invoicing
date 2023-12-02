package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
