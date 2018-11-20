package com.storecheckout.model.transaction;

import java.math.BigDecimal;

public class TransactionDiscount {

    private String transactionDiscountId;
    private BigDecimal amount;

    public String getTransactionDiscountId() {
        return transactionDiscountId;
    }

    public void setTransactionDiscountId(String transactionDiscountId) {
        this.transactionDiscountId = transactionDiscountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
