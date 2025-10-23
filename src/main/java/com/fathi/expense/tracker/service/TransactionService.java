package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.enums.TransactionType;
import com.fathi.expense.tracker.model.request.TransactionRequest;
import com.fathi.expense.tracker.model.response.TransactionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    void create(TransactionRequest request);

    Page<TransactionResponse> getAll(TransactionType type, Pageable pageable);

}
