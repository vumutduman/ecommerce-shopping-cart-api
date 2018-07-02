package com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountCategory;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(
        schema = "ecommerce",
        name = "shopping_cart_discount"
)
public class ShoppingCartDiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "discount_category")
    @Enumerated(value = EnumType.STRING)
    private DiscountCategory category;
}
