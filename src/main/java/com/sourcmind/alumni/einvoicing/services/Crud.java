package com.sourcmind.alumni.einvoicing.services;

import com.sourcmind.alumni.einvoicing.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface Crud<T> {
    Page<T> readAll(Pageable pageable);
    T save(T t);
    T readById(UUID uuid);

    void delete(T t);
}
