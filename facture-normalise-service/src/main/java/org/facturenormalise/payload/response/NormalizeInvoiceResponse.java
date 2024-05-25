package org.facturenormalise.payload.response;

import java.util.UUID;

public record NormalizeInvoiceResponse(UUID invoiceId, String token){
}
