package com.sourcmind.alumni.einvoicing.controllers;

import com.sourcmind.alumni.einvoicing.facades.CompanyFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.CompanyRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("companies")
public class CompanyController {
    private  final CompanyFacade facade;



    @GetMapping
    public Page<CompanyResponse> readAll(Pageable pageable) {
        return facade.readAll(pageable);
    }

    @PostMapping
    public CompanyResponse create(@RequestBody  CompanyRequest request) {
        return facade.create(request);
    }

    @GetMapping("/{uuid}")
    public CompanyResponse readById(@PathVariable UUID uuid) {
        return facade.readById(uuid);
    }

    @PutMapping("/{uuid}")
    public CompanyResponse update(@PathVariable UUID uuid, CompanyRequest request) {
        return facade.update(request);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        facade.delete(uuid);
    }


}
