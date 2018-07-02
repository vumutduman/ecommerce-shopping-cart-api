package com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
