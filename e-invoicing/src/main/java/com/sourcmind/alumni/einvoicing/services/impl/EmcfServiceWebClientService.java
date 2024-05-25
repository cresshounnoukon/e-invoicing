package com.sourcmind.alumni.einvoicing.services.impl;

import java.util.UUID;

import com.sourcmind.alumni.einvoicing.enums.Action;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.InvoiceRequestDataDto;
import com.sourcmind.alumni.einvoicing.payloads.responses.emcf.InvoiceResponseDto;
import com.sourcmind.alumni.einvoicing.payloads.responses.emcf.SecurityElementsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmcfServiceWebClientService {

    private final WebClient webClient;

    public Mono<ResponseEntity<InvoiceResponseDto>> save(InvoiceRequestDataDto invoiceRequestDataDto) {
        return webClient.post()
            .uri("")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invoiceRequestDataDto)
            .retrieve()
            .toEntity(InvoiceResponseDto.class);
    }

    public Mono<ResponseEntity<SecurityElementsDto>> operation(String action, UUID invoiceId) {
        return webClient.put()
            .uri("/" + invoiceId + "/" + action)
            .contentType(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(SecurityElementsDto.class);
    }

}
