package com.vnpt.data_access;

import com.vnpt.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new Category(id, name) FROM Category")
    List<Category> findAll();

    @Query(value = "SELECT new Category(c.id,c.name) FROM Category c where c.id = :#{#id}")
    Category findById(@Param("id")long id);

}
