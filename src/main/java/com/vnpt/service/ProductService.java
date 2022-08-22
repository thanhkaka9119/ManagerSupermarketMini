package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IProductRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IBaseService<Product,Long> {

    private IProductRepository productRepository;

    public ProductService(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getList() {
        try{
            List<Product> productList = productRepository.findAll();
            return productList;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public Product getById(Long id) {
        try{
            Product product = productRepository.findById((long)id);
            return product;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public Product updateById(Long id, Product product) {
        try{
            Product oldProduct = productRepository.findById((long)id);

            oldProduct.setCode(product.getCode());
            oldProduct.setName(product.getName());
            oldProduct.setProductType(product.getProductType());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setImportPrice(product.getImportPrice());

            Product newProduct = productRepository.save(oldProduct);
            return newProduct;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public Product save(Product product) {
        try{
            Product newProduct =  productRepository.save(product);
            return newProduct;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            productRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<Product> getFollowPage(int page, int per_page) {
        try{
            Pageable paging = PageRequest.of(page, per_page);
            Page<Product> products = productRepository.findAll(paging);
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<Product> filterProducts(String query,int page, int per_page){
        try{
            Pageable paging = PageRequest.of(page, per_page);
            Page<Product> products = productRepository.findAllByNameContaining(query,paging);
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<Product> sortProducts(String sortby,String order,int page,int per_page){
        try{
            Pageable sortedByPriceAsc = PageRequest.of(page, per_page, Sort.by(sortby));
            Page<Product> products = productRepository.findAll(sortedByPriceAsc);
            if(order.contains("desc")){
                Pageable sortedByPriceDesc = PageRequest.of(page, per_page, Sort.by(sortby).descending());
                products = productRepository.findAll(sortedByPriceDesc);
            }
            DataPaginate<Product> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(products.getContent());
            dataPaginate.setPageNumber(products.getNumber());
            dataPaginate.setTotalPages(products.getTotalPages());
            return dataPaginate;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }
}
