package com.poc.assessment.ecommerce.shoppingcart.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartCreateRequest {

    private Set<ShoppingCartItem> items;
}
