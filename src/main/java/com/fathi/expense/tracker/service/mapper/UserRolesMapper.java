package com.fathi.expense.tracker.service.mapper;

import com.fathi.expense.tracker.model.entity.Role;
import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.entity.UserRoles;

public class UserRolesMapper {


    public static UserRoles mapToUserRoles(User user, Role role) {
        return UserRoles.builder()
                .user(user)
                .role(role)
                .build();
    }

}
