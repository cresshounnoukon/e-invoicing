package com.sourcmind.alumni.einvoicing.utils;

import com.sourcmind.alumni.einvoicing.entities.Invoice;
import com.sourcmind.alumni.einvoicing.entities.Item;
import com.sourcmind.alumni.einvoicing.entities.Product;
import com.sourcmind.alumni.einvoicing.exceptions.DataUnthorizeProcessingException;
import com.sourcmind.alumni.einvoicing.exceptions.Error;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class InvoiceRule {
    private Invoice currentInvoice;
    private Invoice parent;


    public void  checkInvoiceRules(){
        checkTypeInvoice();
        checkProductOnParentInvoice();
        checkTotalInvoiceAmount();
    }

    private void checkTypeInvoice() {

        // checking parent invoice
        if (parent != null) {
            // checking type invoice authorise for reimbursement
            if (!currentInvoice.getTypeInvoice().getReimbursement().getCode().equals(parent.getTypeInvoice().getCode())) {
                throw createError("TypeInvoice", currentInvoice.getTypeInvoice().getCode(),
                        "Type  Invoice is unauthorized for reimbursement", "typeInvoice");
            }

        }

    }


    private void checkProductOnParentInvoice() {
        currentInvoice.getItems().forEach(this::checkItemAuthorize);

    }


    private void checkItemAuthorize(Item item) {

        if (!parent.getItems().contains(item)) {
            throw createError("Product", item.getId(),
                    String.format("Product with %s is not authorized for reimbursement", item.getProduct().getId()), "productId");

        } else {
            checkQtyOfProductOnParentOnParentInvoice(item);
        }
    }


    private void checkQtyOfProductOnParentOnParentInvoice(Item item) {
        double parentQty = getParentQty(item);
        parent.getChildren().forEach(child -> {

            if (child.getItems().contains(item)) {
                double totalReimbursement = item.getQuantity() + getChildItemQty(child, item);
                if (totalReimbursement > parentQty) {
                    throw createError("Item", totalReimbursement,
                            String.format("Total of reimbursement %s more than  total parent %s", totalReimbursement, parentQty),
                            "quantity"
                    );
                }
            }

        });

    }

    private void checkTotalInvoiceAmount() {
        double totalAmountReimbursement = currentInvoice.getTotal() + parent.getChildren().stream().mapToDouble(Invoice::getTotal).sum();
        if (totalAmountReimbursement > parent.getTotal()) {
            throw createError("Invoice", totalAmountReimbursement, String.format("Total Amount of reimbursment %s more than total Amount of parent invoice %s", totalAmountReimbursement, parent.getTotal()), "Total Amount");
        }

    }

    private double getChildItemQty(Invoice child, Item item) {
        return child.getItems().get(child.getItems()
                .indexOf(item)).getQuantity();
    }


    private double getParentQty(Item item) {
        return parent.getItems().get(parent.getItems()
                .indexOf(item)).getQuantity();
    }

    private DataUnthorizeProcessingException createError(String type, Object value, String message, String field) {
        return new DataUnthorizeProcessingException(
                Error.builder()
                        .type(type)
                        .value(value)
                        .message(message)
                        .field(field)
                        .build()
        );
    }


}
