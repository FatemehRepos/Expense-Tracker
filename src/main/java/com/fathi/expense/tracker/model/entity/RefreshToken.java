package com.fathi.expense.tracker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends BaseEntity {

    private String token;
    private LocalDateTime expiresAt;
    private boolean revoked;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private User user;

}
