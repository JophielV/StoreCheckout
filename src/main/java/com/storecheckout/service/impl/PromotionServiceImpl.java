package com.storecheckout.service.impl;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.enums.DiscountType;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.ItemDiscount;
import com.storecheckout.domain.model.transaction.OrderItem;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.PromotionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionServiceImpl implements PromotionService {

    DataSource dataSource = new DataSource();
    private List<Promotion> promotions = dataSource.promotions;

    @Override
    public Promotion getProductMostRecentPromotion(Product product) {
        // Return most recent  promotion to which this product is a productAction
        // This determines that this product will have promo value if its conditionQty is met
        List<Promotion> byProductAction = promotions.stream().
                filter(p -> p.getProductAction() != null &&
                        p.getProductAction().getProductId().equals(product.getProductId()))
                .collect(Collectors.toList());

        if (byProductAction.size() > 0)
            return byProductAction.get(byProductAction.size()-1);
        else
            return null;
    }

    @Override
    public Transaction processPromotionForItem(Transaction transaction, OrderItem orderItemToAdd, Promotion promotion) {
        List<ItemDiscount> itemDiscounts = new ArrayList<>();
        BigDecimal totalDiscounts = BigDecimal.ZERO;

        // get all items that are conditional product from this promotion by comparing productIds && are still not finished checking based on remaining qty
        List<OrderItem> orderItemsForChecking = transaction.getOrderItems().stream()
                .filter(oI -> oI.getProductId().equals(promotion.getProductCondition().getProductId())
                        && oI.getRemainingQty() > 0)
                .collect(Collectors.toList());

        Integer quantityToReach = promotion.getConditionQuantity();

        if (orderItemsForChecking.size() > 0) {
            Integer quantityAccum = 0;
            boolean promoCountReached = false;

            for (OrderItem orderItemForChecking : orderItemsForChecking) {
                // loop over orderItemsForChecking to check if the orderItemToAdd can have its promo discount applied
                quantityAccum += orderItemForChecking.getRemainingQty();
                // maximum to deduct for orderItemsRemaining qty is quantityToReach
                // example if this orderItemForChecking has still remainingQty = 2 to check, and the conditionQty = 1, deduct only the conditionQty = 1
                if (quantityAccum > quantityToReach) {
                    quantityAccum = quantityToReach;
                    promoCountReached = true;
                } else if (quantityAccum == quantityToReach) {
                    promoCountReached = true;
                }

                /* In the current orderItems of the transaction, find the orderItem
                   with the orderItemId of the orderItemForChecking that satisfied the conditionQty
                   and deduct the remainingQty to check
                */
                transaction.getOrderItems().stream()
                        .filter(o -> o.getOrderItemId().equals(orderItemForChecking.getOrderItemId()))
                        .findFirst()
                        .get().deductRemainingQtyToCheck(quantityAccum);

                // do not check other items if quantityToReach is already satisfied from accumulated remaining qty
                if (promoCountReached) break;
            }

            ItemDiscount itemDiscount = createDiscountForPromotion(orderItemToAdd, promotion, quantityAccum);
            totalDiscounts = totalDiscounts.add(itemDiscount.getAmount());
            itemDiscounts.add(itemDiscount);

            orderItemToAdd.deductRemainingQtyToCheck(quantityAccum > orderItemToAdd.getRemainingQty() ? orderItemToAdd.getRemainingQty() : quantityAccum);

            orderItemToAdd.setNetTotal(orderItemToAdd.getPriceSubtotal().subtract(itemDiscount.getAmount()));
            orderItemToAdd.setItemDiscounts(itemDiscounts);
            orderItemToAdd.setOverallDiscount(totalDiscounts);
        }

        transaction.getOrderItems().add(orderItemToAdd);

        return transaction;
    }

    private ItemDiscount createDiscountForPromotion(OrderItem orderItemToAdd, Promotion promotion, Integer quantityAccum) {
        ItemDiscount itemDiscount;
        BigDecimal discountValue = promotion.getDiscountValue();
        BigDecimal actionQuantity = orderItemToAdd.getRemainingQty() == promotion.getActionQuantity() ? new BigDecimal(promotion.getActionQuantity()) : new BigDecimal(quantityAccum);
        BigDecimal priceForDiscount = orderItemToAdd.getPrice().multiply(actionQuantity);
        String discountType = promotion.getDiscountType();

        if (discountType.equals(DiscountType.PERCENT.name())) {
            discountValue = (discountValue.divide(new BigDecimal("100"))).multiply(priceForDiscount);
            itemDiscount = new ItemDiscount(promotion.getDiscountValue() + "% PROMO DISCOUNT - " + actionQuantity + " item", discountValue);
        } else {
            itemDiscount = new ItemDiscount(discountValue + "AMT PROMO DISCOUNT", discountValue);
        }

        return itemDiscount;
    }
}
