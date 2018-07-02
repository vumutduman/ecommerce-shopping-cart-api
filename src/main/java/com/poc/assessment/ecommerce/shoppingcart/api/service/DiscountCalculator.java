package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountType;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CampaignEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CouponEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.CartAmounts;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountType;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartCategory;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CampaignEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CouponEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartDiscountEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartDiscountRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class DiscountCalculator {

    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;
    private final CampaignService campaignService;
    private final CouponService couponService;

    public CartAmounts calculateFor(Long cartId, Cart cart) {
        final Optional<Set<ShoppingCartDiscountEntity>> optionalDiscountEntities = shoppingCartDiscountRepository.findAllByCartId(cartId);
        if (!optionalDiscountEntities.isPresent()) {
            return CartAmounts.builder().build();
        }

        optionalDiscountEntities
                .get()
                .forEach(e -> calculateDiscount(e, cart));
        return CartAmounts.builder().build();
    }

    private void calculateDiscount(ShoppingCartDiscountEntity shoppingCartDiscountEntity, Cart cart) {
        if (shoppingCartDiscountEntity.getCategory().isCampaign()) {
            calculateCampaign(shoppingCartDiscountEntity, cart);
            return;
        }

        if (shoppingCartDiscountEntity.getCategory().isCoupon()) {
            calculateCoupon(shoppingCartDiscountEntity, cart);
            return;
        }
    }

    private void calculateCoupon(ShoppingCartDiscountEntity discountEntity, Cart cart) {
        final Optional<CouponEntity> optionalCouponEntity = couponService.findCoupon(discountEntity.getDiscountId());
        if (!optionalCouponEntity.isPresent()) {
            return;
        }

        final CouponEntity couponEntity = optionalCouponEntity.get();
        //todo:
    }

    private void calculateCampaign(ShoppingCartDiscountEntity discountEntity, Cart cart) {
        final Optional<CampaignEntity> optionalCampaignEntity = campaignService.find(discountEntity.getDiscountId());
        if (!optionalCampaignEntity.isPresent()) {
            return;
        }

        final CampaignEntity campaignEntity = optionalCampaignEntity.get();
        final Optional<ShoppingCartCategory> optionalCartCategory = cart.getCategories()
                .stream()
                .filter(e -> e.getCategoryId() == campaignEntity.getCategoryId())
                .findFirst();
        if (!optionalCartCategory.isPresent()) {
            return;
        }

        final Set<ShoppingCartItem> shoppingCartItems = optionalCartCategory.get().getItems();
        if (!campaignService.isApplicable(campaignEntity, shoppingCartItems)) {
            return;
        }

        shoppingCartItems
                .stream()
                .forEach(e -> calculateShoppingCartItem(e, campaignEntity.getDiscountType(), campaignEntity.getDiscountValue()));
    }

    private void calculateShoppingCartItem(ShoppingCartItem shoppingCartItem, DiscountType type, BigDecimal discount) {
        final BigDecimal quantity = BigDecimal.valueOf(shoppingCartItem.getQuantity());

        BigDecimal discountValue = BigDecimal.ZERO;
        if (type.isRate()) {
            discountValue = shoppingCartItem.getUnitPrice().multiply(discount).divide(BigDecimal.valueOf(100)).multiply(quantity);
        } else if (type.isAmount()) {
            discountValue = shoppingCartItem.getUnitPrice().subtract(discount).multiply(quantity);
        }

        final BigDecimal newTotalDiscount = shoppingCartItem.getTotalDiscount().add(discountValue);
        shoppingCartItem.setTotalDiscount(newTotalDiscount);

        final BigDecimal totalValue = shoppingCartItem.getUnitPrice().multiply(quantity);
        if (totalValue.compareTo(newTotalDiscount) > 0) {
            shoppingCartItem.setTotalPrice(totalValue.subtract(newTotalDiscount));
        } else {
            shoppingCartItem.setTotalPrice(BigDecimal.ZERO);
        }
    }
}
