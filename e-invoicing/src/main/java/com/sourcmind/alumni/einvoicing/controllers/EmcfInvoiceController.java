package com.sourcmind.alumni.einvoicing.controllers;

import java.util.UUID;

import com.sourcmind.alumni.einvoicing.enums.Action;
import com.sourcmind.alumni.einvoicing.facades.EmcfInvoiceFacade;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.InvoiceRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("emcf-invoice")
@Slf4j
public class EmcfInvoiceController {

    private final EmcfInvoiceFacade emcfInvoiceFacade;

    @PostMapping
    public Mono<ResponseEntity<?>> save(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        log.info("Invoice Data", invoiceRequestDto);
        return emcfInvoiceFacade.save(invoiceRequestDto.invoiceId());
    }

    @PutMapping("{emcfInvoiceId}/{action}")
    public Mono<ResponseEntity<?>> confirm(@PathVariable("emcfInvoiceId") UUID uuid, @PathVariable Action action) {
        return emcfInvoiceFacade.confirm(action, uuid);
    }
}
