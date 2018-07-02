package com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItemEntity, Long> {

    Optional<Set<ShoppingCartItemEntity>> findAllByCartId(Long cartId);
}
