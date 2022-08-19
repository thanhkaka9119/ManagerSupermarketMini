package com.vnpt.service;

import com.vnpt.data_access.ITypeProductRespository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.TypeProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductService implements ITypeProductService{

    private ITypeProductRespository typeProductRespository;

    public TypeProductService(ITypeProductRespository typeProductRespository){
        this.typeProductRespository = typeProductRespository;
    }

    @Override
    public List<TypeProduct> getTypeProductList() {
        return typeProductRespository.findAll();
    }

    @Override
    public TypeProduct getTypeProductById(long id) {
        TypeProduct typeProduct = typeProductRespository.findById(id);
        return typeProduct;
    }

    @Override
    public TypeProduct updateTypeProductById(long id, TypeProduct typeProduct) {
        TypeProduct oldTypeProduct = typeProductRespository.findById(id);
        if(oldTypeProduct == null) throw new NotFoundException("không tìm thấy!");
        oldTypeProduct.setId(typeProduct.getId());
        oldTypeProduct.setName(typeProduct.getName());
        TypeProduct newTypeProduct = typeProductRespository.save(oldTypeProduct);
        return newTypeProduct;
    }

    @Override
    public TypeProduct saveTypeProduct(TypeProduct typeProduct) {
        TypeProduct newTypeProduct = typeProductRespository.save(typeProduct);
        if(newTypeProduct == null) throw new NotFoundException("server error!");
        return newTypeProduct;
    }

    @Override
    public void deleteTypeProductById(long id) {
        try{
            typeProductRespository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }
}
