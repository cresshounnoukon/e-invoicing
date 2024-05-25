package com.sourcmind.alumni.einvoicing.consumer;

import static java.lang.String.format;

import com.sourcmind.alumni.einvoicing.entities.Invoice;
import com.sourcmind.alumni.einvoicing.payloads.responses.emcf.SecurityElementsDto;
import com.sourcmind.alumni.einvoicing.services.impl.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EInvoicingConsumer {

    private final InvoiceService invoiceService;
    @KafkaListener(topics = "${topic.normalize}", groupId = "normalizeInvoice")
    public void consumeMsg(SecurityElementsDto response){

        if(response.getErrorCode() == null){
            Invoice invoice = invoiceService.readByEmcfInvoiceId(response.getUuid());
            invoice.setNormalize(true);
            invoiceService.save(invoice);
            log.info(format("Invoice is successfully normalize and MECeFDGI is :: %s", response.getCodeMECeFDGI()));
        }else{
            log.info("We are unable to normalize invoice ");
        }

    }
}
