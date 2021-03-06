package com.storecheckout.service.impl;

import com.storecheckout.domain.constants.DateTimeConstants;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.ReceiptService;

import java.math.BigDecimal;
import java.util.List;

public class ReceiptServiceImpl implements ReceiptService {

    public void printReceipt(Transaction transaction) {
        System.out.println("************************ PRINTING RECEIPT ***********************");
        System.out.println(String.format("%-20s%s", "", "Grocery Store Checkout"));
        System.out.println(String.format("%-20s%s", "", "Operated By: Philippines"));
        System.out.println(String.format("%-20s%s", "", "Balanga, Bataan"));
        System.out.println(String.format("%-20s%s", "", "VAT REG TIN: 111-111-111"));
        System.out.println();
        System.out.println(String.format("%-23s%s", "", "SALES INVOICE"));
        System.out.println(String.format("%-13s%s", "", "Transaction Date: ") + transaction.getTransactionDate().toString(DateTimeConstants.RECEIPT_TX_DATE_FORMAT));
        System.out.println(String.format("%-13s%s", "", "Transaction Id: ") + transaction.getTransactionId());
        System.out.println();
        System.out.println("Items:");

        List<OrderItem> orderItems = transaction.getOrderItems();
        orderItems.forEach(o -> {
            System.out.println(String.format("%s",  o.getProductName()));
            System.out.println(String.format("%-35s %s",  o.getPrice() + " * " + o.getQuantity(), o.getPriceSubtotal()));

            if (o.getWeightedDescription() != null) {
                System.out.println(String.format("%s",  o.getWeightedDescription()));
            }
            if (o.getItemDiscounts() != null) {
                o.getItemDiscounts().forEach(itemDisc -> {
                    System.out.println(String.format("%-34s %s",  itemDisc.getLabel(), itemDisc.getAmount().negate()));
                });
            }
            System.out.println("--");
        });

        System.out.println();
        System.out.println(String.format("%-35s %s",  "Subtotal: ", transaction.getSubTotal()));
        System.out.println(transaction.getTotalItemDiscounts().compareTo(BigDecimal.ZERO) == 0 ?
                String.format("%-35s %s",  "Total Discounts: ", BigDecimal.ZERO):
                String.format("%-34s %s",  "Total Discounts: ", transaction.getTotalItemDiscounts().negate()));

        System.out.println(String.format("%-35s %s",  "Total: ", transaction.getTotalAmountTender()));
        System.out.println("************************ END RECEIPT ***********************");
    }
}
