package com.sourcmind.alumni.einvoicing.controllers;

import com.sourcmind.alumni.einvoicing.facades.TaxGroupFacade;
import com.sourcmind.alumni.einvoicing.facades.TypeInvoiceFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.TypeInvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TypeInvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("type-invoices")
public class TypeInvoiceController {
    private  final TypeInvoiceFacade facade;



    @GetMapping
    public Page<TypeInvoiceResponse> readAll(Pageable pageable) {
        return facade.readAll(pageable);
    }

    @PostMapping
    public TypeInvoiceResponse create(@RequestBody  TypeInvoiceRequest request) {
        return facade.create(request);
    }

    @GetMapping("/{uuid}")
    public TypeInvoiceResponse readById(@PathVariable UUID uuid) {
        return facade.readById(uuid);
    }

    @GetMapping("/code/{code}")
    public TypeInvoiceResponse readByCode(@PathVariable String code) {
        return facade.readByCode(code);
    }

    @PutMapping("/{uuid}")
    public TypeInvoiceResponse update(@PathVariable UUID uuid, TypeInvoiceRequest request) {
        return facade.update(request);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        facade.delete(uuid);
    }


}
