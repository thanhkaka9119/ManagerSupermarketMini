package com.vnpt.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getProductList();

    Product getProductById(long id);

    Product updateProductById(long id, Product product);

    Product saveProduct(Product product);

    void deleteProductById(long id);
}
