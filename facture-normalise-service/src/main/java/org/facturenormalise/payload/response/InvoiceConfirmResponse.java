package org.facturenormalise.payload.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class InvoiceConfirmResponse {
    public UUID uuid;
    public String dateTime;
    public String qrCode;
    public String codeMECeFDGI;
    public String counters;
    public String nim;
    public String errorCode;
    public String errorDesc;
}
