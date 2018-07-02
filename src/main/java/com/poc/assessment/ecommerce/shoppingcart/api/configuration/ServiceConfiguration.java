package com.poc.assessment.ecommerce.shoppingcart.api.configuration;

import com.poc.assessment.ecommerce.shoppingcart.api.service.CategoryService;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.*;
import com.poc.assessment.ecommerce.shoppingcart.api.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CampaignService campaignService(CampaignRepository campaignRepository, ShoppingCartDiscountRepository shoppingCartDiscountRepository) {
        return new CampaignService(campaignRepository, shoppingCartDiscountRepository);
    }

    @Bean
    public CartService cartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartItemRepository shoppingCartItemRepository, CampaignService campaignService,
                                   CouponService couponService, CartConverter cartConverter, DiscountCalculator discountCalculator, DeliveryCostCalculator deliveryCostCalculator) {
        return new CartService(shoppingCartRepository, shoppingCartItemRepository, campaignService, couponService, cartConverter, discountCalculator, deliveryCostCalculator);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }

    @Bean
    public CouponService couponService(CouponRepository couponRepository, ShoppingCartDiscountRepository shoppingCartDiscountRepository) {
        return new CouponService(couponRepository, shoppingCartDiscountRepository);
    }

    @Bean
    public DeliveryCostCalculator deliveryCostCalculator(ShoppingCartProperties shoppingCartProperties) {
        return new DeliveryCostCalculator(shoppingCartProperties.getCostPerDelivery(), shoppingCartProperties.getCostPerProduct(), shoppingCartProperties.getFixedCost());
    }

    @Bean
    public DiscountCalculator discountCalculator(ShoppingCartDiscountRepository shoppingCartDiscountRepository, CampaignService campaignService, CouponService couponService) {
        return new DiscountCalculator(shoppingCartDiscountRepository, campaignService, couponService);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}
