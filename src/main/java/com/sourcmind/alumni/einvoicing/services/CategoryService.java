package com.sourcmind.alumni.einvoicing.services;

import com.sourcmind.alumni.einvoicing.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface CategoryService {

    Page<Category> readAll(Pageable pageable);
    Category save(Category category);
    Category readById(UUID uuid);
    Category readByCode(String code);
    void delete(Category category);

}
