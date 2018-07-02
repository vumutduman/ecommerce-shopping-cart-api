package com.poc.assessment.ecommerce.shoppingcart.api.domain.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private Set<ShoppingCartCategory> categories;
}
