package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.TypeInvoiceConverter;
import com.sourcmind.alumni.einvoicing.entities.TypeInvoice;
import com.sourcmind.alumni.einvoicing.payloads.requests.TypeInvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TypeInvoiceResponse;
import com.sourcmind.alumni.einvoicing.services.impl.TypeInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TypeInvoiceFacade {

    private final TypeInvoiceConverter converter;
    private final TypeInvoiceService service;

    public Page<TypeInvoiceResponse> readAll(Pageable pageable) {
        return service.readAll(pageable).map(converter::convert);
    }

    public TypeInvoiceResponse create(TypeInvoiceRequest request) {
        service.existsByCode(request.getCode());
        TypeInvoice typeInvoice = converter.convert(request);
        if (request.getReimbursementId() != null) {
            TypeInvoice reimbursement = service.readById(request.getReimbursementId());
            typeInvoice.setReimbursement(reimbursement);
        }
        return converter.convert(service.save(typeInvoice));

    }


    public TypeInvoiceResponse readByCode(String code) {
        return converter.convert(service.readByCode(code));
    }

    public TypeInvoiceResponse readById(UUID id) {
        return converter.convert(service.readById(id));
    }

    public void delete(UUID id) {
        service.delete(service.readById(id));
    }

    public TypeInvoiceResponse update(TypeInvoiceRequest request) {
        return converter.convert(service.save(converter.convert(request)));
    }
}
