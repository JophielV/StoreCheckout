package com.storecheckout.service.impl;

import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.TransactionService;

import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction processTransaction(Transaction transaction, OrderItem orderItem) {
        //List<OrderItem> txOrderItems = transaction.getOrderItems();
        //txOrderItems.add(orderItem);
        //transaction.setOrderItems(txOrderItems);

        // check if there is atleast one

        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalItemDiscounts = BigDecimal.ZERO;
        BigDecimal totalAmountTender = BigDecimal.ZERO;

        for (OrderItem item: transaction.getOrderItems()) {
            subTotal = subTotal.add(item.getPriceSubtotal());
            totalItemDiscounts = totalItemDiscounts.add(item.getOverallDiscount());
            totalAmountTender = totalAmountTender.add(item.getNetTotal());
        }

        transaction.setSubTotal(subTotal);
        transaction.setTotalItemDiscounts(totalItemDiscounts);
        transaction.setTotalAmountTender(totalAmountTender);

        return transaction;
    }
}
