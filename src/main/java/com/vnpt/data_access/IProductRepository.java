package com.vnpt.data_access;

import com.vnpt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p.id, p.code, p.import_price, p.name, p.price, p.category_id, p.quantity, c.code AS categoryCode ,c.name AS categoryName \n" +
            "FROM product p left JOIN category c ON p.category_id = c.id\n" +
            "WHERE p.id = ?1",nativeQuery = true)
    Map<String,Object> getById(long id);

    @Query(value = "SELECT p.id, p.code, p.import_price, p.name, p.price, " +
            "p.category_id, p.quantity, c.name AS categoryName FROM product p left JOIN category c ON p.category_id = c.id", nativeQuery = true)
    List<Map<String,Object>> getAll();

    @Query(value = "SELECT COUNT(p.id) FROM product p",nativeQuery = true)
   int getTotalProducts();

    @Query(value = "SELECT COUNT(p.id) FROM product p where p.code like :keyword",nativeQuery = true)
    int getTotalProductSearch(@Param("keyword") String keyword);

    @Query(value = "SELECT p.id, p.code, p.import_price, p.name, p.price, p.category_id, p.quantity, c.name AS categoryName " +
            "FROM product p left JOIN category c ON p.category_id = c.id LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> getProductByNumberPage(@Param("start")int start,@Param("limit")int limit);

    @Query(value = "SELECT p.id, p.code, p.import_price, p.name, p.price, p.category_id, p.quantity, c.name AS categoryName " +
            "FROM product p left JOIN category c ON p.category_id = c.id where p.code like :keyword LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> findByCodeAndPagination(@Param("keyword") String keyword,@Param("start")int start,@Param("limit")int limit);

    @Query(value = "SELECT COUNT(od.product_id) FROM order_detail od WHERE od.product_id = ?1", nativeQuery = true)
    int existIdInOrderDetail(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from product where id = :id",nativeQuery = true)
    void deleteProductById(@Param("id") long id);

    @Query(value = "SELECT COUNT(p.id) FROM product p WHERE p.code LIKE ?1", nativeQuery = true)
    int existByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product (CODE,import_price,NAME,price,category_id,quantity)" +
            "VALUES(:code,:importPrice,:name,:price,:categoryId,:quantity)", nativeQuery = true)
    void insertProduct(@Param("code") String code,@Param("importPrice") int importPrice,@Param("name") String name,
                       @Param("price")int price, @Param("categoryId")long categoryId, @Param("quantity")int quantity);

    @Query(value = "SELECT p.id FROM product p WHERE p.code LIKE ?1", nativeQuery = true)
    long getIdByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "update product p\n" +
            "SET p.import_price = :importPrice, p.name = :name, p.price = :price, p.category_id = :categoryId, p.quantity = :quantity\n" +
            "where p.id = :id", nativeQuery = true)
    void setProductById(@Param("id")long id,@Param("importPrice") int importPrice,@Param("name") String name,
                        @Param("price")int price, @Param("categoryId")long categoryId, @Param("quantity")int quantity);

}
