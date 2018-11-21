package com.storecheckout.service;

import com.storecheckout.service.api.StoreCheckoutService;
import com.storecheckout.service.impl.StoreCheckoutServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertNotNull;

public class StoreCheckoutServiceTest {
    private final Logger _LOG = LoggerFactory.getLogger(this.getClass());

    StoreCheckoutService storeCheckoutService = new StoreCheckoutServiceImpl();

    @Test
    public void testInitializeTransaction() {
    }
}
