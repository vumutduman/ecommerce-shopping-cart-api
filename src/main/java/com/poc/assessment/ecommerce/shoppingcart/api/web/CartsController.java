package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartAddItemRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartApplyDiscountsRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartResponse;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartsResponse;
import com.poc.assessment.ecommerce.shoppingcart.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping(path = "/api/ecommerce/carts")
public class CartsController {

    @Autowired
    private CartService cartService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartsResponse getCarts(){
        return cartService.findAllCarts();
    }

    @GetMapping(path = "/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCartResponse getCart(@PathVariable Long cartId){
        return cartService.findCart(cartId);
    }

    @PostMapping(path = "/owners/{ownerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@PathVariable Long ownerId, @RequestBody ShoppingCartCreateRequest shoppingCartCreateRequest) {
        cartService.createCart(ownerId, shoppingCartCreateRequest);
    }

    @PutMapping(path = "/{cartId}/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addItem(@PathVariable Long cartId, @RequestBody ShoppingCartAddItemRequest shoppingCartAddItemRequest) {
        cartService.addItemIntoCart(cartId, shoppingCartAddItemRequest);
    }

    @PutMapping(path = "/{cartId}/discounts", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void applyDiscounts(@PathVariable Long cartId, @RequestBody ShoppingCartApplyDiscountsRequest applyDiscountsRequest) {
        cartService.applyDiscountsToCart(cartId, applyDiscountsRequest);
    }

    @PostMapping(path = "/{cartId}/deliver", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void deliverCart() {

    }
}
