package com.vnpt.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findById(long id);
}
