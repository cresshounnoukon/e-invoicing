package org.facturenormalise.service;

import lombok.RequiredArgsConstructor;
import org.facturenormalise.payload.request.DgiInvoice;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.GetDgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class DgiInvoiceServiceImpl {

    private final DgiInvoiceService dgiInvoiceService;

    public DgiInvoiceResponse saveInvoice(DgiInvoice dgiInvoice, String token){
        return dgiInvoiceService.saveInvoice(dgiInvoice, token);
    }

    public InvoiceConfirmResponse confirmInvoice(UUID uuid, String token){
        return dgiInvoiceService.confirmInvoice(uuid, token);
    }

    public InvoiceConfirmResponse cancelInvoice(UUID uuid, String token){
        return dgiInvoiceService.cancelInvoice(uuid, token);
    }

    public GetDgiInvoiceResponse getDgiInvoiceResponse( String token){
        return dgiInvoiceService.getDgiInvoice(token);
    }

}
