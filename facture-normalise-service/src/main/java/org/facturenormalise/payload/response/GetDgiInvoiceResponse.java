package org.facturenormalise.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDgiInvoiceResponse {
    private boolean status;
    private String version;
    private String ifu;
    private String nim;
    private Date tokenValid;
    private Date serverDateTime;
    private int pendingRequestsCount;
    private ArrayList<PendingRequestsList> pendingRequestsList;
    private String pendingAibPaymentUid;
    private String pendingAibPaymentUrl;
    private ArrayList<PendingAibPaymentList> pendingAibPaymentList;
}
