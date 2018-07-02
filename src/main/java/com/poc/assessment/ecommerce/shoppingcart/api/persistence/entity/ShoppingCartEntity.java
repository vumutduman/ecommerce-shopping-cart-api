package com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table(
        schema = "ecommerce",
        name = "shopping_cart"
)
public class ShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cart_owner_id")
    private Long cartOwnerId;

}
