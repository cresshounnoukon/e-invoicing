package com.sourcmind.alumni.einvoicing.controllers;

import java.util.UUID;

import com.sourcmind.alumni.einvoicing.enums.Action;
import com.sourcmind.alumni.einvoicing.facades.EmcfInvoiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("emcf-invoice")
public class EmcfInvoiceController {

    private final EmcfInvoiceFacade emcfInvoiceFacade;

    @PutMapping("{invoiceId}")
    public Mono<ResponseEntity<?>> save(@PathVariable("invoiceId") UUID uuid) {
        return emcfInvoiceFacade.save(uuid);
    }

    @PutMapping("{emcfInvoiceId}/{action}")
    public Mono<ResponseEntity<?>> confirm(@PathVariable("emcfInvoiceId") UUID uuid, @PathVariable Action action) {
        return emcfInvoiceFacade.confirm(action, uuid);
    }
}
