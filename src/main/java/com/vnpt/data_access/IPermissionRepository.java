package com.vnpt.data_access;

import com.vnpt.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface IPermissionRepository extends JpaRepository<Permission,Long> {
    @Query(value = "SELECT permission_key FROM permission p\n" +
            "WHERE p.id IN (SELECT permission_id FROM user_role_permission urp " +
            "WHERE urp.user_id = ?1)",nativeQuery = true)
    ArrayList<String> findPermissionKeyByUserId(long id);
}
