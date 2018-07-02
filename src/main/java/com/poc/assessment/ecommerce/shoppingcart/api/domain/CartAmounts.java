package com.poc.assessment.ecommerce.shoppingcart.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@RequiredArgsConstructor
public class CartAmounts {

    private final BigDecimal totalAmountAfterDiscounts;
    private final BigDecimal campaignDiscount;
    private final BigDecimal couponDiscount;
}
