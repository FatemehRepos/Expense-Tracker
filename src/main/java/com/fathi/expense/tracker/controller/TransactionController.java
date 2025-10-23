package com.fathi.expense.tracker.controller;

import com.fathi.expense.tracker.model.enums.TransactionType;
import com.fathi.expense.tracker.model.request.TransactionRequest;
import com.fathi.expense.tracker.model.response.TransactionResponse;
import com.fathi.expense.tracker.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<?> create(@RequestBody @Valid TransactionRequest request) {
        transactionService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Page<TransactionResponse>> getAll(
            @RequestParam(required = false) TransactionType type,
            Pageable pageable) {
        return ResponseEntity.ok(transactionService.getAll(type, pageable));
    }


}
