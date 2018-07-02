package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CampaignCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping(path = "/api/ecommerce/campaigns")
public class CampaignsController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCoupon(@RequestBody CampaignCreateRequest campaignCreateRequest) {
        campaignService.createCampaign(campaignCreateRequest);
    }
}
