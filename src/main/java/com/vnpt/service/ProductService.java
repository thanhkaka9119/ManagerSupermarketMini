package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.ICategoryRepository;
import com.vnpt.data_access.IProductRepository;
import com.vnpt.dto.response.PaginationData;
import com.vnpt.exception.DuplicateRecordException;
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

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IBaseService<Product,Long> {

    @Autowired
    private IProductRepository productRepository;

    public List<Map<String,Object>> getAll() {
        try {
            return productRepository.getAll();
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public Map<String,Object> getByProductId(Long id) {
        if(id == null) throw new NotFoundException("id không tồn tại!");
        try {
            return productRepository.getById((long)id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public List<Product> getList() {
        return null;
    }

    @Override
    public Product getById(Long id) {
            return null;
    }

    @Override
    public Product updateById(Long id, Product product) {
        if(id == null) throw new NotFoundException("id không tồn tại!");
//        int existCode = productRepository.existByCode(product.getCode());
//        if(existCode > 0) throw new DuplicateRecordException("mã sản phẩm đã tồn tại");
        try {
            productRepository.setProductById(id,product.getImportPrice(),
                    product.getName(),product.getPrice(),product.getCategoryId(),product.getQuantity());
            return new Product(id,product.getCode(),product.getName(),product.getPrice(),
                    product.getImportPrice(),product.getQuantity(),product.getCategoryId());
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Product save(Product product) {
        int existCode = productRepository.existByCode(product.getCode());
        if(existCode > 0) throw new DuplicateRecordException("mã sản phẩm đã tồn tại");
        try {
            productRepository.insertProduct(product.getCode(),product.getImportPrice(),product.getName(),
                    product.getPrice(),product.getCategoryId(),product.getQuantity());
            long newProductId = productRepository.getIdByCode(product.getCode());
            return new Product(newProductId,product.getCode(),product.getName(),product.getPrice(),
                    product.getImportPrice(),product.getQuantity(),product.getCategoryId());
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public void deleteById(Long id) {
        int orderAmount = productRepository.existIdInOrderDetail(id);
        if(orderAmount > 0) throw new DuplicateRecordException("Sản phẩm có hoá đơn liên quan, không thể xoá!");
        try {
            productRepository.deleteProductById(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public PaginationData getFollowPage(int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            int totalRecord = productRepository.getTotalProducts();
            int start = (page-1)*per_page;
            List<Map<String,Object>> products = productRepository.getProductByNumberPage(start,per_page);
            responseData.setContent(products);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public PaginationData filterProducts(String searchString, int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            String keyword ='%' + searchString + '%';
            int totalRecord = productRepository.getTotalProductSearch(keyword);
            int start = (page-1)*per_page;
            List<Map<String,Object>> products = productRepository.findByCodeAndPagination(keyword,start,per_page);
            responseData.setContent(products);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

//    public DataPaginate<Product> sortProducts(String sortby, String order, int page, int per_page) {
//        try {
//            Pageable sortedByPriceAsc = PageRequest.of(page, per_page, Sort.by(sortby));
//            Page<Product> products = productRepository.findAll(sortedByPriceAsc);
//            if (order.contains("desc")) {
//                Pageable sortedByPriceDesc = PageRequest.of(page, per_page, Sort.by(sortby).descending());
//                products = productRepository.findAll(sortedByPriceDesc);
//            }
//            DataPaginate<Product> dataPaginate = new DataPaginate<>();
//            dataPaginate.setContent(products.getContent());
//            dataPaginate.setPageNumber(products.getNumber());
//            dataPaginate.setTotalCount(products.getTotalElements());
//            return dataPaginate;
//        } catch (Exception ex) {
//            throw new ServerErrorException("lỗi rồi!");
//        }
//    }
}
