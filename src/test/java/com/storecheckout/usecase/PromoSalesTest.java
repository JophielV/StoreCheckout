package com.storecheckout.usecase;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.product.Promotion;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.PromotionService;
import com.storecheckout.service.api.ReceiptService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.impl.PromotionServiceImpl;
import com.storecheckout.service.impl.ReceiptServiceImpl;
import com.storecheckout.service.impl.StoreCheckoutServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class PromoSalesTest {

    private final Logger _LOG = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource = new DataSource();
    private StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();
    private ReceiptService receiptService = new ReceiptServiceImpl();
    private HashMap<String, Product> products = dataSource.products;
    private List<Promotion> promotions = dataSource.promotions;

    @Test
    public void promoSalesBuyXGetXTest_1() {
        _LOG.info("-- Promo Sales BuyXGetX Test Scenario --");
        _LOG.info("-- Creating a new transaction for customer --");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("2"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("11"), new BigDecimal("2"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("2"));

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetXTest_2() {
        _LOG.info("-- Promo Sales BuyXGetX Test Scenario --");
        _LOG.info("-- Creating a new transaction for customer --");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("8"), new BigDecimal("3"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("13"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("9"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("3"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("13"), new BigDecimal("2"));

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetXTest_3() {
        _LOG.info("-- Promo Sales BuyXGetX Test Scenario --");
        _LOG.info("-- Creating a new transaction for customer --");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("14"), new BigDecimal("2"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("1"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("14"), new BigDecimal("1"));

        receiptService.printReceipt(transaction);
    }

    @Test
    public void promoSalesBuyXGetYTest() {
        _LOG.info("-- Promo Sales BuyXGetX Test Scenario --");
        _LOG.info("-- Creating a new transaction for customer --");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        // assuming that we retrieved promotions from db(in this case we are considering hashmap)
        assertNotNull(products);
        assertNotNull(promotions);

        transaction = storeCheckoutService.scanItem(transaction, products.get("2"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("12"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("8"), new BigDecimal("1"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("11"), new BigDecimal("2"));

        receiptService.printReceipt(transaction);
    }
}
