package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.entity.UserRoles;

public interface UserRoleService {

    UserRoles save(User user, String roleName);

}

