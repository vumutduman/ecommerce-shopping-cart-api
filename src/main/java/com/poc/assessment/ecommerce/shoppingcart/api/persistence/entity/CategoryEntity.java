package com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Builder
@EqualsAndHashCode
@Entity
@Table(
        schema = "ecommerce",
        name = "category"
)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @Column(name = "title", nullable = false)
    private String title;

}
