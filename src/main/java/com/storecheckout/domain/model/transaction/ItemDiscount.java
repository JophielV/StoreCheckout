package com.storecheckout.domain.model.transaction;

import java.math.BigDecimal;

public class ItemDiscount {

    private String label;
    private BigDecimal amount;

    public ItemDiscount(String label, BigDecimal amount) {
        this.label = label;
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
