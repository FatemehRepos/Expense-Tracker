package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.component.exception.RecordNotFoundException;
import com.fathi.expense.tracker.model.entity.Wallet;
import com.fathi.expense.tracker.repository.AccountRepository;
import com.fathi.expense.tracker.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Wallet findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("error.account.not.found"));
    }

}
