package org.daypilot.demo.html5eventcalendarspring.service;



import org.daypilot.demo.html5eventcalendarspring.model.Category;

import java.util.List;

public interface CategoryService {
    //InvalidCategoryIdException
    Category findById(Long id);

    List<Category> listAll();

    Category create(String name);
}
