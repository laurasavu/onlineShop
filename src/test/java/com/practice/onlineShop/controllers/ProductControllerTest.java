package com.practice.onlineShop.controllers;

import com.practice.onlineShop.entities.Adress;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Currency;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.vos.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {
    @Autowired
    private UserRepository userRepository;
    @LocalServerPort
    private int port;

    @Autowired
    private ProductController productController;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void contextLoads() {
        assertThat(productController).isNotNull();
    }

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
    assertThat(product.getCode()).isEqualTo(productVO.getCode());
    }

   //mai sunt teste
    //   TODO opinii personale daca baza de date se incarca conditia de mai sus nu mai e ok deci
    //testul asta nu e ok pt productie

}