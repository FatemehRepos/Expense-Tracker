package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserGoal extends BaseEntity {

    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private BigDecimal targetAmount;
    @Column(nullable = false)
    private BigDecimal currentAmount;
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

}
