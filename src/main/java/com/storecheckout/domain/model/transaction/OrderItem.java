package com.storecheckout.domain.model.transaction;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {

    private String orderItemId;
    private String productId;
    private String productName;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal priceSubtotal;
    private BigDecimal overallDiscount;
    private List<ItemDiscount> itemDiscounts;
    private BigDecimal netTotal;

    // Fields for checking promo
    private Integer remainingQty; // initially this is always equals to quantity

    // Fields related to weight
    private String weightedDescription;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceSubtotal() {
        return priceSubtotal;
    }

    public void setPriceSubtotal(BigDecimal priceSubtotal) {
        this.priceSubtotal = priceSubtotal;
    }

    public BigDecimal getOverallDiscount() {
        return overallDiscount;
    }

    public void setOverallDiscount(BigDecimal overallDiscount) {
        this.overallDiscount = overallDiscount;
    }

    public List<ItemDiscount> getItemDiscounts() {
        return itemDiscounts;
    }

    public void setItemDiscounts(List<ItemDiscount> itemDiscounts) {
        this.itemDiscounts = itemDiscounts;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    public Integer getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Integer remainingQty) {
        this.remainingQty = remainingQty;
    }

    public void deductRemainingQtyToCheck(Integer qtyToSubtract) {
        this.remainingQty -= qtyToSubtract;
    }

    public String getWeightedDescription() {
        return weightedDescription;
    }

    public void setWeightedDescription(String weightedDescription) {
        this.weightedDescription = weightedDescription;
    }
}
