package com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServerSideException extends RuntimeException {

    public ServerSideException(String message){
        super(message);
    }
}
