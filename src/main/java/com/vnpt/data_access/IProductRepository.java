package com.vnpt.data_access;

import com.vnpt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findById(long id);
}
