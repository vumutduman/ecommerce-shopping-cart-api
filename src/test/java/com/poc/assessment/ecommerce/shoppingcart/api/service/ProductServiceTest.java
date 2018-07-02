package com.poc.assessment.ecommerce.shoppingcart.api.service;

import com.poc.assessment.ecommerce.shoppingcart.api.domain.exceptions.ServerSideException;
import com.poc.assessment.ecommerce.shoppingcart.api.domain.request.ProductCreateRequest;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.entity.ProductEntity;
import com.poc.assessment.ecommerce.shoppingcart.api.persistence.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testSuccessCreateProduct() {
        //given
        final Long categoryId = 1L;
        final ProductCreateRequest request = new ProductCreateRequest("title", new BigDecimal(10));

        when(mockProductRepository.save(any(ProductEntity.class))).thenReturn(any(ProductEntity.class));

        //when
        productService.createProduct(categoryId, request);

        //then
        verify(mockProductRepository, times(1)).save(any());
    }

    @Test(expected = ServerSideException.class)
    public void testFailedCreateProduct() {
        //given
        final Long categoryId = 1L;
        final ProductCreateRequest request = new ProductCreateRequest("title", new BigDecimal(10));

        doThrow(new ServerSideException("NetworkException")).when(mockProductRepository).save(any(ProductEntity.class));

        //when
        productService.createProduct(categoryId, request);

        //then
        fail("Code should not reach here.");
    }
}