package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.Company;
import com.sourcmind.alumni.einvoicing.entities.TypeInvoice;
import com.sourcmind.alumni.einvoicing.payloads.requests.CompanyRequest;
import com.sourcmind.alumni.einvoicing.payloads.requests.TypeInvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CompanyResponse;
import com.sourcmind.alumni.einvoicing.payloads.responses.TypeInvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyConverter {

    private final  ModelMapper modelMapper;

    public Company convert(CompanyRequest request) {
        return  modelMapper.map(request, Company.class);

    }

    public CompanyResponse convert(Company data) {
        return  modelMapper.map(data, CompanyResponse.class);
    }
}
