package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CategoryCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CategoryEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        final CategoryEntity categoryEntity = CategoryEntity.builder()
                .title(categoryCreateRequest.getTitle())
                .build();
        categoryRepository.save(categoryEntity);
    }

    public void createCategory(Long categoryId, CategoryCreateRequest categoryCreateRequest) {
        final CategoryEntity categoryEntity = CategoryEntity.builder()
                .parentCategoryId(categoryId)
                .title(categoryCreateRequest.getTitle())
                .build();
        categoryRepository.save(categoryEntity);
    }
}
