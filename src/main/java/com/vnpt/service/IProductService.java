package com.vnpt.service;

import com.vnpt.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getProductList();

    Product getProductById(long id);

    Product updateProductById(long id, Product product);

    Product saveProduct(Product product);

    void deleteProductById(long id);

    Page<Product> getFollowPage(int page, int per_page);

}
