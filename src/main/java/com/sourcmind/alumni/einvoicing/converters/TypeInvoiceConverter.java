package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.TypeInvoice;
import com.sourcmind.alumni.einvoicing.payloads.requests.TypeInvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TypeInvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeInvoiceConverter {
    private final  ModelMapper modelMapper;

    public TypeInvoice convert(TypeInvoiceRequest request) {
        return  modelMapper.map(request, TypeInvoice.class);

    }

    public TypeInvoiceResponse convert(TypeInvoice data) {
        return  modelMapper.map(data, TypeInvoiceResponse.class);
    }
}
