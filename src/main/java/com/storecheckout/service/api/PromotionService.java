package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;

import java.util.List;

public interface PromotionService {

    List<Promotion> getProductPromotions(Product product);

    Transaction processPromotions(Transaction transaction, OrderItem orderItem, List<Promotion> promotions);
}
