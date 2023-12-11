package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.Invoice;
import com.sourcmind.alumni.einvoicing.payloads.requests.InvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.InvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InvoiceConverter {
    private final ModelMapper modelMapper;

    public Invoice convert(InvoiceRequest request) {
        return modelMapper.map(request, Invoice.class);

    }

    public InvoiceResponse convert(Invoice data) {
        return modelMapper.map(data, InvoiceResponse.class);
    }
}
