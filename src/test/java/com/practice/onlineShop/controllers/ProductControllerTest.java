package com.practice.onlineShop.controllers;


import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.practice.onlineShop.entities.Adress;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.enums.Currency;
import com.practice.onlineShop.enums.Roles;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.service.ProductService;

import com.practice.onlineShop.vos.ProductVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {


    private static final String LOCAHOST="http://localhost";
    @LocalServerPort
    private int port;
    @Autowired
    private ProductController productController;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
        public void addProductwhenhaspermission_shouldStoreTheProduct() {
            productRepository.deleteAll();
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
    @Test
    public void addProductwhendoesNOThavepermission_shouldReturnBadRequest() {
        productRepository.deleteAll();
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.CLIENT));
        userRepository.save(userEntity);

        ProductVO productVO = new ProductVO();
        productVO.setCode("ygiy");
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prd");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);

        ResponseEntity<String>responseEntity=testRestTemplate.postForEntity("http://localhost:" + port + "/product/" + userEntity.getId(),
                productVO, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void addProductwhenUserNotInDB_shouldReturnBadRequest() {
        productRepository.deleteAll();
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.CLIENT));
        userRepository.save(userEntity);

        ProductVO productVO = new ProductVO();
        productVO.setCode("ygiy");
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prd");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);

        ResponseEntity<String>responseEntity=testRestTemplate.postForEntity("http://localhost:" + port + "/product/" + userEntity.getId(),
                productVO, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    void getProductByCode_whenCodeinDb_ShouldReturnProduct() {
        Product product = new Product();
        product.setCode("ygiy");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        ProductVO productVO=testRestTemplate.getForObject("http://localhost:" + port + "/product/" +product.getCode(),ProductVO.class);
        assertThat(productVO.getCode()).isEqualTo(product.getCode());
    }
    @Test
    void getProductByCode_whenCodeNotDb_ShouldReturnError() {

       String response=testRestTemplate.getForObject("http://localhost:" + port + "/product/123",String.class);
        assertThat(response).isEqualTo("Product not Found");
    }
    @Test
    void getProducts() {
       productRepository.deleteAll();
        Product product = new Product();
        product.setCode("ygily");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);

        ResponseEntity<List<ProductVO>> listResponseEntity = testRestTemplate.
                exchange("http://localhost:" +
                        port + "/product", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductVO>>() {
                });
        assertThat(listResponseEntity.getBody()).hasSize(1);
    }

    @Test
    void updateProduct_whenUserisEditor_shouldUpdateProd() {
        Product product = new Product();
        product.setCode("ygi55ly");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        ProductVO productVO = new ProductVO();
        productVO.setCode(product.getCode());
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prd");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.EDITOR));
        testRestTemplate.put("http://localhost:" +
                port + "/product/"+userEntity.getId(),productVO);
        Optional<Product> updatedProduct=productRepository.findByCode(product.getCode());
        assertThat(updatedProduct.isPresent()).isEqualTo(true);
        assertThat(updatedProduct.get().getCode()).isEqualTo(productVO.getCode());
    }
    @Test
    void updateProduct_whenUserisAdmin_shouldUpdateProd() {
        Product product = new Product();
        product.setCode("i5p6ly");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        ProductVO productVO = new ProductVO();
        productVO.setCode(product.getCode());
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prd");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.ADMIN));
        testRestTemplate.put("http://localhost:" +
                port + "/product/"+userEntity.getId(),productVO);
        Optional<Product> updatedProduct=productRepository.findByCode(product.getCode());
        assertThat(updatedProduct.isPresent()).isEqualTo(true);
        assertThat(updatedProduct.get().getCode()).isEqualTo(productVO.getCode());
    }
    @Test
    void updateProduct_whenUserisClient_shouldNotUpdateProd() {
        Product product = new Product();
        product.setCode("ygi75gff5ly");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        ProductVO productVO = new ProductVO();
        productVO.setCode(product.getCode());
        productVO.setCurrency(Currency.EUR);
        productVO.setDescription("a new prdrgwsfrg");
        productVO.setStock(12);
        productVO.setPrice(12);
        productVO.setValid(true);
        User userEntity = new User("ioana", "1234",
                new Adress("bucuresti", "aleea toparceanu", 4),
                "@yahoo.com", Set.of(Roles.CLIENT));
        testRestTemplate.put("http://localhost:" +
                port + "/product/"+userEntity.getId(),productVO);
        Optional<Product> updatedProduct=productRepository.findByCode(product.getCode());
        assertThat(updatedProduct.isPresent()).isEqualTo(true);
        assertThat(updatedProduct.get().getCode()).isEqualTo(product.getCode());
        assertThat(updatedProduct.get().getDescription()).isEqualTo(product.getDescription());
    }

    @Test
    void deleteProduct_whenUserClient_shouldNotDelete() {
        Product product = new Product();
        product.setCode("ygi75gff5ly");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        testRestTemplate.delete("http://localhost:" +
                port + "/product/"+product.getCode()+"/2");

        assertThat(productRepository.findByCode(product.getCode())).isPresent();
    }
    @Test
    void deleteProduct_whenUserAdmin_shouldDelete() {
        productRepository.deleteAll();
        Product product = new Product();
        product.setCode("ygi75gwff5ly");
        product.setCurrency(Currency.EUR);
        product.setDescription("a new prd");
        product.setStock(12);
        product.setPrice(12);
        product.setValid(true);
        productRepository.save(product);
        testRestTemplate.delete("http://localhost:" +
                port + "/product/"+product.getCode()+"/1");

        assertThat(productRepository.findByCode(product.getCode())).isNotPresent();
    }
}


















