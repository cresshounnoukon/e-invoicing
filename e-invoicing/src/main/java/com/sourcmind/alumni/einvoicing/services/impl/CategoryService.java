package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.Category;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.CategoryRepository;
import com.sourcmind.alumni.einvoicing.services.CodeReadable;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService implements Crud<Category>, CodeReadable<Category> {
    private final CategoryRepository categoryRepository;
    private static  final String TYPE="Categorty";


    @Override
    public Page<Category> readAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category readById(UUID uuid) {
        return categoryRepository.findById(uuid).orElseThrow(
                () -> new NotFoundException(
                        Error.builder()
                                .type(TYPE)
                                .message(String.format("NOT FOUND %s", uuid))
                                .field("uuid")
                                .value(uuid)
                                .build()
                )
        );
    }

    @Override
    public Category readByCode(String code) {
        return categoryRepository.findByCode(code).orElseThrow(
                () -> new NotFoundException(
                        Error.builder()
                                .type(TYPE)
                                .message(String.format("NOT FOUND %s", code))
                                .field("code")
                                .value(code)
                                .build()
                )
        );
    }

    @Override
    public boolean existsByCode(String code) {
        return categoryRepository.existsByCode(code);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
