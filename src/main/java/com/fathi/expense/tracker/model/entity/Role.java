package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "role")
    private List<RolePermissions> rolePermissions;

}
