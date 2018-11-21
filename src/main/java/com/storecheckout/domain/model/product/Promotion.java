package com.storecheckout.domain.model.product;

public class Promotion {

    private String promoId;
    private String promoType;
    private String conditionType;

    private Integer conditionValue;
    private Integer actionQuantity;

    private String discountType;
    private String discountValue;

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

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public Integer getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Integer conditionValue) {
        this.conditionValue = conditionValue;
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

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
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
