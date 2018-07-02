package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CouponCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.service.CouponService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponsController.class)
public class CouponsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CouponService mockCouponService;

    @Test
    public void createCoupon() throws Exception {
        //given
        String requestJson = "{\"threshold\": 100.5, \"type\": \"RATE\", \"discount\": 10.5}";

        doNothing().when(mockCouponService).createCoupon(any(CouponCreateRequest.class));

        final ResultActions perform = mockMvc.perform(post("/api/ecommerce/coupons")
                .content(requestJson.getBytes())
                .contentType(MediaType.APPLICATION_JSON));
        //when
        perform.andExpect(status().isCreated());

        //then
    }
}