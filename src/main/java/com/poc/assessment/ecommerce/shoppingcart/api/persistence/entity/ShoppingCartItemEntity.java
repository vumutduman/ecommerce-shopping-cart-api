package com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder
@Entity
@Table(
        schema = "ecommerce",
        name = "shopping_cart_item"
)
public class ShoppingCartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_unit_price", nullable = false)
    private BigDecimal productUnitPrice;

}
