package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IProductRepository;
import com.vnpt.data_access.ICategoryRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Category;
import com.vnpt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IBaseService<Product,Long> {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Product> getList() {
        List<Product> productList = productRepository.getAll();
        return productList;
    }

    @Override
    public Product getById(Long id) {
        if(id == null) throw new NotFoundException("id không tồn tại!");
        Product product = productRepository.findById((long)id);
        if (product == null) throw new NotFoundException("id không tồn tại!");
        return product;
    }

    @Override
    public Product updateById(Long id, Product product) {
        try {
//            if(id == null || product.getCategoryId() == null) throw new NotFoundException("id không tồn tại!");
            Product oldProduct = productRepository.findById((long)id);
//            Category category = categoryRepository.findById(Long.parseLong(product.getCategoryId()));

            oldProduct.setCode(product.getCode());
            oldProduct.setName(product.getName());
//            oldProduct.setCategory(category);
            oldProduct.setPrice(product.getPrice());
            oldProduct.setImportPrice(product.getImportPrice());

            Product newProduct = productRepository.save(oldProduct);
            return newProduct;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public Product save(Product product) {
        try {
            Product newProduct = productRepository.save(product);
            return newProduct;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    public DataPaginate<Product> getFollowPage(int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<Product> products = productRepository.findAll(paging);
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    public DataPaginate<Product> filterProducts(String query, int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<Product> products = productRepository.findAllByNameContaining(query, paging);
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<Product> sortProducts(String sortby, String order, int page, int per_page) {
        try {
            Pageable sortedByPriceAsc = PageRequest.of(page, per_page, Sort.by(sortby));
            Page<Product> products = productRepository.findAll(sortedByPriceAsc);
            if (order.contains("desc")) {
                Pageable sortedByPriceDesc = PageRequest.of(page, per_page, Sort.by(sortby).descending());
                products = productRepository.findAll(sortedByPriceDesc);
            }
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }
}
