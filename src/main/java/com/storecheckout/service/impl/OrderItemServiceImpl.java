package com.storecheckout.service.impl;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.OrderItemService;
import com.storecheckout.service.api.PromotionService;
import com.storecheckout.utils.IdGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    DataSource dataSource = new DataSource();
    private List<Promotion> promotions = dataSource.promotions;
    private PromotionService promotionService = new PromotionServiceImpl();

    @Override
    public OrderItem processNewOrderItem(Transaction transaction, Product product, BigDecimal quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(IdGenerator.generateId());
        orderItem.setProductId(product.getProductId());
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
