package com.fathi.expense.tracker.repository;

import com.fathi.expense.tracker.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public interface TransactionRepository extends
        JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Override
    @EntityGraph(attributePaths = {"category"})
    Page<Transaction> findAll(Specification<Transaction> spec, Pageable pageable);

}
