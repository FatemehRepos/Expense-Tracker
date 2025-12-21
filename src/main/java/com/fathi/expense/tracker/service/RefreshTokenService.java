package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.RefreshToken;
import com.fathi.expense.tracker.model.entity.User;

public interface RefreshTokenService {

    String issue(User user);

    RefreshToken validate(String token);

    void revoke(RefreshToken token);

    void revokeAll(long userId);

}
