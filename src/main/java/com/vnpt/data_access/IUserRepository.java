package com.vnpt.data_access;

import com.vnpt.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.id,u.code,u.name,u.birthday,u.address,u.email,u.identifier,u.url_avatar,r.role_name " +
            "FROM user u LEFT JOIN user_role ur ON u.id = ur.user_id\n" +
            "LEFT JOIN role r ON ur.role_id = r.id",nativeQuery = true)
    List<Map<String,Object>> getAll();

    @Query(value = "SELECT COUNT(u.id) FROM user u LEFT JOIN user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN role r ON ur.role_id = r.id",nativeQuery = true)
    int getTotalUser();

    @Query(value = "SELECT u.id,u.code,u.name,u.birthday,u.address,u.email,u.identifier,u.url_avatar,r.role_name FROM user u LEFT JOIN user_role ur ON u.id = ur.user_id\n" +
            "LEFT JOIN role r ON ur.role_id = r.id LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> getUserByNumberPage(@Param("start")int start, @Param("limit")int limit);

    @Query(value = "SELECT COUNT(u.id) FROM user u where u.code LIKE :keyword",nativeQuery = true)
    int getTotalUserSearch(@Param("keyword") String keyword);

    @Query(value = "SELECT u.id,u.code,u.name,u.birthday,u.address,u.email,u.identifier,u.url_avatar,r.role_name FROM user u \n" +
            "LEFT JOIN user_role ur ON u.id = ur.user_id \n" +
            "LEFT JOIN role r ON ur.role_id = r.id where u.code like :keyword LIMIT :start, :limit", nativeQuery = true)
    List<Map<String,Object>> findByCodeAndPagination(@Param("keyword") String keyword,@Param("start")int start,@Param("limit")int limit);

    @Query(value = "INSERT INTO user_role VALUES (?1, ?2)", nativeQuery = true)
    void saveUserIdRoleId(long userId,long roleId);

    @Query(value = "SELECT COUNT(u.id) FROM user u WHERE u.code LIKE ?1", nativeQuery = true)
    int existByCode(String code);

    @Query(value = "SELECT COUNT(u.id) FROM user u WHERE u.user_name LIKE ?1", nativeQuery = true)
    int existByUsername(String username);

    @Query("SELECT new User(id, name, username, password) FROM User WHERE username like ?1")
    User findByUsername(String username);

    @Query(value = "DELETE FROM user_role WHERE user_id = ?1", nativeQuery = true)
    void deleteUserRoleByUserId(long userId);

    @Query(value = "DELETE FROM user WHERE id = ?1", nativeQuery = true)
    void deleteUserByUserId(long userId);

    @Query(value = "SELECT code,address,birthday,email,identifier,name,url_avatar,user_name FROM user WHERE id = ?1",nativeQuery = true)
    Map<String,Object> findByUserId(long userId);

    @Query("SELECT new User(code, name, birthday, address, email, identifier,urlAvatar,username) " +
            "FROM User WHERE id = ?1")
    User findById(long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user \n" +
            "SET address = :address, birthday = :birthday, email = :email,\n" +
            "identifier = :identifier, name = :name, password = :password\n" +
            "WHERE id = :id",nativeQuery = true)
    void setUserByUserId(@Param("address") String address,@Param("birthday") Date birthday,
                         @Param("email")String email, @Param("identifier")String identifier, @Param("name")String name,
                         @Param("password")String password, @Param("id")long userId);

    @Query(value = "SELECT COUNT(o.id) FROM `order` o WHERE user_id = ?1", nativeQuery = true)
    int existIdInOrder(long userId);
}
