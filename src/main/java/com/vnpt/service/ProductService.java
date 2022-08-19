package com.vnpt.service;

import com.vnpt.data_access.IProductRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) throw new NotFoundException("server error!");
        return productList;
    }

    @Override
    public Product getProductById(long id) {
        Product product = productRepository.findById(id);
        if(product == null) throw new NotFoundException("Không tìm thấy!");
        return productRepository.findById(id);
    }

    @Override
    public Product updateProductById(long id,Product product) {
        Product oldProduct = productRepository.findById(id);
        if(oldProduct == null) throw new NotFoundException("không tìm thấy!");
        oldProduct.setCode(product.getCode());
        oldProduct.setName(product.getName());
        oldProduct.setProductType(product.getProductType());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImportPrice(product.getImportPrice());

        Product newProduct = productRepository.save(oldProduct);
        return newProduct;
    }

    @Override
    public Product saveProduct(Product product) {
        Product newProduct =  productRepository.save(product);
        if(newProduct == null) throw  new NotFoundException("server error!");
        return newProduct;
    }

    @Override
    public void deleteProductById(long id) {
        try{
            productRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public Page<Product> getFollowPage(int page, int per_page) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page,per_page));
        if(products.isEmpty()) throw new NotFoundException("server error!");
        return products;
    }

}
