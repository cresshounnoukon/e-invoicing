package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.CompanyConverter;
import com.sourcmind.alumni.einvoicing.entities.Company;
import com.sourcmind.alumni.einvoicing.payloads.requests.CompanyRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.CompanyResponse;
import com.sourcmind.alumni.einvoicing.services.impl.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CompanyFacade {

    private final CompanyConverter converter;
    private final CompanyService service;

    public Page<CompanyResponse> readAll(Pageable pageable) {
        return service.readAll(pageable).map(converter::convert);
    }

    public CompanyResponse create(CompanyRequest request) {
        service.checkTin(request.getTin());
        service.checkTradeNo(request.getTradeNo());
        Company company = converter.convert(request);
        service.save(company);
        return converter.convert(service.save(company));

    }

    public CompanyResponse readById(UUID id) {
        return converter.convert(service.readById(id));
    }

    public void delete(UUID id) {
        service.delete(service.readById(id));
    }

    public CompanyResponse update(CompanyRequest request) {
        return converter.convert(service.save(converter.convert(request)));
    }
}
