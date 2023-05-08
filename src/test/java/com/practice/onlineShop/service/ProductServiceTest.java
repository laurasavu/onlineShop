package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.enums.Currency;
import com.practice.onlineShop.exceptions.InvalidCodeException;
import com.practice.onlineShop.exceptions.productNotFoundException;
import com.practice.onlineShop.mappers.ProductMapper;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.vos.ProductVO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)

public class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;


    @TestConfiguration
    static class ProductServiceTestConfiguration {

        @MockBean
        private ProductMapper productMapper;
        @MockBean
        private ProductRepository productRepository;

        @Bean
        public ProductService productService() {
            return new ProductService(productRepository, productMapper);
        }

    }

    @Test
    public void getAllProducts() {
        ArrayList<Product> products=new ArrayList<>();

        Product product=new Product();
        product.setCode("code");
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        ProductVO productvo=new ProductVO();
        productvo.setCode("codes");
        when(productMapper.toVO(product)).thenReturn(productvo);
        List<ProductVO> productsList= (ArrayList<ProductVO>) productService.getAllProducts();
        assertThat(productsList).hasSize(1);
        verify(productRepository).findAll();
        verify(productMapper).toVO(product);
    }

    @org.junit.Test
    public void addProduct() {
        Product product1 = new Product();
        product1.setDescription("shirt");
        product1.setStock(2);
        product1.setValid(true);
        product1.setCurrency(Currency.RON);
        product1.setCode("500");
        product1.setPrice(23.0D);

        ProductVO productvo = new ProductVO();
        productvo.setDescription("shirt");
        productvo.setStock(2);
        productvo.setValid(true);
        productvo.setCurrency(Currency.RON);
        productvo.setCode("500");
        productvo.setPrice(23.0D);

        Mockito.when(productMapper.toEntity(Mockito.any())).thenReturn(product1);

        productService.addProduct(productvo, 2L);

        verify(productMapper).toEntity(productvo);
        verify(productRepository).save(product1);
    }

    @org.junit.Test
    public void getProduct_whenNotinDB_shouldREturnException() {
        try {
            productService.getProduct("yugyui");
        } catch (productNotFoundException e) {
            assert true;
            return;
        }
        assert false;
    }

    //metoda2
    @org.junit.Test
    public void getProduct_whenNotinDB_shouldREturnException2() {
        //given
        Product product1 = new Product();
        product1.setDescription("shirt");
        product1.setStock(2);
        product1.setValid(true);
        product1.setCurrency(Currency.RON);
        product1.setCode("500");
        product1.setPrice(23.0D);
        //when
        //then
        assertThatThrownBy(() -> productService.getProduct("jhg"))
                .isInstanceOf(productNotFoundException.class);
    }


    @org.junit.Test
    public void getProduct_wheninDB_shouldREturnproduct() throws productNotFoundException {
        Product product1 = new Product();
        product1.setDescription("shirt");
        product1.setStock(2);
        product1.setValid(true);
        product1.setCurrency(Currency.RON);
        product1.setCode("500");
        product1.setPrice(23.0D);

        ProductVO productvo = new ProductVO();
        productvo.setDescription("shirt");
        productvo.setStock(2);
        productvo.setValid(true);
        productvo.setCurrency(Currency.RON);
        productvo.setCode("500");
        productvo.setPrice(23.0D);

        Mockito.when(productRepository.findByCode(Mockito.any())).thenReturn(Optional.of(product1));
        Mockito.when(productMapper.toVO(Mockito.any())).thenReturn(productvo);

        ProductVO returnedproductvo = productService.getProduct("500");
        Assertions.assertThat(returnedproductvo.getCode()).isEqualTo("500");

        verify(productRepository).findByCode("500");
        verify(productMapper).toVO(product1);
    }

    @org.junit.Test
    public void updateProductwhencodeISNULL_shouldREturnException() {
        ProductVO productvo = new ProductVO();
        assertThatThrownBy(() -> productService.updateProduct(productvo, 1L))
                .isInstanceOf(InvalidCodeException.class);
    }
    @Test
    public void updateProductwhencodeISINVALID_shouldREturnException() {
        ProductVO productvo = new ProductVO();
        productvo.setCode("new");
        assertThatThrownBy(() -> productService.updateProduct(productvo, 1L))
                .isInstanceOf(productNotFoundException.class);
    }
    @Test
    public void updateProductwhencodeISVALID_shouldUpdate() throws InvalidCodeException, productNotFoundException {
        Product product1 = new Product();
        product1.setDescription("shirt");
        product1.setStock(2);
        product1.setValid(true);
        product1.setCurrency(Currency.RON);
        product1.setCode("500");
        product1.setPrice(23.0D);

        ProductVO productvo = new ProductVO();
        productvo.setDescription("shirt");
        productvo.setStock(2);
        productvo.setValid(true);
        productvo.setCurrency(Currency.RON);
        productvo.setCode("500");
        productvo.setPrice(23.0D);
        when(productRepository.findByCode(Mockito.any())).
                thenReturn(Optional.of(product1));
        productService.updateProduct(productvo, 2L);
        verify(productRepository).findByCode(productvo.getCode());
        verify(productRepository).save(product1);

    }
     @org.junit.Test
     public void deleteProduct_whennotInDB_shouldThrowExc() {
         assertThatThrownBy(()->productService.deleteProduct("jhg", 2L))
                 .isInstanceOf(productNotFoundException.class);
     }
    @org.junit.Test
    public void deleteProduct_whenVOnullshouldThrowExc() {
        assertThatThrownBy(()->productService.deleteProduct(null, 2L))
                .isInstanceOf(InvalidCodeException.class);
    }
    @Test
    public void deleteProduct_whencodeisOK_shouldDeleteProduct() throws productNotFoundException, InvalidCodeException {
        Product product=new Product();
        product.setCode("code");
        when(productRepository.findByCode(Mockito.any())).thenReturn(Optional.of(product));
        productService.deleteProduct(product.getCode(),2L);
        verify(productRepository).findByCode(product.getCode());
        verify(productRepository).delete(product);
    }

}