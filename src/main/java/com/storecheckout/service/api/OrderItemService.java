package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.OrderItem;

import java.math.BigDecimal;

public interface OrderItemService {

    OrderItem processOrderItem(Product product, BigDecimal quantity);
}
