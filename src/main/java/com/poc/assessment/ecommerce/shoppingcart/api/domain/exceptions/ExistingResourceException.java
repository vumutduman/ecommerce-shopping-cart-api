package com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExistingResourceException extends RuntimeException {

    public ExistingResourceException(String message) {
        super(message);
    }
}
