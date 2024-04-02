package com.sourcmind.alumni.einvoicing.services.impl;

import com.sourcmind.alumni.einvoicing.entities.TypeInvoice;
import com.sourcmind.alumni.einvoicing.exceptions.AlreadyExistException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.exceptions.NotFoundException;
import com.sourcmind.alumni.einvoicing.repositories.TypeInvoiceRepository;
import com.sourcmind.alumni.einvoicing.services.CodeReadable;
import com.sourcmind.alumni.einvoicing.services.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TypeInvoiceService implements Crud<TypeInvoice>, CodeReadable<TypeInvoice> {
    private final TypeInvoiceRepository repository;
    private static  final String TYPE="TypeInvoice";


    @Override
    public Page<TypeInvoice> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public TypeInvoice save(TypeInvoice taxGroup) {
        return repository.save(taxGroup);
    }

    @Override
    public TypeInvoice readById(UUID uuid) {
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
    public TypeInvoice readByCode(String code) {
        return repository.findByCode(code).orElseThrow(
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
    public void delete(TypeInvoice taxGroup) {
        repository.delete(taxGroup);
    }

    @Override
    public boolean existsByCode(String code) {
         if( repository.existsByCode(code)){
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
