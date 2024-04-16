package com.sourcmind.alumni.einvoicing.payloads.responses.emcf;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingAibPaymentList {
    private Date date;
    private String uid;
}

