package com.storecheckout.usecase;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.ReceiptService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.impl.ReceiptServiceImpl;
import com.storecheckout.service.impl.StoreCheckoutServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class PromoSalesTest {

    private DataSource dataSource = new DataSource();
    private HashMap<String, Product> products = dataSource.products;
    private List<Promotion> promotions = dataSource.promotions;

    private StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();
    private ReceiptService receiptService = new ReceiptServiceImpl();

    @Test
    public void promoSalesBuyXGetXTest_1() {
        System.out.println();
        System.out.println("************************** Promo Sales Buy 1 Safeguard Get1 free Safeguard Test Scenario **************************");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("2"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("11"), new BigDecimal("2"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("2"), null);

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetXTest_2() {
        System.out.println();
        System.out.println("************************** Promo Sales Buy 1 Notebook Get 2 free Notebook Test Scenario **************************");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("8"), new BigDecimal("3"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("13"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("9"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("13"), new BigDecimal("2"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("3"), new BigDecimal("1"), null);

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetXTest_3() {
        System.out.println();
        System.out.println("************************** Promo Sales Buy 2 Magazine Get 1 free Magazine Test Scenario **************************");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("14"), new BigDecimal("2"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("14"), new BigDecimal("1"), null);

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetYTest() {
        System.out.println();
        System.out.println("************************** Promo Sales Buy 1 Chocalate Get 2 free Pasta Test Scenario **************************");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("2"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("12"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("8"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("11"), new BigDecimal("2"), null);

        receiptService.printReceipt(transaction);
    }
}
