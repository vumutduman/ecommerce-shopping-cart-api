package com.poc.assessment.ecommerce.shoppingcart.api.configuration;

import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartCategoryConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartItemConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CategoryRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ProductRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartItemRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartCategoryConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.converter.CartItemConverter;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CategoryRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ProductRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public CartConverter cartConverter(CategoryRepository categoryRepository, ProductRepository productRepository,
                                       ShoppingCartItemRepository shoppingCartItemRepository, CartCategoryConverter cartCategoryConverter,
                                       CartItemConverter cartItemConverter) {
        return new CartConverter(categoryRepository, productRepository, shoppingCartItemRepository, cartCategoryConverter, cartItemConverter);
    }

    @Bean
    public CartCategoryConverter cartCategoryConverter() {
        return new CartCategoryConverter();
    }

    @Bean
    public CartItemConverter cartItemConverter() {
        return new CartItemConverter();
    }
}
