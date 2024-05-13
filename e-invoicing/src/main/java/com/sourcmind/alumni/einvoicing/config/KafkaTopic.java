package com.sourcmind.alumni.einvoicing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Value("${topic.emcf}")
    private String topic;

    @Value("${topic.normalize}")
    private String normalizeTopic;
    @Bean
    public NewTopic eInvoicing() {
        return TopicBuilder
            .name(topic)
            .build();
    }

    @Bean
    public NewTopic normalizeInvoice() {
        return TopicBuilder
            .name(normalizeTopic)
            .build();
    }
}
