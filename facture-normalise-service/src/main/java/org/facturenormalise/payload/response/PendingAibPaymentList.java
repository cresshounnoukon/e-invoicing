package org.facturenormalise.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingAibPaymentList {
    private Date date;
    private String uid;
}

