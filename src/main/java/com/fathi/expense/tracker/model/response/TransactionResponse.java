package com.fathi.expense.tracker.model.response;

import lombok.Builder;

@Builder
public record TransactionResponse(
        double amount,
        String TransactionType,
        String category,
        String categoryType) {
}
