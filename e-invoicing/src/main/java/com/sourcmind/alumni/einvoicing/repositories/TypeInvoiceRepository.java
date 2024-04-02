package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.TypeInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TypeInvoiceRepository extends JpaRepository<TypeInvoice, UUID> {
    Optional<TypeInvoice> findByCode(String code);
    boolean existsByCode(String code);
}
