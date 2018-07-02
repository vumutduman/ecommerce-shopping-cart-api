package com.poc.assessment.ecommerce.shoppingcart.api.domain.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponse {

    private Cart cart;
    private BigDecimal totalAmountAfterDiscounts;
    private BigDecimal campaignDiscount;
    private BigDecimal couponDiscount;
    private BigDecimal deliveryCost;
}
