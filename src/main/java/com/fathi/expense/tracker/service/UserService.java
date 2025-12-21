package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.User;

public interface UserService {

    User save(String username, String password);

//    void update(String newPassword);

    User getAuthenticatedUser();

    User getUserByUsername(String username);

//    User getUserByEmail(String email);

}
