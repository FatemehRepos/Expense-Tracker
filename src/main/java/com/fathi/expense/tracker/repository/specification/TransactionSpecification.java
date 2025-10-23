package com.fathi.expense.tracker.repository.specification;

import com.fathi.expense.tracker.model.entity.Transaction;
import com.fathi.expense.tracker.model.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> transactionSpecification(long userId, TransactionType type) {
        return getALlSpecifications(userId, type).stream()
                .reduce(Specification::and)
                .orElse(getAll());
    }

    private static List<Specification<Transaction>> getALlSpecifications(long userId, TransactionType type) {
        List<Specification<Transaction>> specs = new ArrayList<>();
        if (userId != 0) specs.add(getByTransactionUser(userId));
        if (type != null) specs.add(getByTransactionType(type));
        return specs;
    }

    private static Specification<Transaction> getByTransactionUser(long userId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    private static Specification<Transaction> getByTransactionType(TransactionType type) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("type"), type);
    }

    private static Specification<Transaction> getAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();
    }

}
