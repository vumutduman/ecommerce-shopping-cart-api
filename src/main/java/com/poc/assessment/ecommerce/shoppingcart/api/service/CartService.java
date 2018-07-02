package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartAddItemRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartItemRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.CartAmounts;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ClientSideException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartAddItemRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartApplyDiscountsRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartResponse;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartsResponse;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartItemEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartItemRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@RequiredArgsConstructor
public class CartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final CampaignService campaignService;
    private final CouponService couponService;
    private final CartConverter cartConverter;
    private final DiscountCalculator discountCalculator;
    private final DeliveryCostCalculator deliveryCostCalculator;

    public void createCart(Long ownerId, ShoppingCartCreateRequest shoppingCartCreateRequest) {
        final ShoppingCartEntity shoppingCartEntity = ShoppingCartEntity.builder()
                .cartOwnerId(ownerId)
                .build();
        final ShoppingCartEntity cartEntity = shoppingCartRepository.save(shoppingCartEntity);

        shoppingCartCreateRequest.getItems()
                .forEach(item -> addCartItem(cartEntity.getId(), item));
    }

    public void addItemIntoCart(Long cartId, ShoppingCartAddItemRequest shoppingCartAddItemRequest) {
        final ShoppingCartEntity shoppingCartEntity = checkShoppingCart(cartId);
        addCartItem(shoppingCartEntity.getId(), shoppingCartAddItemRequest.getItem());
    }

    public void applyDiscountsToCart(Long cartId, ShoppingCartApplyDiscountsRequest applyDiscountsRequest) {
        checkShoppingCart(cartId);
        applyDiscountsRequest.getCampaigns().forEach(campaignId -> campaignService.applyCampaign(cartId, campaignId));
        applyDiscountsRequest.getCoupons().forEach(couponId -> couponService.applyCoupon(cartId, couponId));
    }

    public ShoppingCartResponse findCart(Long cartId) {
        final ShoppingCartEntity shoppingCartEntity = checkShoppingCart(cartId);
        final Cart cart = cartConverter.groupedCart(shoppingCartEntity);
        final CartAmounts cartAmounts = discountCalculator.calculateFor(cartId, cart);
        final BigDecimal deliveryCost = deliveryCostCalculator.calculateFor(cart);

        return ShoppingCartResponse.builder()
                .cart(cart)
                .totalAmountAfterDiscounts(cartAmounts.getTotalAmountAfterDiscounts())
                .campaignDiscount(cartAmounts.getCampaignDiscount())
                .couponDiscount(cartAmounts.getCouponDiscount())
                .deliveryCost(deliveryCost)
                .build();
    }

    public ShoppingCartsResponse findAllCarts() {
        final Set<Long> cartIds = shoppingCartRepository.findAllShoppingCartIds();
        return ShoppingCartsResponse.builder().carts(cartIds).build();
    }

    private ShoppingCartEntity checkShoppingCart(Long cartId) {
        return shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ClientSideException("Cart can not found."));
    }

    private void addCartItem(Long cartId, ShoppingCartItem item) {
        final ShoppingCartItemEntity shoppingCartItemEntity = ShoppingCartItemEntity.builder()
                .cartId(cartId)
                .productId(item.getProduct())
                .quantity(item.getQuantity())
                .productUnitPrice(item.getUnitPrice())
                .build();
        shoppingCartItemRepository.save(shoppingCartItemEntity);
    }

}
