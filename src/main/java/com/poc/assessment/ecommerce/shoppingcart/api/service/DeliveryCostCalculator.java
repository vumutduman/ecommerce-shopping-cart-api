package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class DeliveryCostCalculator {

    private final BigDecimal costPerDelivery;
    private final BigDecimal costPerProduct;
    private final BigDecimal fixedCost;

    public BigDecimal calculateFor(Cart cart) {
        final BigDecimal distinctCategory = BigDecimal.valueOf(cart.getCategories().size());
        final BigDecimal distinctProduct = BigDecimal.ZERO;
        cart.getCategories()
                .forEach(category -> distinctProduct.add(BigDecimal.valueOf(category.getItems().size())));

        return formula(distinctCategory, distinctProduct);
    }

    private BigDecimal formula(BigDecimal numberOfDeliveries, BigDecimal numberOfProducts) {
        return costPerDelivery.multiply(numberOfDeliveries).add(costPerProduct.multiply(numberOfProducts)).add(fixedCost);
    }
}
