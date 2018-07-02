package com.poc.assessment.ecommerce.shoppingcart.api.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartCategory {

    @JsonIgnore
    private Long categoryId;
    private String title;
    private Set<ShoppingCartItem> items;
}
