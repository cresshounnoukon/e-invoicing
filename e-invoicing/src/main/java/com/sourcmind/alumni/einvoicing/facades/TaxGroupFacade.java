package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.TaxGroupConverter;
import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import com.sourcmind.alumni.einvoicing.payloads.requests.TaxGroupRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TaxGroupResponse;
import com.sourcmind.alumni.einvoicing.services.impl.TaxGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaxGroupFacade {

    private final TaxGroupConverter converter;
    private final TaxGroupService taxGroupService;

    public Page<TaxGroupResponse> readAll(Pageable pageable) {
       return taxGroupService.readAll(pageable).map(converter::convert);
    }

    public TaxGroupResponse create(TaxGroupRequest taxGroupRequest) {
        taxGroupService.existsByCode(taxGroupRequest.getCode());
        TaxGroup taxGroup = converter.convert(taxGroupRequest);
        return converter.convert(taxGroupService.save(taxGroup));
    }


    public TaxGroupResponse readByCode(String code) {
        return converter.convert(taxGroupService.readByCode(code));
    }

    public TaxGroupResponse readById(UUID id) {
        return converter.convert(taxGroupService.readById(id));
    }

    public void delete(UUID id) {
        taxGroupService.delete(taxGroupService.readById(id));
    }

    public TaxGroupResponse update(TaxGroupRequest taxGroupRequest) {
        return converter.convert(taxGroupService.save(converter.convert(taxGroupRequest)));

    }
}
