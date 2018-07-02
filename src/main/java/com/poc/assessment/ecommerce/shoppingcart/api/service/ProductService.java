package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ProductCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ProductEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Long categoryId, ProductCreateRequest request) {
        final ProductEntity entity = ProductEntity.builder()
                .categoryId(categoryId)
                .title(request.getTitle())
                .price(request.getPrice())
                .build();
        productRepository.save(entity);
    }
}
