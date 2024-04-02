package com.sourcmind.alumni.einvoicing.converters;


import com.sourcmind.alumni.einvoicing.entities.TaxGroup;
import com.sourcmind.alumni.einvoicing.payloads.requests.TaxGroupRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.TaxGroupResponse;
import org.springframework.stereotype.Component;

@Component
public class TaxGroupConverter {

    public TaxGroup convert(TaxGroupRequest request){
        return  TaxGroup
                .builder()
                .code(request.getCode())
                .name(request.getName())
                .rate(request.getRate())
                .build();
    }

    public TaxGroupResponse convert(TaxGroup taxGroup){
        return  TaxGroupResponse
                .builder()
                .id(taxGroup.getId())
                .code(taxGroup.getCode())
                .name(taxGroup.getName())
                .rate(taxGroup.getRate())
                .build();
    }
}
