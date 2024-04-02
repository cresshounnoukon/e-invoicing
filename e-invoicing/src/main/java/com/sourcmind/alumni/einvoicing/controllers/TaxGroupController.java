package com.sourcmind.alumni.einvoicing.controllers;

import com.sourcmind.alumni.einvoicing.facades.TaxGroupFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.TaxGroupRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TaxGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("tax-groups")
public class TaxGroupController {
    private final TaxGroupFacade facade;

    @GetMapping
    public Page<TaxGroupResponse> readAll(Pageable pageable) {
        return facade.readAll(pageable);
    }

    @PostMapping
    public TaxGroupResponse create(@RequestBody  TaxGroupRequest taxGroupRequest) {
        return facade.create(taxGroupRequest);
    }

    @GetMapping("/{uuid}")
    public TaxGroupResponse readById(@PathVariable UUID uuid) {
        return facade.readById(uuid);
    }

    @GetMapping("/code/{code}")
    public TaxGroupResponse readByCode(@PathVariable String code) {
        return facade.readByCode(code);
    }

    @PutMapping("/{uuid}")
    public TaxGroupResponse update(@PathVariable UUID uuid, TaxGroupRequest taxGroupRequest) {
        return facade.update(taxGroupRequest);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        facade.delete(uuid);
    }


}
