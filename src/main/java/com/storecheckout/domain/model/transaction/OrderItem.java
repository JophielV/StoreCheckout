package com.storecheckout.domain.model.transaction;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {

    private String orderItemId;
    private String productName;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal priceSubtotal;
    private BigDecimal overallDiscount;
    private List<ItemDiscount> itemDiscounts;
    private BigDecimal netTotal;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
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
}
