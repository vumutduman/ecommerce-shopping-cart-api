package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountCategory;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CouponCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CouponEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartDiscountEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CouponRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartDiscountRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;

    public Optional<CouponEntity> findCoupon(Long id) {
        return couponRepository.findById(id);
    }

    public void createCoupon(CouponCreateRequest couponCreateRequest) {
        final CouponEntity couponEntity = CouponEntity.builder()
                .threshold(couponCreateRequest.getThreshold())
                .discountType(couponCreateRequest.getType())
                .discountValue(couponCreateRequest.getDiscount())
                .build();
        couponRepository.save(couponEntity);
    }

    public void applyCoupon(Long cartId, Long couponId) {
        final ShoppingCartDiscountEntity discountEntity = ShoppingCartDiscountEntity.builder()
                .cartId(cartId)
                .discountId(couponId)
                .category(DiscountCategory.COUPON)
                .build();

        shoppingCartDiscountRepository.save(discountEntity);
    }
}
