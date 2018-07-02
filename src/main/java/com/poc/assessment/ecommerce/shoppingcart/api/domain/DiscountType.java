package com.poc.assessment.ecommerce.shoppingcart.api.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountType {

    RATE("RATE"), AMOUNT("AMOUNT");

    private final String val;

    public Boolean isRate(){
        return this == RATE;
    }

    public Boolean isAmount(){
        return this == AMOUNT;
    }
}
