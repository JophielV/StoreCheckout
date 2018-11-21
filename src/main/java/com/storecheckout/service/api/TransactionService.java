package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    Transaction processTransaction(Transaction transaction, OrderItem orderItem);
}
