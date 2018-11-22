package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.Transaction;

import java.math.BigDecimal;

public interface StoreCheckoutService {

    Transaction initializeTransaction();

    Transaction scanItem(Transaction transaction, Product product,
                         BigDecimal quantity, BigDecimal weight);
}
