package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.Item;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.ItemRepository;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService implements Crud<Item> {
    private static final String TYPE = "Item";
    private final ItemRepository repository;

    @Override
    public Page<Item> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Item save(Item data) {
        return repository.save(data);
    }

    public List<Item> saveAll(List<Item> data) {
        return repository.saveAll(data);
    }
    @Override
    public Item readById(UUID uuid) {
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
    public void delete(Item data) {
        repository.delete(data);
    }

}
