package com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
