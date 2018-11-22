package com.storecheckout.service.impl;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.OrderItemService;
import com.storecheckout.utils.IdGenerator;

import java.math.BigDecimal;

public class OrderItemServiceImpl implements OrderItemService {

    @Override
    public OrderItem processNewOrderItem(Transaction transaction, Product product,
                                         BigDecimal quantity, BigDecimal weight) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(String.valueOf(IdGenerator.generateIntId()));
        orderItem.setProductId(product.getProductId());
        orderItem.setProductName(product.getProductName());
        orderItem.setPrice(product.getPrice());
        orderItem.setQuantity(quantity);
        orderItem.setRemainingQty(quantity.intValue());

        BigDecimal subTotal;
        if (product.getWeighted()) {
            orderItem.setWeightedDescription(product.getPrice() + " per "
                    + product.getUnitOfMeasurement().toString() + " - weight: " + weight + " " + product.getUnitOfMeasurement().toString());
            subTotal = computePriceSubtotal(product.getPrice(), quantity, weight);
        } else {
            subTotal = computePriceSubtotal(product.getPrice(), quantity);
        }

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

    private BigDecimal computePriceSubtotal(BigDecimal price, BigDecimal quantity, BigDecimal weight) {
        return price.multiply(quantity).multiply(weight);
    }
}
