package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.ITypeProductRespository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.TypeProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProductService implements IBaseService<TypeProduct, Long> {

    private ITypeProductRespository typeProductRespository;

    public TypeProductService(ITypeProductRespository typeProductRespository){
        this.typeProductRespository = typeProductRespository;
    }

    @Override
    public List<TypeProduct> getList() {
        try{
            return typeProductRespository.findAll();
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public TypeProduct getById(Long id) {
        try{
            TypeProduct typeProduct = typeProductRespository.findById((long) id);
            return typeProduct;
        }catch (Exception ex){
            throw new NotFoundException("không tìm thấy!");
        }
    }

    @Override
    public TypeProduct updateById(Long id, TypeProduct typeProduct) {
        try{
            TypeProduct oldTypeProduct = typeProductRespository.findById((long)id);
            oldTypeProduct.setId(typeProduct.getId());
            oldTypeProduct.setName(typeProduct.getName());
            TypeProduct newTypeProduct = typeProductRespository.save(oldTypeProduct);
            return newTypeProduct;
        }catch (Exception ex){
            throw new NotFoundException("không tìm thấy!");
        }
    }

    @Override
    public TypeProduct save(TypeProduct typeProduct) {
        try{
            TypeProduct newTypeProduct = typeProductRespository.save(typeProduct);
            return newTypeProduct;
        }catch (Exception ex){
            throw new NotFoundException("không tìm thấy!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            typeProductRespository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }
}
