package com.sourcmind.alumni.einvoicing.services;

import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface TaxGroupService {

    Page<TaxGroup> readAll(Pageable pageable);
    TaxGroup save(TaxGroup category);
    TaxGroup readById(UUID uuid);
    TaxGroup readByCode(String code);
    void delete(TaxGroup category);

    boolean existsByCode(String code);

}
