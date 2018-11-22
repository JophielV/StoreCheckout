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

public class BulkItemTest {

    private DataSource dataSource = new DataSource();
    private HashMap<String, Product> products = dataSource.products;

    private StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();
    private ReceiptService receiptService = new ReceiptServiceImpl();

    @Test
    public void bulkItemTest() {
        System.out.println();
        System.out.println("************************** Bulk/Weighted Item Test **************************");

        Transaction transaction = storeCheckoutService.initializeTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());

        // assuming that we retrieved products from db with their barcodes(in this case we are considering hashmap)
        assertNotNull(products);

        transaction = storeCheckoutService.scanItem(transaction, products.get("5"), new BigDecimal("1"), new BigDecimal("2"));
        transaction = storeCheckoutService.scanItem(transaction, products.get("6"), new BigDecimal("1"), null);
        transaction = storeCheckoutService.scanItem(transaction, products.get("5"), new BigDecimal("1"), new BigDecimal("0.6"));

        receiptService.printReceipt(transaction);
    }
}
