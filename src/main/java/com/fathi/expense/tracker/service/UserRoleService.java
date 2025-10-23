package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.entity.UserRoles;

public interface UserRoleService {

    UserRoles create(User user, String roleName);

}

