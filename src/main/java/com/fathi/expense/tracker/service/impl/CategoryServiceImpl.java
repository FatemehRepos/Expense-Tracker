package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.component.exception.RecordNotFoundException;
import com.fathi.expense.tracker.model.entity.Category;
import com.fathi.expense.tracker.repository.CategoryRepository;
import com.fathi.expense.tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("error.category.not.found"));
    }

}
