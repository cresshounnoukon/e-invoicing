package org.facturenormalise.service;

import org.facturenormalise.payload.request.DgiInvoice;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.GetDgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(value = "dgiInvoice", url = "${dgi.url}")
public interface DgiInvoiceService {

    @PostMapping
    public DgiInvoiceResponse saveInvoice(@RequestBody DgiInvoice dgiInvoice, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PutMapping("{uuid}/confirm")
    public InvoiceConfirmResponse confirmInvoice(@PathVariable UUID uuid, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PutMapping("{uuid}/cancel")
    public InvoiceConfirmResponse cancelInvoice(@PathVariable UUID uuid, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @GetMapping
    public GetDgiInvoiceResponse getDgiInvoice(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

}
