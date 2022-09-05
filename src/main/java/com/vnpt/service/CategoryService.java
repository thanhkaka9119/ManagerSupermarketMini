package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.ICategoryRepository;
import com.vnpt.exception.DuplicateRecordException;
import com.vnpt.exception.NotFoundException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Category;
import com.vnpt.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IBaseService<Category,Long> {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getList() {
        try {
            return categoryRepository.getAll();
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Category getById(Long id) {
        try {
            Category category = categoryRepository.getById((long)id);
            return category;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Category updateById(Long id, Category category) {
        if(id == null) throw new NotFoundException("id không tồn tại!");
        try {
            categoryRepository.setCategoryById((long) id,category.getName());
            return new Category(id,category.getName(),category.getCode());
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Category save(Category category) {
        int existCode = categoryRepository.existByCode(category.getCode());
        if(existCode > 0) throw new DuplicateRecordException("mã loại sản phẩm đã tồn tại");
        try {
            categoryRepository.insertCategory(category.getName(),category.getCode());
            long newCategoryId = categoryRepository.getIdByCode(category.getCode());
            return new Category(newCategoryId,category.getName(),category.getCode());
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public void deleteById(Long id) {
        int productAmount = categoryRepository.existIdInProducts(id);
        if(productAmount > 0) throw new DuplicateRecordException("danh mục có chứa sản phẩm, không thể xoá!");
        try {
            categoryRepository.deleteCategoryById(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public DataPaginate<Category> getFollowPage(int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<Category> categories = categoryRepository.findAll(paging);
            DataPaginate<Category> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(categories.getContent());
            dataPaginate.setPageNumber(categories.getNumber());
            dataPaginate.setTotalCount(categories.getTotalElements());
            return dataPaginate;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public DataPaginate<Category> filterCategory(String searchString, int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<Category> categories = categoryRepository.findAllByCodeContaining(searchString, paging);
            DataPaginate<Category> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(categories.getContent());
            dataPaginate.setPageNumber(categories.getNumber());
            dataPaginate.setTotalCount(categories.getTotalElements());
            return dataPaginate;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

}
