package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CouponCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ApiController
@RequestMapping(path = "/api/ecommerce/coupons")
public class CouponsController {

    @Autowired
    private CouponService couponService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCoupon(@RequestBody CouponCreateRequest couponCreateRequest) {
        couponService.createCoupon(couponCreateRequest);
    }
}
