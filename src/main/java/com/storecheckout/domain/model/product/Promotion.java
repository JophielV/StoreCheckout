package com.storecheckout.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

public class Promotion {

    private String promoId;
    private String promoType;

    private Integer conditionQuantity;
    private Integer actionQuantity;

    private String discountType;
    private BigDecimal discountValue;

    // these 2 fields are for buyXgetX & buyXgetY condition
    private Product productCondition;
    private Product productAction;


    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public Integer getConditionQuantity() {
        return conditionQuantity;
    }

    public void setConditionQuantity(Integer conditionQuantity) {
        this.conditionQuantity = conditionQuantity;
    }

    public Integer getActionQuantity() {
        return actionQuantity;
    }

    public void setActionQuantity(Integer actionQuantity) {
        this.actionQuantity = actionQuantity;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Product getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(Product productCondition) {
        this.productCondition = productCondition;
    }

    public Product getProductAction() {
        return productAction;
    }

    public void setProductAction(Product productAction) {
        this.productAction = productAction;
    }
}
