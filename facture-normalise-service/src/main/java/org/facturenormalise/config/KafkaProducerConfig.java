package org.facturenormalise.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.facturenormalise.payload.response.InvoiceConfirmResponse;
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

    private final KafkaTemplate<String, InvoiceConfirmResponse> kafkaTemplate;

    @Value("${topic.normalize}")
    private String topic;

    public void sendMessage(InvoiceConfirmResponse normalizeInvoiceDto){
        Message<InvoiceConfirmResponse> message = MessageBuilder
            .withPayload(normalizeInvoiceDto)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build();

        kafkaTemplate.send(message);
    }

}
