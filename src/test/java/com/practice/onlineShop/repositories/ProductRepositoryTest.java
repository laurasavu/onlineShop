package com.practice.onlineShop.repositories;

import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.enums.Currency;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;

@RunWith(SpringRunner.class)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByCode_whenCodePresent_shouldReturnTheProduct() {
        Product product1=new Product();
        product1.setDescription("shirt");
        product1.setStock(2);
        product1.setValid(true);
        product1.setCurrency(Currency.RON);
        product1.setCode("500");
        product1.setPrice(23);
        testEntityManager.persist(product1);
        testEntityManager.flush();
        Optional<Product> productOptional=productRepository.findByCode(product1.getCode());
        assertThat(productOptional).isPresent();
        assertThat(productOptional.get().getCode()).isEqualTo(product1.getCode());
    }
}