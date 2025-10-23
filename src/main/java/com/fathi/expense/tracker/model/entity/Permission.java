package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Permissions")
public class Permission extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

}
