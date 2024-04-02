package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.entities.Company;
import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaxGroupRepository extends JpaRepository<TaxGroup, UUID> {
    Optional<TaxGroup> findByCode(String code);
    boolean existsByCode(String code);
}
