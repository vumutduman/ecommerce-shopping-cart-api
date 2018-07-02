package com.poc.assessment.ecommerce.shoppingcart.api.domain.request;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponCreateRequest {

    private BigDecimal threshold;
    private DiscountType type;
    private BigDecimal discount;
}
