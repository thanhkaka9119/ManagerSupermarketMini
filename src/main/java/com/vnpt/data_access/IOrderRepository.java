package com.vnpt.data_access;

import com.vnpt.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Query(value = "SELECT p.id as productId,p.code as productCode ,p.name as productName,p.price,p.import_price,p.quantity,od.quantity as quantityPurchased FROM order_detail od " +
            "INNER JOIN product p ON od.product_id = p.id  WHERE order_id = :id\t",nativeQuery = true)
    List<Map<String,Object>> getOrderDetailByOrderId(@Param("id") long orderId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `order`(order_date,user_id,total_money,code) VALUES(:orderDate,:userId,:totalMoney,:code)",nativeQuery = true)
    public void saveOrder(@Param("orderDate") Date orderDate, @Param("userId") long userId, @Param("totalMoney") int totalMoney,
                          @Param("code") String code);

    @Query(value = "SELECT COUNT(o.id) FROM `order` o WHERE o.code LIKE ?1", nativeQuery = true)
    int existByCode(String code);

    @Query(value = "SELECT o.id FROM `order` o WHERE o.code LIKE ?1", nativeQuery = true)
    long getOrderIdByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_detail(order_id,product_id,import_price,quantity,price)" +
            "VALUES(:orderId,:productId,:importPrice,:quantity,:price)", nativeQuery = true)
    void saveOrderDetail(@Param("orderId") long orderId, @Param("productId") long productId, @Param("importPrice") int importPrice,
                         @Param("quantity") int quantity, @Param("price") int price);

    @Modifying
    @Transactional
    @Query(value = "update product p set p.quantity = :quantity where p.id = :id", nativeQuery = true)
    void updateProductQuantity(@Param("id") long productId, @Param("quantity")int quantity);

    @Query(value = "select * from `order` where id = ?1", nativeQuery = true)
    Order getByOrderId(long orderId);

    @Query(value = "select p.quantity from product p where p.id=?1",nativeQuery = true)
    int getQuantityByProductId(long productId);

    @Modifying
    @Transactional
    @Query(value = "update `order` set total_money = :totalMoney where id = :id",nativeQuery = true)
    void updateTotalMoney(@Param("id") long orderId, @Param("totalMoney")int totalMoney);
}
