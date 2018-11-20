package com.storecheckout.service.impl;

import com.storecheckout.service.api.ComputationService;

import java.math.BigDecimal;

public class ComputationServiceImpl implements ComputationService {

    public BigDecimal add(BigDecimal n1, BigDecimal n2) {
        return n1.add(n2);
    }
}
