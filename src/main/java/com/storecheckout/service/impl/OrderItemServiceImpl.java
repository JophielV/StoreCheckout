package com.storecheckout.service.impl;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.service.api.OrderItemService;
import com.storecheckout.utils.IdGenerator;

import java.math.BigDecimal;

public class OrderItemServiceImpl implements OrderItemService {

    @Override
    public OrderItem processOrderItem(Product product, BigDecimal quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(IdGenerator.generateId());
        orderItem.setProductName(product.getProductName());
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(quantity);

        BigDecimal subTotal = computePriceSubtotal(product.getPrice(), quantity);
        orderItem.setPriceSubtotal(subTotal);

        // if there are discounts
        BigDecimal itemDiscountTotal = BigDecimal.ZERO;
        orderItem.setOverallDiscount(itemDiscountTotal);
        orderItem.setNetTotal(subTotal.subtract(itemDiscountTotal));

        return orderItem;
    }

    private BigDecimal computePriceSubtotal(BigDecimal price, BigDecimal quantity) {
        return price.multiply(quantity);
    }
}
