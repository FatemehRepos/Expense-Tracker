package com.fathi.expense.tracker.model.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record AuthenticationResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Date expireAt) {
}
