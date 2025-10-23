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

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double balance;
    private boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WalletType walletType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

}
