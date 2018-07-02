package com.poc.assessment.ecommerce.shoppingcart.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItem {

    private Long product;
    private Integer quantity;
    private BigDecimal unitPrice;
}
