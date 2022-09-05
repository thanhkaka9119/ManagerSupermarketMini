package com.vnpt.data_access;

import com.vnpt.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long> {
    @Query(value = "SELECT p.permission_key FROM permission p \n" +
            "WHERE p.id IN (SELECT DISTINCT rp.permission_id FROM role_permission rp \n" +
            "WHERE rp.role_id IN (SELECT ur.role_id FROM user_role ur WHERE ur.user_id = ?1))",nativeQuery = true)
    ArrayList<String> findPermissionKeyByUserId(long id);

    @Query(value = "select * from permission",nativeQuery = true)
    List<Permission> getAll();
}
