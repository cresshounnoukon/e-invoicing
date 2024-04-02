package com.sourcmind.alumni.einvoicing.controllers;

import com.sourcmind.alumni.einvoicing.facades.ProductFacade;
import com.sourcmind.alumni.einvoicing.facades.TaxGroupFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.ProductRequest;
import com.sourcmind.alumni.einvoicing.payloads.requests.TaxGroupRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.ProductResponse;
import com.sourcmind.alumni.einvoicing.payloads.responses.TaxGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductFacade facade;


    @GetMapping
    public Page<ProductResponse> readAll(Pageable pageable) {
      return   facade.readAll(pageable);
    }


    @PostMapping
    public ProductResponse create( @RequestBody  ProductRequest request) {
        return facade.create(request);
    }



    @GetMapping("/{uuid}")
    public ProductResponse readByid( @PathVariable  UUID uuid) {
        return facade.readByid(uuid);
    }

    @PutMapping("/{uuid}")
    public ProductResponse update( @PathVariable  UUID uuid, @RequestBody  ProductRequest request) {

        return facade.update(uuid,request);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable  UUID uuid) {
        facade.delete(uuid);
    }


}
