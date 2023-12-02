package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
