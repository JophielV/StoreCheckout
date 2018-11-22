package com.storecheckout.service.api;

import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;

import java.util.List;

public interface PromotionService {

    Promotion getProductMostRecentPromotion(Product product);

    Transaction processPromotionForItem(Transaction transaction, OrderItem orderItem, Promotion promotion);
}
