package com.storecheckout.domain.model.transaction;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private String transactionId;
    private String orNumber;
    private DateTime transactionDate;
    private BigDecimal subTotal;
    private BigDecimal totalAmountTender;
    private BigDecimal totalItemDiscounts;
    private List<OrderItem> orderItems = new ArrayList<>();

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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotalAmountTender() {
        return totalAmountTender;
    }

    public void setTotalAmountTender(BigDecimal totalAmountTender) {
        this.totalAmountTender = totalAmountTender;
    }

    public BigDecimal getTotalItemDiscounts() {
        return totalItemDiscounts;
    }

    public void setTotalItemDiscounts(BigDecimal totalItemDiscounts) {
        this.totalItemDiscounts = totalItemDiscounts;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
