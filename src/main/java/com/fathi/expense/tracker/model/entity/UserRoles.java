package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles extends SimpleEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;

}
