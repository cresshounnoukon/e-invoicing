package com.sourcmind.alumni.einvoicing.config;

import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.NormalizeInvoiceDto;
import com.sourcmind.alumni.einvoicing.payloads.responses.emcf.InvoiceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerConfig {

    private final KafkaTemplate<String, InvoiceResponseDto> kafkaTemplate;

    @Value("${topic.emcf}")
    private String topic;

    public void sendMessage(NormalizeInvoiceDto normalizeInvoiceDto){
        Message<NormalizeInvoiceDto> message = MessageBuilder
            .withPayload(normalizeInvoiceDto)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build();

        kafkaTemplate.send(message);
    }

}
