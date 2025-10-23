package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.component.exception.RecordNotFoundException;
import com.fathi.expense.tracker.model.entity.Role;
import com.fathi.expense.tracker.repository.RoleRepository;
import com.fathi.expense.tracker.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role find(String role) {
        return roleRepository.findByName(role)
                .orElseThrow(() -> new RecordNotFoundException("errors.system.error"));
    }

}
