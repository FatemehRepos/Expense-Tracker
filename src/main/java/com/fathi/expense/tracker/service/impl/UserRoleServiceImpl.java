package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.Role;
import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.entity.UserRoles;
import com.fathi.expense.tracker.repository.UserRoleRepository;
import com.fathi.expense.tracker.service.RoleService;
import com.fathi.expense.tracker.service.UserRoleService;
import com.fathi.expense.tracker.service.mapper.UserRolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final RoleService roleService;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRoles create(User user, String roleName) {
        Role role = roleService.find(roleName);
        return userRoleRepository.save(UserRolesMapper.mapToUserRoles(user, role));
    }

}
