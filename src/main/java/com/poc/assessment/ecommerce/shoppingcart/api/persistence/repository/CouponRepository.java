package com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
}
