package com.vnpt.Product;

import java.util.List;
import com.vnpt.data_access.IProductRepository;
import com.vnpt.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryRepositoryTest {
    @Autowired
    private IProductRepository productRepository;

    @Test
    public void getAll(){
        List<Product> productList = productRepository.getAll();
        Assertions.assertThat(productList).size().isGreaterThan(0);
    }

}
