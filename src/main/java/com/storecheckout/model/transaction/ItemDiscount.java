package com.storecheckout.model.transaction;

import java.math.BigDecimal;

public class ItemDiscount {

    private String itemDiscountId;
    private BigDecimal amount;

    public String getItemDiscountId() {
        return itemDiscountId;
    }

    public void setItemDiscountId(String itemDiscountId) {
        this.itemDiscountId = itemDiscountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
