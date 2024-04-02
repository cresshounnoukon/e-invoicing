package org.facturenormalise.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DgiInvoiceResponse {
    private String uid;
    private int ta;
    private int tb;
    private int tc;
    private int td;
    private int taa;
    private int tab;
    private int tac;
    private int tad;
    private int tae;
    private int taf;
    private int hab;
    private int had;
    private int vab;
    private int vad;
    private int aib;
    private int ts;
    private int total;
    private String errorCode;
    private String errorDesc;

}
