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

        //System.out.println("----------------- PROCESSING PROMOTIONS -------------");
        for (Promotion promotion: promotions) {

            List<OrderItem> modifiedOrderItems = new ArrayList<>();
            Integer conditionQuantity = promotion.getConditionQuantity();

            List<OrderItem> orderItemsForChecking = transaction.getOrderItems().stream()
                    .filter(oI -> oI.getProductId().equals(promotion.getProductCondition().getProductId())
                            && !oI.getPromoCheckingDone())
                    .collect(Collectors.toList());

            if (orderItemsForChecking.size() > 0) {
                BigDecimal itemQuantity = orderItemsForChecking.stream()
                        .map(OrderItem::getQuantity)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                if (itemQuantity.intValue() == conditionQuantity) {
                    //System.out.println("------------------- IF!!!");
                    orderItemsForChecking.stream().forEach(orderItemForChecking -> {
                        transaction.getOrderItems().stream().filter(o -> o.getOrderItemId().equals(orderItemForChecking.getOrderItemId()))
                                .findFirst().get().setPromoCheckingDone(true);
                    });

                    ItemDiscount itemDiscount;
                    BigDecimal discountValue = promotion.getDiscountValue();
                    BigDecimal priceForDiscount = orderItem.getPrice().multiply(new BigDecimal(promotion.getConditionQuantity()));
                    String discountType = promotion.getDiscountType();
                    if (discountType.equals(DiscountType.PERCENT.name())) {
                        discountValue = (discountValue.divide(new BigDecimal("100"))).multiply(priceForDiscount);
                        itemDiscount = new ItemDiscount(discountValue + "% DISCOUNT", discountValue);
                    } else {
                        itemDiscount = new ItemDiscount(discountValue + "AMT DISCOUNT", discountValue);
                    }

                    //System.out.println("---------------------- DISC: " + orderItem.getPriceSubtotal().subtract(discountValue));

                    itemDiscounts.add(itemDiscount);
                    orderItem.setPromoCheckingDone(true);
                    orderItem.setItemDiscounts(itemDiscounts);
                    orderItem.setNetTotal(orderItem.getPriceSubtotal().subtract(discountValue));


                } else {

                }
            }

            transaction.getOrderItems().add(orderItem);
            //modifiedOrderItems.add(orderItem);
            //transaction.setOrderItems(modifiedOrderItems);
        }

        return transaction;
    }
}
