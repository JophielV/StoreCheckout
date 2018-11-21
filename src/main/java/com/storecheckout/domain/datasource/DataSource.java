package com.storecheckout.domain.datasource;

import com.storecheckout.domain.enums.DiscountType;
import com.storecheckout.domain.enums.PromoType;
import com.storecheckout.domain.enums.UnitOfMeasurement;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSource {
    public static HashMap<String, Product> products = new HashMap<>();
    public static List<Promotion> promotions = new ArrayList<>();

    static {
        products.put("1", new Product("1", "Safeguard",
                new BigDecimal("30"), false, UnitOfMeasurement.EACH.name()));
        products.put("2", new Product("2", "Century Tuna",
                new BigDecimal("45"), false, UnitOfMeasurement.EACH.name()));
        products.put("3", new Product("3", "Peanut Butter",
                new BigDecimal("65.75"), false, UnitOfMeasurement.EACH.name()));
        products.put("4", new Product("4", "Pencil",
                new BigDecimal("11.25"), false, UnitOfMeasurement.EACH.name()));
        products.put("5", new Product("5", "Rice",
                new BigDecimal("45"), true, UnitOfMeasurement.EACH.name()));
        products.put("6", new Product("6", "Paper",
                new BigDecimal("25.50"), false, UnitOfMeasurement.EACH.name()));
        products.put("7", new Product("7", "Eraser",
                new BigDecimal("15"), false, UnitOfMeasurement.EACH.name()));
        products.put("8", new Product("8", "Bulb Light",
                new BigDecimal("120"), false, UnitOfMeasurement.EACH.name()));
        products.put("9", new Product("9", "Electric Fan",
                new BigDecimal("1500"), false, UnitOfMeasurement.EACH.name()));
        products.put("10", new Product("10", "Milk",
                new BigDecimal("90"), false, UnitOfMeasurement.EACH.name()));
        products.put("11", new Product("11", "Pasta",
                new BigDecimal("112"), false, UnitOfMeasurement.EACH.name()));

        Promotion buyX_getX_safeguard = new Promotion();
        buyX_getX_safeguard.setPromoId("1");
        buyX_getX_safeguard.setPromoType(PromoType.BUYX_GETX.name());
        buyX_getX_safeguard.setConditionQuantity(1); // if you buy 1 safeguard
        buyX_getX_safeguard.setActionQuantity(1);  // you take 1 free safeguard
        buyX_getX_safeguard.setDiscountType(DiscountType.PERCENT.name());
        buyX_getX_safeguard.setDiscountValue(new BigDecimal("100")); // set discount to 100% since the other is free
        buyX_getX_safeguard.setProductCondition(products.get("1")); // product safeguard has buy 1 take 1 promo
        buyX_getX_safeguard.setProductAction(products.get("1"));
        promotions.add(buyX_getX_safeguard);

        Promotion buyX_getY_paper_pencil = new Promotion();
        buyX_getY_paper_pencil.setPromoId("2");
        buyX_getY_paper_pencil.setPromoType(PromoType.BUYX_GETY.name());
        buyX_getY_paper_pencil.setConditionQuantity(1); // if you buy 1 paper
        buyX_getY_paper_pencil.setActionQuantity(2);  // you take 1 less 5(amount) discounted pencil
        buyX_getY_paper_pencil.setDiscountType(DiscountType.AMOUNT.name());
        buyX_getY_paper_pencil.setDiscountValue(new BigDecimal("10")); // set discount to 5 for productY = pencil
        buyX_getY_paper_pencil.setProductCondition(products.get("6")); // product paper is the condition product
        buyX_getY_paper_pencil.setProductAction(products.get("4")); // pencil is the action product
        promotions.add(buyX_getY_paper_pencil);

        Promotion sale_50_percent = new Promotion();
        // air-con and lightbulb are 50% off
        List<Product> saleProducts = new ArrayList<>();
        saleProducts.add(products.get("8")); saleProducts.add(products.get("9"));
        sale_50_percent.setPromoId("3");
        sale_50_percent.setPromoType(PromoType.SALE_PROMO.name());
        sale_50_percent.setDiscountType(DiscountType.PERCENT.name());
        sale_50_percent.setDiscountValue(new BigDecimal("50")); // set discount to 5 for productY = pencil
        sale_50_percent.setSaleProducts(saleProducts);
        promotions.add(sale_50_percent);
    }
}
