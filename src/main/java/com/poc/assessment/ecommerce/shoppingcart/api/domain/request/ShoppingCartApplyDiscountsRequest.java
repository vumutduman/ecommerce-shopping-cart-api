package com.poc.assessment.ecommerce.shoppingcart.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartApplyDiscountsRequest {

    private Set<Long> campaigns;
    private Set<Long> coupons;
}
