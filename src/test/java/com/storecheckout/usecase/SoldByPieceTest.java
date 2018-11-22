package com.storecheckout.usecase;

import com.storecheckout.domain.datasource.DataSource;
import com.storecheckout.domain.model.product.Product;
import com.storecheckout.domain.model.transaction.Transaction;
import com.storecheckout.service.api.ReceiptService;
import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.impl.ReceiptServiceImpl;
import com.storecheckout.service.impl.StoreCheckoutServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static junit.framework.Assert.assertNotNull;

public class SoldByPieceTest {

    private DataSource dataSource = new DataSource();
    private HashMap<String, Product> products = dataSource.products;

    private StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();
    private ReceiptService receiptService = new ReceiptServiceImpl();

    @Test
    public void firstScenario() {
        System.out.println();
        System.out.println("************************** Sold by Piece Test Scenario **************************");

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
