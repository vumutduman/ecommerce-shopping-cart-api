package com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountType;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.DiscountType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Builder
@Entity
@Table(
        schema = "ecommerce",
        name = "campaign"
)
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "discount_threshold", nullable = false)
    private Integer threshold;

    @Column(name = "discount_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DiscountType discountType;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

}
