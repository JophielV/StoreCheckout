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
    private Boolean isActionProduct = false;
    private Boolean isPromoCheckingDone;

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

    public Boolean getActionProduct() {
        return isActionProduct;
    }

    public void setActionProduct(Boolean actionProduct) {
        isActionProduct = actionProduct;
    }

    public Boolean getPromoCheckingDone() {
        return isPromoCheckingDone;
    }

    public void setPromoCheckingDone(Boolean promoCheckingDone) {
        isPromoCheckingDone = promoCheckingDone;
    }
}
