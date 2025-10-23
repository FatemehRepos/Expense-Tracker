package com.fathi.expense.tracker.repository;

import com.fathi.expense.tracker.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Wallet, Long> {

}
