package org.facturenormalise.consumer;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.facturenormalise.config.KafkaProducerConfig;
import org.facturenormalise.facade.DgiInvoiceFacade;
import org.facturenormalise.payload.response.DgiInvoiceResponse;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
import org.facturenormalise.payload.response.NormalizeInvoiceResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmcfConsumer {

    private final DgiInvoiceFacade dgiInvoiceFacade;

    private final KafkaProducerConfig kafkaProducerConfig;
    @KafkaListener(topics = "e-invoicing", groupId = "eInvoicing")
    public void consumeMsg(NormalizeInvoiceResponse response){

        InvoiceConfirmResponse invoiceConfirmResponse = dgiInvoiceFacade.confirmInvoice(response.invoiceId(), response.token());

        invoiceConfirmResponse.setUuid(response.invoiceId());

        kafkaProducerConfig.sendMessage(invoiceConfirmResponse);

        log.info(format("Consuming the message from facture normalise service consumer :: %s", invoiceConfirmResponse.getCodeMECeFDGI()));
    }
}
