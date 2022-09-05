package com.vnpt.data_access;

import com.vnpt.model.Category;
import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Query("select new Category (id,name,code) from Category")
    List<Category> getAll();

    @Query("select new Category (id,name,code) from Category where id = :#{#id}")
    Category getById(@Param("id") long id);

    @Query(value = "SELECT COUNT(c.id) FROM category c WHERE c.code LIKE ?1", nativeQuery = true)
    int existByCode(String code);

    @Query(value = "SELECT c.id FROM category c WHERE c.code LIKE ?1", nativeQuery = true)
    long getIdByCode(String code);

    @Query(value = "SELECT COUNT(p.id) FROM product p WHERE p.category_id = ?1", nativeQuery = true)
    int existIdInProducts(long id);

    @Modifying
    @Transactional
    @Query(value = "insert into category (name, code) values (:name, :code)", nativeQuery = true)
    void insertCategory(@Param("name") String name, @Param("code") String code);

    @Modifying
    @Transactional
    @Query(value = "update category c set c.name = :name where c.id = :id", nativeQuery = true)
    void setCategoryById(@Param("id") long id,@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "delete from category where id = :id", nativeQuery = true)
    void deleteCategoryById(@Param("id") long id);

    Page<Category> findAllByCodeContaining(String searchString, Pageable pageable);
}
