package com.casestudycheckerbackend.service.categoryService;

import com.casestudycheckerbackend.models.CategoryService;
import com.casestudycheckerbackend.repository.CategoryServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryServicesService implements ICategoryServicesService{
    @Autowired
    CategoryServiceRepository categoryServiceRepository;
    @Override
    public Iterable<CategoryService> findAll() {
        return categoryServiceRepository.findAll();
    }

    @Override
    public Optional<CategoryService> findById(Long id) {
        return categoryServiceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        categoryServiceRepository.deleteById(id);

    }

    @Override
    public CategoryService save(CategoryService categoryService) {
        return categoryServiceRepository.save(categoryService);
    }
}
