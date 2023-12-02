package com.sourcmind.alumni.einvoicing.repositories;

import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.entities.PointOfSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PointOfSaleRepository extends JpaRepository<PointOfSale, UUID> {
}
