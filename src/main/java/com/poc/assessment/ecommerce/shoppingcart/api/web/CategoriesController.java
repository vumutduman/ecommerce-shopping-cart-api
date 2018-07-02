package com.poc.assessment.ecommerce.shoppingcart.api.web;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.CategoryCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ProductCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.service.CategoryService;
import com.poc.assessment.ecommerce.shoppingcart.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping(path = "/api/ecommerce/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        categoryService.createCategory(categoryCreateRequest);
    }

    @PostMapping(path = "/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategoryWithParent(@PathVariable Long categoryId, @RequestBody CategoryCreateRequest categoryCreateRequest) {
        categoryService.createCategory(categoryId, categoryCreateRequest);
    }

    @PostMapping(path = "/{categoryId}/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@PathVariable Long categoryId, @RequestBody ProductCreateRequest productCreateRequest) {
        productService.createProduct(categoryId, productCreateRequest);
    }
}
