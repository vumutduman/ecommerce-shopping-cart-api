package com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientSideException extends RuntimeException {

    public ClientSideException(String message) {
        super(message);
    }
}
