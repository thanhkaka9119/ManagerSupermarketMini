package com.vnpt.data_access;

import com.vnpt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "SELECT o.id,o.code AS orderCode, o.order_date, u.code AS userCode ,u.name, o.total_money " +
            "FROM `order` o left JOIN user u ON o.user_id = u.id LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> getOrderByNumberPage(@Param("start")int start,@Param("limit")int limit);

    @Query(value = "SELECT COUNT(id) FROM `order`",nativeQuery = true)
    int getTotalOrders();

    @Query(value = "SELECT o.id,o.code AS orderCode, o.order_date, u.code AS userCode ,u.name, o.total_money\n" +
            "FROM `order` o left JOIN user u ON o.user_id = u.id\n" +
            "where o.code like :keyword LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> findByCodeAndPagination(@Param("keyword") String keyword,@Param("start")int start,@Param("limit")int limit);

    @Query(value = "SELECT COUNT(id) FROM `order` where code like :keyword",nativeQuery = true)
    int getTotalOrderSearch(@Param("keyword") String keyword);

    @Query(value = "SELECT COUNT(id) FROM `order` WHERE order_id = ?1", nativeQuery = true)
    int existIdInOrderDetail(long id);

    @Modifying
    @Transactional
    @Query(value = "delete from `order` where id = :id",nativeQuery = true)
    void deleteOrderById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "delete from `order_detail` where order_id = :id",nativeQuery = true)
    void deleteOrderDetailByOrderId(@Param("id") long orderId);
}
