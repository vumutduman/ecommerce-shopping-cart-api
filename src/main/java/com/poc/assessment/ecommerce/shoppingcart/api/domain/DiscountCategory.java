package com.poc.assessment.ecommerce.shoppingcart.api.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountCategory {

    CAMPAIGN("CAMPAIGN"), COUPON("COUPON");

    private final String val;

    public boolean isCampaign() {
        return this == CAMPAIGN;
    }

    public boolean isCoupon() {
        return this == COUPON;
    }
}
