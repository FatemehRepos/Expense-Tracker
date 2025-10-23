package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RolePermissions extends SimpleEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Permission permission;

}
