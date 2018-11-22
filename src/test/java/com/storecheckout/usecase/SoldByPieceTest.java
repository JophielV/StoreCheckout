package com.storecheckout.usecase;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.ReceiptService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.impl.ReceiptServiceImpl;
import com.storecheckout.service.impl.StoreCheckoutServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;

import static junit.framework.Assert.assertNotNull;

public class SoldByPieceTest {

    private final Logger _LOG = LoggerFactory.getLogger(this.getClass());
    private DataSource dataSource = new DataSource();
    private StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();
    private ReceiptService receiptService = new ReceiptServiceImpl();
    private HashMap<String, Product> products = dataSource.products;

    @Test
    public void firstScenario() {
        _LOG.info("-- Sold By Piece Test Scenario 1 --");
        _LOG.info("-- Creating a new transaction for customer --");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        assertNotNull(products);
        transaction = storeCheckoutService.scanItem(transaction, products.get("10"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("2"), new BigDecimal("3"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("11"), new BigDecimal("5"), null);

        receiptService.printReceipt(transaction);
    }
}
