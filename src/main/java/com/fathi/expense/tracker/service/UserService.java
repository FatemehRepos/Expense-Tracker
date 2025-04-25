package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.request.AuthenticationRequest;
import com.fathi.expense.tracker.model.response.UserResponse;

public interface UserService {

    UserResponse save(AuthenticationRequest request);

}
