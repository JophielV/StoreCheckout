package com.storecheckout.model.transaction;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

public class Transaction {

    private String transactionId;
    private String orNumber;
    private DateTime transactionDate;
    private BigDecimal totalAmountTender;
    private String cashierName;
    private List<OrderItem> orderItems;
    private List<TransactionDiscount> transactionDiscounts;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOrNumber() {
        return orNumber;
    }

    public void setOrNumber(String orNumber) {
        this.orNumber = orNumber;
    }

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTotalAmountTender() {
        return totalAmountTender;
    }

    public void setTotalAmountTender(BigDecimal totalAmountTender) {
        this.totalAmountTender = totalAmountTender;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<TransactionDiscount> getTransactionDiscounts() {
        return transactionDiscounts;
    }

    public void setTransactionDiscounts(List<TransactionDiscount> transactionDiscounts) {
        this.transactionDiscounts = transactionDiscounts;
    }
}
