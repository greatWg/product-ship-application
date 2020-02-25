package com.ecommerce.ShipApplication;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "product" , path= "product")
public interface ProductRepository extends PagingAndSortingRepository <Product, Long>  {

    List<Product> findByShipOnWeekendsIsFalse();

    Product findByProductId(@Param("productId") long productId);

}
