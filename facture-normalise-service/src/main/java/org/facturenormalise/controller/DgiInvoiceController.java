package org.facturenormalise.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.facturenormalise.enums.Action;
import org.facturenormalise.facade.DgiInvoiceFacade;
import org.facturenormalise.payload.request.DgiInvoice;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.GetDgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("dgiInvoice")
@Slf4j
public class DgiInvoiceController {

    private final DgiInvoiceFacade dgiInvoiceFacade;

    @GetMapping()
    public GetDgiInvoiceResponse getDgiInvoiceResponse(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        return dgiInvoiceFacade.getDgiInvoices(token);
    }

    @PostMapping
    public DgiInvoiceResponse saveDgiInvoice(@RequestBody DgiInvoice dgiInvoice, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        log.info("test");
        return dgiInvoiceFacade.saveInvoice(dgiInvoice, token);
    }

    @PutMapping("{uuid}/confirm")
    public InvoiceConfirmResponse confirmDgiInvoice(@PathVariable("uuid") UUID uuid, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return dgiInvoiceFacade.confirmInvoice(uuid, token);
    }

    @PutMapping("{uuid}/cancel")
    public InvoiceConfirmResponse cancelDgiInvoice(@PathVariable("uuid") UUID uuid, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return dgiInvoiceFacade.confirmInvoice(uuid, token);
    }

    @PostMapping("normalize/{action}")
    public InvoiceConfirmResponse normalizeInvoice(@RequestBody DgiInvoice dgiInvoice, @PathVariable("action") Action action, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        log.info("test");
        return dgiInvoiceFacade.normalize(dgiInvoice, token, action);
    }

}
