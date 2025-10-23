package com.fathi.expense.tracker.model.request;

import com.fathi.expense.tracker.model.enums.TransactionType;
import jakarta.validation.constraints.Positive;
import lombok.Builder;


@Builder
public record TransactionRequest(
        @Positive
        double amount,
        TransactionType type,
        long categoryId,
        long accountId,
        String description) {
}
