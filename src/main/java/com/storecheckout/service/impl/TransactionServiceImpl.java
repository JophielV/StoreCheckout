package com.storecheckout.service.impl;

import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.TransactionService;

import java.math.BigDecimal;

public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction processTransaction(Transaction transaction, OrderItem orderItem) {
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalItemDiscounts = BigDecimal.ZERO;
        BigDecimal totalAmountTender = BigDecimal.ZERO;

        for (OrderItem item: transaction.getOrderItems()) {
            subTotal = subTotal.add(item.getPriceSubtotal());
            totalItemDiscounts = totalItemDiscounts.add(item.getOverallDiscount() != null ? item.getOverallDiscount() : BigDecimal.ZERO);
            totalAmountTender = totalAmountTender.add(item.getNetTotal() != null ? item.getNetTotal() :  BigDecimal.ZERO);
        }

        transaction.setSubTotal(subTotal);
        transaction.setTotalItemDiscounts(totalItemDiscounts);
        transaction.setTotalAmountTender(totalAmountTender);

        return transaction;
    }
}
