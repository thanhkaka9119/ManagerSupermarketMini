package com.vnpt.data_access;

import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT new Product(id,code,name,price,importPrice) FROM Product")
    List<Product> getAll();

    @Query(value = "SELECT new Product(id,code,name,price,importPrice) FROM Product where id = :#{#id}")
    Product findById(@Param("id")long id);

    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
