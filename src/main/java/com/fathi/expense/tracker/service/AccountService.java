package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.Wallet;

public interface AccountService {

    Wallet findById(Long id);

}
