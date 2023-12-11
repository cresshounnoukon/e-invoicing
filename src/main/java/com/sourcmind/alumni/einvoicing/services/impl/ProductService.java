package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.Product;
import com.sourcmind.alumni.einvoicing.exceptions.AlreadyExistException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.ProductRepository;
import com.sourcmind.alumni.einvoicing.services.CodeReadable;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService implements Crud<Product>, CodeReadable<Product> {
    private static final String TYPE = "Product";
    private final ProductRepository productRepository;

    @Override
    public Page<Product> readAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product readById(UUID uuid) {
        return productRepository.findById(uuid).orElseThrow(
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
    public Product readByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(
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
        return productRepository.existsByCode(code);
    }

    public void checkCode(String code) {
        if (existsByCode(code)) {
            throw new AlreadyExistException(
                    Error.builder()
                            .type(TYPE)
                            .message(String.format("Product already exist  with  %s", code))
                            .field("code")
                            .value(code)
                            .build()
            );
        }
    }

    @Override
    public void delete(Product category) {
        productRepository.delete(category);
    }
}
