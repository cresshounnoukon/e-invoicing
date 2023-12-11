package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.Invoice;
import com.sourcmind.alumni.einvoicing.exceptions.AlreadyExistException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.InvoiceRepository;
import com.sourcmind.alumni.einvoicing.services.CodeReadable;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class InvoiceService implements Crud<Invoice> {
    private static final String TYPE = "Invoice";
    private final InvoiceRepository repository;

    @Override
    public Page<Invoice> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Invoice save(Invoice data) {
        return repository.save(data);
    }

    @Override
    public Invoice readById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(
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
    public void delete(Invoice data) {
        repository.delete(data);
    }

}
