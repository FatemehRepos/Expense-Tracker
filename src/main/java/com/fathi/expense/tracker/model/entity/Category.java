package com.fathi.expense.tracker.model.entity;

import com.fathi.expense.tracker.model.enums.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

}
