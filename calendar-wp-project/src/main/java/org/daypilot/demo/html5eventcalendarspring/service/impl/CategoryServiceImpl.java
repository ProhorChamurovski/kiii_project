package org.daypilot.demo.html5eventcalendarspring.service.impl;


import org.daypilot.demo.html5eventcalendarspring.model.Category;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidCategoryIdException;
import org.daypilot.demo.html5eventcalendarspring.repository.CategoryRepository;
import org.daypilot.demo.html5eventcalendarspring.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);
    }

    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }
}
