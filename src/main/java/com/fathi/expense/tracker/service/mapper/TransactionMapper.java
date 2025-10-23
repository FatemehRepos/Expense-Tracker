package com.fathi.expense.tracker.service.mapper;

import com.fathi.expense.tracker.model.entity.Transaction;
import com.fathi.expense.tracker.model.request.TransactionRequest;
import com.fathi.expense.tracker.model.response.TransactionResponse;

public class TransactionMapper {

    public static TransactionResponse mapToTransactionMapper(Transaction transaction) {
        return TransactionResponse.builder()
                .TransactionType(transaction.getType().toString())
                .TransactionType(transaction.getCategory().getType().toString())
                .build();
    }

}
