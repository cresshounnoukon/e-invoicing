package com.sourcmind.alumni.einvoicing.payloads.responses.emcf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityElementsDto {
    public String dateTime;
    public String qrCode;
    public String codeMECeFDGI;
    public String counters;
    public String nim;
    public String errorCode;
    public String errorDesc;
}
