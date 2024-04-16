package com.sourcmind.alumni.einvoicing.facades;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.sourcmind.alumni.einvoicing.entities.Invoice;
import com.sourcmind.alumni.einvoicing.entities.Item;
import com.sourcmind.alumni.einvoicing.enums.Action;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.ClientDto;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.InvoiceRequestDataDto;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.ItemDto;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.OperatorDto;
import com.sourcmind.alumni.einvoicing.payloads.requests.emcf.PaymentDto;
import com.sourcmind.alumni.einvoicing.services.impl.EmcfServiceWebClientService;
import com.sourcmind.alumni.einvoicing.services.impl.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmcfInvoiceFacade {

    private final InvoiceService invoiceService;
    private final EmcfServiceWebClientService emcfServiceWebClientService;

    public Mono<ResponseEntity<?>> save(UUID invoiceId) {

        Invoice invoice = invoiceService.readById(invoiceId);

        List<Item> items = invoice.getItems();

        ArrayList<ItemDto> itemDtoList = items.stream()
            .map(item -> ItemDto.builder()
                .name(item.getProduct().getName())
                .taxGroup(item.getProduct().getTaxGroup().getCode())
                .code(item.getProduct().getCode())
                .price(item.getProduct().getPrice())
                .quantity((int) item.getQuantity())
                .build())
            .collect(Collectors.toCollection(ArrayList::new));

        ClientDto clientDto = ClientDto.builder()
            .name("Herval Ardiles")
            .contact("67084843")
            .address("Lom-Nava")
            .build();

        OperatorDto operatorDto = new OperatorDto();

        String type = invoice.getTypeInvoice().getCode();

        PaymentDto paymentDto = PaymentDto.builder()
            .name("ESPECES")
            .amount(invoice.getTotal())
            .build();

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        paymentDtos.add(paymentDto);

        InvoiceRequestDataDto invoiceRequestDataDto = InvoiceRequestDataDto.builder()
            .aib("A")
            .ifu("0202112946365")
            .payment(paymentDtos)
            .type(type)
            .items(itemDtoList)
            .client(clientDto)
            .operator(operatorDto)
            .build();

        return emcfServiceWebClientService.save(invoiceRequestDataDto)
            .map(responseEntity -> {
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    return ResponseEntity.ok(responseEntity.getBody());
                } else {
                    return ResponseEntity.status(responseEntity.getStatusCode())
                        .body("Failed to save invoice");
                }
            })
            .onErrorResume(exception -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + exception.getMessage())));

    }

    public Mono<ResponseEntity<?>> confirm(Action action, UUID emcfInvoiceId) {
        return emcfServiceWebClientService.operation(action.name(), emcfInvoiceId)
            .map(responseEntity -> {
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    return ResponseEntity.ok(responseEntity.getBody());
                } else {
                    return ResponseEntity.status(responseEntity.getStatusCode())
                        .body("Failed to confirm invoice");
                }
            })
            .onErrorResume(exception -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + exception.getMessage())));
    }

}
