package com.practice.onlineShop.controllers;


import com.practice.onlineShop.service.ProductService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;




    }













/* Aceasta testare sugerata de bitnett nu mi se pare ok voi incerca c mockito, wish me luck
    @Test
    public void addProductwhenhaspermission_shouldStoreTheProduct() {
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.ADMIN));
        userRepository.save(userEntity);

        ProductVO productVO = new ProductVO();
        productVO.setCode("ygiy");
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prd");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);

        testRestTemplate.postForEntity("http://localhost:" + port + "/product/" + userEntity.getId(),
                productVO, Void.class);
        Iterable<Product> products = productRepository.findAll();

      assertThat(products).hasSize(1);


    Product product=products.iterator().next();

    assertThat(product.getCode()).isEqualTo(productVO.getCode());*/




