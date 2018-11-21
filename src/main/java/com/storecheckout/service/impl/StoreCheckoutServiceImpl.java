package com.storecheckout.service.impl;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.OrderItemService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.api.TransactionService;
import com.storecheckout.utils.IdGenerator;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class StoreCheckoutServiceImpl implements StoreCheckoutService {

    TransactionService transactionService = new TransactionServiceImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();

    @Override
    public Transaction initializeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(IdGenerator.generateId());
        transaction.setTransactionDate(new DateTime());
        return transaction;
    }

    @Override
    public Transaction scanItem(Transaction tx, Product product, BigDecimal quantity) {
        OrderItem orderItem = orderItemService.processOrderItem(product, quantity);
        Transaction transaction = transactionService.processTransaction(tx, orderItem);
        return transaction;
    }

    @Override
    public void printReceipt(Transaction transaction) {
    }


}
