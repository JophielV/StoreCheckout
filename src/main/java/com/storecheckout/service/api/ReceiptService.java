package com.storecheckout.service.api;

import com.storecheckout.domain.model.transaction.Transaction;

public interface ReceiptService {

    void printReceipt(Transaction transaction);
}
