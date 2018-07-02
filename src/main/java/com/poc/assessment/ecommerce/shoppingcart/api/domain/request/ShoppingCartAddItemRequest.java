package com.poc.assessment.ecommerce.shoppingcart.api.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartAddItemRequest {

    private ShoppingCartItem item;
}
