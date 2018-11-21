package com.storecheckout.service.impl;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.enums.DiscountType;
import com.storecheckout.domain.enums.PromoType;
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
    public List<Promotion> getProductPromotions(Product product) {
        List<Promotion> productPromotions = new ArrayList<>();


        List<Promotion> byProductAction = promotions.stream().
                filter(p -> p.getProductAction() != null &&
                        p.getProductAction().getProductId().equals(product.getProductId()))
                .collect(Collectors.toList());
        productPromotions.addAll(byProductAction);

        return productPromotions;
    }

    @Override
    public Transaction processPromotions(Transaction transaction, OrderItem orderItem, List<Promotion> promotions) {
        List<ItemDiscount> itemDiscounts = new ArrayList<>();
        BigDecimal totalDiscounts = BigDecimal.ZERO;

        for (Promotion promotion : promotions) {
            Integer conditionQuantity = promotion.getConditionQuantity();

            List<OrderItem> orderItemsForChecking = new ArrayList<>();

            orderItemsForChecking = transaction.getOrderItems().stream()
                    .filter(oI -> oI.getProductId().equals(promotion.getProductCondition().getProductId())
                            && oI.getRemainingQty() > 0)
                    .collect(Collectors.toList());


            if (orderItemsForChecking.size() > 0) {
                System.out.println("orderItemsForChecking Size: " + orderItemsForChecking.size());

                Integer quantityAccum = 0;
                Integer quantityToReach = promotion.getConditionQuantity();

                BigDecimal quantityToMultiply = orderItem.getQuantity().intValue() != promotion.getActionQuantity() ? new BigDecimal(promotion.getActionQuantity()) : orderItem.getQuantity();
                boolean promoCountReached = false;
                for (OrderItem orderItemForChecking : orderItemsForChecking) {
                    quantityAccum += orderItemForChecking.getRemainingQty();
                    if (quantityAccum > quantityToReach) {
                        quantityAccum = quantityToReach;
                        promoCountReached = true;
                    } else if (quantityAccum == quantityToReach) {
                        promoCountReached = true;
                    }

                    transaction.getOrderItems().stream()
                            .filter(o -> o.getOrderItemId().equals(orderItemForChecking.getOrderItemId()))
                            .findFirst()
                            .get().setPromoFieldsToCheck(promoCountReached, quantityAccum);

                    if (promoCountReached) break;
                }

                ItemDiscount itemDiscount;
                BigDecimal discountValue = promotion.getDiscountValue();
                BigDecimal actionQuantity = orderItem.getRemainingQty() == promotion.getActionQuantity() ? new BigDecimal(promotion.getActionQuantity()) : new BigDecimal(quantityAccum);
                BigDecimal priceForDiscount = orderItem.getPrice().multiply(actionQuantity);
                String discountType = promotion.getDiscountType();
                if (discountType.equals(DiscountType.PERCENT.name())) {
                    discountValue = (discountValue.divide(new BigDecimal("100"))).multiply(priceForDiscount);
                    itemDiscount = new ItemDiscount(promotion.getDiscountValue() + "% PROMO DISCOUNT - " + actionQuantity + " item", discountValue);
                } else {
                    itemDiscount = new ItemDiscount(discountValue + "AMT PROMO DISCOUNT", discountValue);
                }

                totalDiscounts = totalDiscounts.add(discountValue);
                itemDiscounts.add(itemDiscount);

                orderItem.setPromoFieldsToCheck(orderItem.getRemainingQty() - conditionQuantity == 0, quantityAccum > orderItem.getRemainingQty() ? orderItem.getRemainingQty() : quantityAccum);
                orderItem.setNetTotal(orderItem.getPriceSubtotal().subtract(discountValue));
                orderItem.setItemDiscounts(itemDiscounts);
                orderItem.setOverallDiscount(totalDiscounts);
            }


            transaction.getOrderItems().add(orderItem);
        }

        return transaction;
    }
}
