package com.vnpt.data_access;

import com.vnpt.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeProductRespository extends JpaRepository<TypeProduct,Long> {
    TypeProduct findById(long id);
}
