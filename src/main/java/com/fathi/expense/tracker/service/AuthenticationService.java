package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.response.AuthenticationResponse;

public interface AuthenticationService {

    User register(String username, String password);

    AuthenticationResponse login(String username);

    AuthenticationResponse refresh(String refreshToken);

    void logout(String refreshToken);

}
