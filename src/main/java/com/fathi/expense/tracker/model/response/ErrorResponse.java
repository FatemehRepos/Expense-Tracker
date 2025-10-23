package com.fathi.expense.tracker.model.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(
        LocalDateTime timeStamp,
        int status,
        List<String> errors,
        String path) {
}
