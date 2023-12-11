package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import com.sourcmind.alumni.einvoicing.exceptions.AlreadyExistException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.TaxGroupRepository;
import com.sourcmind.alumni.einvoicing.services.CodeReadable;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaxGroupService implements Crud<TaxGroup>, CodeReadable<TaxGroup> {
    private final TaxGroupRepository taxGroupRepository;
    private static  final String TYPE="Taxgroup";


    @Override
    public Page<TaxGroup> readAll(Pageable pageable) {
        return taxGroupRepository.findAll(pageable);
    }

    @Override
    public TaxGroup save(TaxGroup taxGroup) {
        return taxGroupRepository.save(taxGroup);
    }

    @Override
    public TaxGroup readById(UUID uuid) {
        return taxGroupRepository.findById(uuid).orElseThrow(
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
    public TaxGroup readByCode(String code) {
        return taxGroupRepository.findByCode(code).orElseThrow(
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
    public void delete(TaxGroup taxGroup) {
        taxGroupRepository.delete(taxGroup);
    }

    @Override
    public boolean existsByCode(String code) {
         if( taxGroupRepository.existsByCode(code)){
           throw  new AlreadyExistException(
                   Error.builder()
                           .type(TYPE)
                           .message(String.format("Already exist %s", code))
                           .field("code")
                           .value(code)
                           .build()
           );
         }else {
             return  false;
         }

    }
}
