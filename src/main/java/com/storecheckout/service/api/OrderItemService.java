package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemService {

    OrderItem processNewOrderItem(Transaction transaction, Product product,
                                  BigDecimal quantity, BigDecimal weight);
}
