package com.ecommerce.ShipApplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest

class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository repository;

    @Test
    void findByShipOnWeekendsIsFalse() {
        Product p =  new Product("test", 2,false,4);
        entityManager.persist(p);
        List<Product> productList = repository.findByShipOnWeekendsIsFalse();
        assertEquals(6, productList.size());
        assertThat(productList).extracting(Product::isShipOnWeekends).contains(Boolean.FALSE);

    }

    @Test
    void findByProductId() {
        Product p =  new Product("test", 2,false,4);
        entityManager.persist(p);
        Product product = repository.findByProductId(p.getProductId().longValue());

        assertEquals(product.productName, p.productName);
        assertEquals(product.getProductId(), p.getProductId());
    }


}