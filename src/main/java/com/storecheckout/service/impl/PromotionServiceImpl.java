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

        /*List<Promotion> byProductCondition = promotions.stream().
                filter(p -> !p.getPromoType().equals(PromoType.SALE_PROMO.name()) &&
                        p.getProductCondition() != null &&
                        p.getProductCondition().getProductId().equals(product.getProductId()))
                .collect(Collectors.toList());
        productPromotions.addAll(byProductCondition);*/

        List<Promotion> byProductAction = promotions.stream().
                filter(p -> !p.getPromoType().equals(PromoType.SALE_PROMO.name()) &&
                        p.getProductAction() != null &&
                        p.getProductAction().getProductId().equals(product.getProductId()))
                .collect(Collectors.toList());
        productPromotions.addAll(byProductAction);

        /*List<Promotion> productSale = promotions.stream().
                filter(p -> p.getPromoType().equals(PromoType.SALE_PROMO.name()) &&
                        p.getSaleProducts().stream().anyMatch(q -> q.getProductId().equals(product.getProductId())))
                .collect(Collectors.toList());
        productPromotions.addAll(productSale);*/

        return productPromotions;
    }

    @Override
    public Transaction processPromotions(Transaction transaction, OrderItem orderItem, List<Promotion> promotions) {
        List<ItemDiscount> itemDiscounts = new ArrayList<>();
        BigDecimal totalDiscounts = BigDecimal.ZERO;

        for (Promotion promotion: promotions) {
            Integer conditionQuantity = promotion.getConditionQuantity();

            List<OrderItem> orderItemsForChecking = transaction.getOrderItems().stream()
                    .filter(oI -> oI.getProductId().equals(promotion.getProductCondition().getProductId())
                            && !oI.getPromoCheckingDone()
                            && oI.getRemainingQty() > 0)
                    .collect(Collectors.toList());

            if (orderItemsForChecking.size() > 0) {
                Integer itemQuantity = orderItemsForChecking.stream()
                        .mapToInt(OrderItem::getRemainingQty)
                        .sum();

                if (itemQuantity.intValue() == conditionQuantity) {
                    orderItemsForChecking.stream().forEach(orderItemForChecking -> {
                        Boolean isPromoCheckingDone = orderItem.getRemainingQty() - conditionQuantity == 0;
                        transaction.getOrderItems().stream()
                                .filter(o -> o.getOrderItemId().equals(orderItemForChecking.getOrderItemId()))
                                .findFirst()
                                .get().setPromoFieldsToCheck(isPromoCheckingDone, conditionQuantity);
                    });

                    ItemDiscount itemDiscount;
                    BigDecimal discountValue = promotion.getDiscountValue();
                    BigDecimal priceForDiscount = orderItem.getPrice().multiply(new BigDecimal(promotion.getConditionQuantity()));
                    String discountType = promotion.getDiscountType();
                    if (discountType.equals(DiscountType.PERCENT.name())) {
                        discountValue = (discountValue.divide(new BigDecimal("100"))).multiply(priceForDiscount);
                        itemDiscount = new ItemDiscount(promotion.getDiscountValue() + "% PROMO DISCOUNT - " + promotion.getConditionQuantity() + " item", discountValue);
                    } else {
                        itemDiscount = new ItemDiscount(discountValue + "AMT PROMO DISCOUNT", discountValue);
                    }

                    totalDiscounts = totalDiscounts.add(discountValue);
                    itemDiscounts.add(itemDiscount);

                    orderItem.setPromoFieldsToCheck(true, conditionQuantity);
                    orderItem.setNetTotal(orderItem.getPriceSubtotal().subtract(discountValue));
                }
            }


            orderItem.setItemDiscounts(itemDiscounts);
            orderItem.setOverallDiscount(totalDiscounts);
            transaction.getOrderItems().add(orderItem);
        }

        return transaction;
    }
}
