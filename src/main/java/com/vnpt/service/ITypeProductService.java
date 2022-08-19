package com.vnpt.service;

import com.vnpt.model.TypeProduct;

import java.util.List;

public interface ITypeProductService {
    List<TypeProduct> getTypeProductList();

    TypeProduct getTypeProductById(long id);

    TypeProduct updateTypeProductById(long id, TypeProduct typeProduct);

    TypeProduct saveTypeProduct(TypeProduct typeProduct);

    void deleteTypeProductById(long id);
}
