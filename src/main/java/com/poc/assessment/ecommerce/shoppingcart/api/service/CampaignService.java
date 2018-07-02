package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CampaignEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountCategory;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CampaignCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CampaignEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartDiscountEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CampaignRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartDiscountRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;

    public Optional<CampaignEntity> find(Long id) {
        return campaignRepository.findById(id);
    }

    public Boolean isApplicable(CampaignEntity campaignEntity, Set<ShoppingCartItem> shoppingCartItems){
        return (shoppingCartItems.size() >= campaignEntity.getThreshold());
    }

    public void createCampaign(CampaignCreateRequest campaignCreateRequest) {
        final CampaignEntity campaignEntity = CampaignEntity.builder()
                .categoryId(campaignCreateRequest.getCategoryId())
                .title(campaignCreateRequest.getTitle())
                .threshold(campaignCreateRequest.getThreshold())
                .discountType(campaignCreateRequest.getType())
                .discountValue(campaignCreateRequest.getDiscount())
                .build();
        campaignRepository.save(campaignEntity);
    }

    public void applyCampaign(Long cartId, Long campaignId) {
        final ShoppingCartDiscountEntity discountEntity = ShoppingCartDiscountEntity.builder()
                .cartId(cartId)
                .discountId(campaignId)
                .category(DiscountCategory.CAMPAIGN)
                .build();

        shoppingCartDiscountRepository.save(discountEntity);
    }
}
