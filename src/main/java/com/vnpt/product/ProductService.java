package com.vnpt.product;

import com.vnpt.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductList() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) throw new NotFoundException("server error!");
        return productRepository.findAll();
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
        oldProduct.setTypeProduct(product.getTypeProduct());
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
        productRepository.deleteById(id);
    }

}
