package com.storecheckout.domain.datasource;

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
    }
}
