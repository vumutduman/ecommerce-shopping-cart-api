package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ClientSideException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ExistingResourceException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ResourceNotFoundException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ServerSideException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice(annotations = {
        ApiController.class
})
public class ApiControllerAdvice {

    @ExceptionHandler(ClientSideException.class)
    public final String handleClientSideException(ClientSideException e, HttpServletResponse httpServletResponse) {
        log.error("ApiControllerAdvice handle ClientSideException.", e);
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return "Please, check your request";
    }

    @ExceptionHandler(ExistingResourceException.class)
    public final String handleExistingResourceException(ExistingResourceException e, HttpServletResponse httpServletResponse) {
        log.error("ApiControllerAdvice handle ExistingResourceException.", e);
        httpServletResponse.setStatus(HttpStatus.CONFLICT.value());
        return "Existing Resource." + e.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final void handleResourceNotFoundException(ResourceNotFoundException e, HttpServletResponse httpServletResponse) {
        log.error("ApiControllerAdvice handle ResourceNotFoundException.", e);
        httpServletResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return;
    }

    @ExceptionHandler(ServerSideException.class)
    public final String handleRuntimeException(ServerSideException e, HttpServletResponse httpServletResponse) {
        log.error("ApiControllerAdvice handle ServerSideException.", e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "Internal Server Error:" + e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    public final String handleRuntimeException(RuntimeException e, HttpServletResponse httpServletResponse) {
        log.error("ApiControllerAdvice handle RuntimeException.", e);
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "Internal Server Error:" + e.getMessage();
    }
}
