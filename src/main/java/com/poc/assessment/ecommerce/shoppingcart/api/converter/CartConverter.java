package com.poc.assessment.ecommerce.shoppingcart.api.converter;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ServerSideException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.Cart;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartCategory;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.response.ShoppingCartItem;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.CategoryEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ProductEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ShoppingCartItemEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.CategoryRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ProductRepository;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ShoppingCartItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CartConverter {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final CartCategoryConverter cartCategoryConverter;
    private final CartItemConverter cartItemConverter;

    public Cart groupedCart(ShoppingCartEntity shoppingCartEntity) {
        final Optional<Set<ShoppingCartItemEntity>> optionalItemEntities = shoppingCartItemRepository.findAllByCartId(shoppingCartEntity.getId());
        if (!optionalItemEntities.isPresent()) {
            return Cart.builder().build();
        }

        final Map<CategoryEntity, Set<ShoppingCartItem>> categoryMap = Maps.newHashMap();
        for (ShoppingCartItemEntity shoppingCartItemEntity : optionalItemEntities.get()) {
            addProduct(shoppingCartItemEntity, categoryMap);
        }

        final Set<ShoppingCartCategory> categories = cartCategoryConverter.convertToCartCategories(categoryMap);
        return Cart.builder()
                .categories(categories)
                .build();
    }

    private void addProduct(ShoppingCartItemEntity shoppingCartItemEntity, Map<CategoryEntity, Set<ShoppingCartItem>> categoryMap) {
        final Long productId = shoppingCartItemEntity.getProductId();
        final ProductEntity productEntity = getProduct(productId);
        final CategoryEntity categoryEntity = getCategory(productEntity.getCategoryId());
        final ShoppingCartItem shoppingCartItem = cartItemConverter.convertToCartItem(productEntity, shoppingCartItemEntity);

        if (isNewCategory(categoryEntity, categoryMap)) {
            final Set<ShoppingCartItem> items = Sets.newHashSet(shoppingCartItem);
            categoryMap.put(categoryEntity, items);
        } else {
            categoryMap.get(categoryEntity).add(shoppingCartItem);
        }
    }

    private ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ServerSideException("Product can not found."));
    }

    private CategoryEntity getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServerSideException("Category can not found."));
    }

    private boolean isNewCategory(CategoryEntity categoryEntity, Map<CategoryEntity, Set<ShoppingCartItem>> categoryMap) {
        return (categoryMap.get(categoryEntity) == null);
    }
}
