package com.poc.assessment.ecommerce.shoppingcart.api.converter;

import com.google.common.collect.Sets;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartCategory;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CategoryEntity;

import java.util.Map;
import java.util.Set;

public class CartCategoryConverter {

    public Set<ShoppingCartCategory> convertToCartCategories(Map<CategoryEntity, Set<ShoppingCartItem>> categoryMap) {
        final Set<ShoppingCartCategory> categories = Sets.newHashSet();
        categoryMap.forEach((k, e) ->
                categories.add(ShoppingCartCategory.builder()
                        .title(k.getTitle())
                        .items(categoryMap.get(k))
                        .build())
        );
        return categories;
    }
}
