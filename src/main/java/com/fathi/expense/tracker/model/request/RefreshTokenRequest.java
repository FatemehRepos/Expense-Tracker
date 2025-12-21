package com.fathi.expense.tracker.model.request;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(String refreshToken) {
}
