package org.facturenormalise.facade;

import lombok.RequiredArgsConstructor;
import org.facturenormalise.enums.Action;
import org.facturenormalise.payload.request.DgiInvoice;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.GetDgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.facturenormalise.service.DgiInvoiceServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DgiInvoiceFacade {

    private final DgiInvoiceServiceImpl dgiInvoiceServiceImp;

    public DgiInvoiceResponse saveInvoice(DgiInvoice dgiInvoice, String token) {
        return dgiInvoiceServiceImp.saveInvoice(dgiInvoice, token);
    }

    public InvoiceConfirmResponse confirmInvoice(UUID uuid, String token) {
        return dgiInvoiceServiceImp.confirmInvoice(uuid, token);
    }

    public InvoiceConfirmResponse cancelInvoice(UUID uuid, String token) {
        return dgiInvoiceServiceImp.cancelInvoice(uuid, token);
    }

    public GetDgiInvoiceResponse getDgiInvoices(String token) {
        return dgiInvoiceServiceImp.getDgiInvoiceResponse(token);
    }

    public InvoiceConfirmResponse normalize(DgiInvoice dgiInvoice, String token, Action action) {
        DgiInvoiceResponse dgiInvoiceResponse = saveInvoice(dgiInvoice, token);
        if (action == Action.confirm) {
            return confirmInvoice((UUID.fromString(dgiInvoiceResponse.getUid())), token);
        }else{
            return cancelInvoice((UUID.fromString(dgiInvoiceResponse.getUid())), token);
        }
    }
}
