package org.facturenormalise.facade;

import lombok.RequiredArgsConstructor;
import org.facturenormalise.enums.Action;
import org.facturenormalise.exceptions.EmcfProcessingException;
import org.facturenormalise.exceptions.Error;
import org.facturenormalise.payload.request.DgiInvoice;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.GetDgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.facturenormalise.service.DgiInvoiceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DgiInvoiceFacade {

    private final DgiInvoiceServiceImpl dgiInvoiceServiceImp;

    public DgiInvoiceResponse saveInvoice(DgiInvoice dgiInvoice, String token) {

        DgiInvoiceResponse dgiInvoiceResponse = dgiInvoiceServiceImp.saveInvoice(dgiInvoice, token);

        if (dgiInvoiceResponse.getErrorCode() != null)
            throw new EmcfProcessingException(Error.builder()
                .errorCode(dgiInvoiceResponse.getErrorCode())
                .errorDesc(dgiInvoiceResponse.getErrorDesc())
                .build());

        return dgiInvoiceResponse;
    }

    public InvoiceConfirmResponse confirmInvoice(UUID uuid, String token) {

        InvoiceConfirmResponse invoiceConfirmResponse = dgiInvoiceServiceImp.confirmInvoice(uuid, token);
        if (invoiceConfirmResponse.getErrorCode() != null)
            throw new EmcfProcessingException(Error.builder()
                .errorCode(invoiceConfirmResponse.getErrorCode())
                .errorDesc(invoiceConfirmResponse.getErrorDesc())
                .build());

        return invoiceConfirmResponse;
    }

    public InvoiceConfirmResponse cancelInvoice(UUID uuid, String token) {
        return dgiInvoiceServiceImp.cancelInvoice(uuid, token);
    }

    public GetDgiInvoiceResponse getDgiInvoices(String token) {
        return dgiInvoiceServiceImp.getDgiInvoiceResponse(token);
    }

    public InvoiceConfirmResponse normalize(DgiInvoice dgiInvoice, String token, Action action) {
        DgiInvoiceResponse dgiInvoiceResponse = saveInvoice(dgiInvoice, token);
        String uid = dgiInvoiceResponse.getUid();
        if (uid == null)
            throw new EmcfProcessingException(Error.builder()
                .errorCode(dgiInvoiceResponse.getErrorCode())
                .errorDesc(dgiInvoiceResponse.getErrorDesc())
                .build());
        if (action == Action.confirm) {
            return confirmInvoice((UUID.fromString(dgiInvoiceResponse.getUid())), token);
        } else {
            return cancelInvoice((UUID.fromString(dgiInvoiceResponse.getUid())), token);
        }
    }
}
