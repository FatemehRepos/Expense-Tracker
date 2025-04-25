package com.fathi.expense.tracker.model.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record AuthenticationResponse(
        String accessToken,
        String tokenType,
        Date expireAt) {
}
