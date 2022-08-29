package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.ICategoryRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IBaseService<Category,Long> {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getList() {
        try {
            return categoryRepository.findAll();
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public Category getById(Long id) {
        try {
            Category category = categoryRepository.findById((long)id);
            if(category == null) throw new NotFoundException("id không tồn tại!");
            return category;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public Category updateById(Long id, Category category) {
        try {
            Category oldCategoryProduct = categoryRepository.findById((long) id);
            if(oldCategoryProduct == null) throw new NotFoundException("id không tồn tại!");
            oldCategoryProduct.setName(category.getName());
            Category newCategoryProduct = categoryRepository.save(oldCategoryProduct);
            return newCategoryProduct;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public Category save(Category category) {
        try {
            Category newCategoryProduct = categoryRepository.save(category);
            return newCategoryProduct;
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ServerErrorException("server lỗi!");
        }
    }

}
