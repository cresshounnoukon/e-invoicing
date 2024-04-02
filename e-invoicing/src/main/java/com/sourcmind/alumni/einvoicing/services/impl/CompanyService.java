package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.Company;
import com.sourcmind.alumni.einvoicing.exceptions.AlreadyExistException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.CompanyRepository;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompanyService implements Crud<Company> {
    private static final String TYPE = "Company";
    private final CompanyRepository repository;

    @Override
    public Page<Company> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Company save(Company data) {
        return repository.save(data);
    }

    @Override
    public Company readById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(
                () -> new NotFoundException(
                        Error.builder()
                                .type(TYPE)
                                .message(String.format("Company is not found  with  %s ", uuid))
                                .field("uuid")
                                .value(uuid)
                                .build()
                )
        );
    }


    public Company readByTin(String tin) {
        return repository.findByTin(tin).orElseThrow(
                () -> new NotFoundException(
                        Error.builder()
                                .type(TYPE)
                                .message(String.format("NOT FOUND %s", tin))
                                .field("tin")
                                .value(tin)
                                .build()
                )
        );
    }


    public Company readByTradeNo(String value) {
        return repository.findByTin(value).orElseThrow(
                () -> new NotFoundException(
                        Error.builder()
                                .type(TYPE)
                                .message(String.format("NOT FOUND %s", value))
                                .field("tradeNo")
                                .value(value)
                                .build()
                )
        );
    }


    public void checkTin(String value) {
        if (repository.existsByTin(value)) {
            throw new AlreadyExistException(
                    Error.builder()
                            .type(TYPE)
                            .message(String.format("Company already exist  with  %s", value))
                            .field("tin")
                            .value(value)
                            .build()
            );

        }
    }

    public void checkTradeNo(String value) {
        if (repository.existsByTradeNo(value)) {
            throw new AlreadyExistException(
                    Error.builder()
                            .type(TYPE)
                            .message(String.format("Company already exist  with  %s", value))
                            .field("tradeNo")
                            .value(value)
                            .build()
            );

        }
    }


    @Override
    public void delete(Company data) {
        repository.delete(data);
    }

}
