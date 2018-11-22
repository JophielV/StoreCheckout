package com.storecheckout.service.impl;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.OrderItemService;
import com.storecheckout.service.api.PromotionService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.api.TransactionService;
import com.storecheckout.utils.IdGenerator;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

public class StoreCheckoutServiceImpl implements StoreCheckoutService {

    TransactionService transactionService = new TransactionServiceImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();
    PromotionService promotionService = new PromotionServiceImpl();

    @Override
    public Transaction initializeTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(IdGenerator.generateId());
        transaction.setTransactionDate(new DateTime());
        return transaction;
    }

    @Override
    public Transaction scanItem(Transaction tx, Product product, BigDecimal quantity, BigDecimal weight) {
        List<Promotion> promotions = promotionService.getProductPromotions(product);
        OrderItem orderItem = orderItemService.processNewOrderItem(tx, product, quantity, weight);


        if (promotions.size() > 0) {
            orderItem.setPromoCheckingDone(false);
            orderItem.setActionProduct(true);
            tx = promotionService.processPromotions(tx, orderItem, promotions);
        } else {
            tx.getOrderItems().add(orderItem);
        }

        Transaction transaction = transactionService.processTransaction(tx, orderItem);
        return transaction;
    }


}
