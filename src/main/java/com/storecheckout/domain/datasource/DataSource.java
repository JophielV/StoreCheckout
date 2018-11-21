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
        products.put("12", new Product("12", "Chocolate",
                new BigDecimal("50"), false, UnitOfMeasurement.EACH.name()));
        products.put("13", new Product("13", "Notebook",
                new BigDecimal("30"), false, UnitOfMeasurement.EACH.name()));

        Promotion buy1_get1_safeguard = new Promotion();
        buy1_get1_safeguard.setPromoId("1");
        buy1_get1_safeguard.setPromoType(PromoType.BUYX_GETX.name());
        buy1_get1_safeguard.setConditionQuantity(1); // if you buy 1 safeguard
        buy1_get1_safeguard.setActionQuantity(1);  // you take 1 free safeguard
        buy1_get1_safeguard.setDiscountType(DiscountType.PERCENT.name());
        buy1_get1_safeguard.setDiscountValue(new BigDecimal("100")); // set discount to 100% since the other is free
        buy1_get1_safeguard.setProductCondition(products.get("1")); // product safeguard has buy 1 take 1 promo
        buy1_get1_safeguard.setProductAction(products.get("1"));
        promotions.add(buy1_get1_safeguard);

        Promotion buy1_get2_notebook = new Promotion();
        buy1_get2_notebook.setPromoId("2");
        buy1_get2_notebook.setPromoType(PromoType.BUYX_GETX.name());
        buy1_get2_notebook.setConditionQuantity(1); // if you buy 1 notebook
        buy1_get2_notebook.setActionQuantity(2);  // you take 2 free notebook
        buy1_get2_notebook.setDiscountType(DiscountType.PERCENT.name());
        buy1_get2_notebook.setDiscountValue(new BigDecimal("100")); // set discount to 100% since the other is free
        buy1_get2_notebook.setProductCondition(products.get("13")); // product safeguard has buy 1 take 1 promo
        buy1_get2_notebook.setProductAction(products.get("13"));
        promotions.add(buy1_get2_notebook);

        Promotion buy1_chocolate_get2_pasta = new Promotion();
        buy1_chocolate_get2_pasta.setPromoId("3");
        buy1_chocolate_get2_pasta.setPromoType(PromoType.BUYX_GETY.name());
        buy1_chocolate_get2_pasta.setConditionQuantity(1); // if you buy 1 chocolate
        buy1_chocolate_get2_pasta.setActionQuantity(2);  // you take 2 free pasta
        buy1_chocolate_get2_pasta.setDiscountType(DiscountType.PERCENT.name());
        buy1_chocolate_get2_pasta.setDiscountValue(new BigDecimal("100")); // set discount to 100% since the other is free
        buy1_chocolate_get2_pasta.setProductCondition(products.get("12")); // product safeguard has buy 1 take 1 promo
        buy1_chocolate_get2_pasta.setProductAction(products.get("11"));
        promotions.add(buy1_chocolate_get2_pasta);

    }
}
