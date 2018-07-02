package com.poc.assessment.ecommerce.shoppingcart.api.converter;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ProductEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartItemEntity;

import java.math.BigDecimal;

public class CartItemConverter {

    public ShoppingCartItem convertToCartItem(ProductEntity productEntity, ShoppingCartItemEntity shoppingCartItemEntity) {
        return ShoppingCartItem.builder()
                .title(productEntity.getTitle())
                .quantity(shoppingCartItemEntity.getQuantity())
                .unitPrice(shoppingCartItemEntity.getProductUnitPrice())
                .totalDiscount(BigDecimal.ZERO)
                .totalPrice(calculateTotalPrice(shoppingCartItemEntity))
                .build();
    }

    private BigDecimal calculateTotalPrice(ShoppingCartItemEntity shoppingCartItemEntity) {
        return shoppingCartItemEntity.getProductUnitPrice().multiply(BigDecimal.valueOf(shoppingCartItemEntity.getQuantity()));
    }
}
