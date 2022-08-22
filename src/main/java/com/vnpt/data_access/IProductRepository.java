package com.vnpt.data_access;

import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findById(long id);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
