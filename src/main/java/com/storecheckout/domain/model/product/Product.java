package com.storecheckout.domain.model.product;

import java.math.BigDecimal;

public class Product {

    private String productId;
    private String productName;
    private BigDecimal price;
    private Boolean isWeighted;
    private String unitOfMeasurement;

    public Product(String productId, String productName, BigDecimal price,
                   Boolean isWeighted, String unitOfMeasurement) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.isWeighted = isWeighted;
        this.unitOfMeasurement = unitOfMeasurement;
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

    public Boolean getWeighted() {
        return isWeighted;
    }

    public void setWeighted(Boolean weighted) {
        isWeighted = weighted;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }
}
