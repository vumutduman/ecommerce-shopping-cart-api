package com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartDiscountEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartDiscountRepository extends JpaRepository<ShoppingCartDiscountEntity, Long> {

    Optional<Set<ShoppingCartDiscountEntity>> findAllByCartId(Long cartId);
}
