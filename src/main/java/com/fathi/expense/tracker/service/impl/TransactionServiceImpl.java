package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.Transaction;
import com.fathi.expense.tracker.model.enums.TransactionType;
import com.fathi.expense.tracker.model.request.TransactionRequest;
import com.fathi.expense.tracker.model.response.TransactionResponse;
import com.fathi.expense.tracker.repository.TransactionRepository;
import com.fathi.expense.tracker.repository.specification.TransactionSpecification;
import com.fathi.expense.tracker.service.AccountService;
import com.fathi.expense.tracker.service.CategoryService;
import com.fathi.expense.tracker.service.TransactionService;
import com.fathi.expense.tracker.service.UserService;
import com.fathi.expense.tracker.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final TransactionRepository transactionRepository;

    @Override
    public void create(TransactionRequest request) {
        transactionRepository.save(map(request));
    }

    @Override
    public Page<TransactionResponse> getAll(TransactionType type, Pageable pageable) {
        return transactionRepository.findAll(
                        TransactionSpecification.transactionSpecification(
                                userService.getAuthenticatedUser().getId(), type), pageable)
                .map(TransactionMapper::mapToTransactionMapper);
    }

    private Transaction map(TransactionRequest request) {
        return Transaction.builder()
                .amount(BigDecimal.valueOf(request.amount()))
                .type(request.type())
                .category(categoryService.findById(request.categoryId()))
                .wallet(accountService.findById(request.accountId()))
                .user(userService.getAuthenticatedUser())
                .build();
    }

}
