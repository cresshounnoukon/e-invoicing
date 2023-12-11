package com.sourcmind.alumni.einvoicing.facades;

import com.sourcmind.alumni.einvoicing.converters.InvoiceConverter;
import com.sourcmind.alumni.einvoicing.entities.*;
import com.sourcmind.alumni.einvoicing.exceptions.DataUnthorizeProcessingException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import com.sourcmind.alumni.einvoicing.payloads.requests.InvoiceRequest;
import com.sourcmind.alumni.einvoicing.payloads.responses.InvoiceResponse;
import com.sourcmind.alumni.einvoicing.services.impl.*;
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


        Invoice parent = service.readById(request.getReference());
// checking parent invoice
        if (parent != null) {
            // checking type invoice authorise for reimbursement
            if (!typeInvoice.getReimbursement().getCode().equals(parent.getTypeInvoice().getCode())) {
                throw new DataUnthorizeProcessingException(
                        Error.builder()
                                .type("TypeInvoice")
                                .value(request.getReference())
                                .message("Type  Invoice is unauthorize for reimbursement")
                                .field("typeInvoice")
                                .build()

                );
            }

            // checking product on parent invoice
            List<Product> parentProducts = parent.getItems().stream().map(Item::getProduct).toList();

            items.forEach(item -> {
                if (!parentProducts.contains(item.getProduct())) {
                    throw new DataUnthorizeProcessingException(
                            Error.builder()
                                    .type("Product ")
                                    .value(item.getId())
                                    .message(String.format("Product with %s is not authorized for reimburesment", item.getProduct().getId()))
                                    .field("productId")
                                    .build()

                    );

                }
                // verification  quantity som for rembursement is less than original Invoice item
                // verification for price is equal of originale Invoice item





            });





        }


        Invoice invoice = Invoice.builder()
                .typeInvoice(typeInvoice)
                .company(company)
                .customer(customer)
                //    .items(items)
                .build();
        invoice = service.save(invoice);

        double invoiceAmount = invoice.getTotal() + parent.getChildren().stream().mapToDouble(Invoice::getTotal).sum();

        double parentTotal = parent.getTotal();

        if(invoiceAmount > parentTotal){
            throw new DataUnthorizeProcessingException(
                    Error.builder()
                            .type("Invoice")
                            .value(invoiceAmount)
                            .message("Credit invoice amount exceeds parent invoice amount")
                            .field("Invoice")
                            .build());
        }

        Invoice finalInvoice = invoice;
        items.forEach(item -> item.setInvoice(finalInvoice));

        items = itemService.saveAll(items);
        invoice.setItems(items);
        return converter.convert(invoice);
    }

//    public InvoiceService makeRefund(InvoiceRequest request)
//    {
//        TypeInvoice typeInvoice = typeInvoiceService.readById(request.getTypeInvoiceId());
//        if(!typeInvoice.equals())
//
//    }


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
