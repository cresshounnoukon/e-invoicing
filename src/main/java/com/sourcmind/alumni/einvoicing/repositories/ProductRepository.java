package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.Product;
import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
