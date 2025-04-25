package com.fathi.expense.tracker.model.request;

import com.fathi.expense.tracker.validation.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotBlank
        @Size(min = 8, message = "error.username.validation.failed")
        String username,
        @Password
        String password) {
}
