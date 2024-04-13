package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.InvoiceConverter;
import com.sourcmind.alumni.einvoicing.entities.*;
import com.sourcmind.alumni.einvoicing.exceptions.DataUnthorizeProcessingException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.payloads.requests.InvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.InvoiceResponse;
import com.sourcmind.alumni.einvoicing.services.impl.*;
import com.sourcmind.alumni.einvoicing.utils.InvoiceRule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class InvoiceFacade {

    private final InvoiceConverter converter;
    private final InvoiceService service;
    private final CompanyService companyService;
    private final TypeInvoiceService typeInvoiceService;
    private final ProductService productService;
    private final ItemService itemService;

    public Page<InvoiceResponse> readAll(Pageable pageable) {
        return service.readAll(pageable).map(converter::convert);
    }

    public InvoiceResponse create(InvoiceRequest request) {

        TypeInvoice typeInvoice = typeInvoiceService.readById(request.getTypeInvoiceId());

        Company company = companyService.readById(request.getCompanyId());
        Company customer = companyService.readById(request.getCustomerId());

        List<Item> items = request.getItems().stream().map(itemRequest -> {
            Product product = productService.readById(itemRequest.getProductId());
            return Item.builder()
                .product(product)
                .price(itemRequest.getPrice())
                .quantity(itemRequest.getQuantity())
                .build();
        }).toList();

        Invoice invoice = Invoice.builder()
            .typeInvoice(typeInvoice)
            .company(company)
            .customer(customer)
            .items(items)
            .build();

        Invoice parent = request.getReference() == null ? null : service.readById(request.getReference());

        // checking parent invoice
        if (parent != null) {
            InvoiceRule invoiceRule = new InvoiceRule();

            invoiceRule.setCurrentInvoice(invoice);
            invoiceRule.setParent(parent);

            invoiceRule.checkInvoiceRules();

        }

        invoice = service.save(invoice);

        Invoice finalInvoice = invoice;
        items.forEach(item -> item.setInvoice(finalInvoice));

        items = itemService.saveAll(items);
        invoice.setItems(items);
        return converter.convert(invoice);
    }

    public InvoiceResponse readById(UUID id) {
        return converter.convert(service.readById(id));
    }

    public void delete(UUID id) {
        service.delete(service.readById(id));
    }

    public InvoiceResponse update(InvoiceRequest request) {
        return converter.convert(service.save(converter.convert(request)));
    }
}
