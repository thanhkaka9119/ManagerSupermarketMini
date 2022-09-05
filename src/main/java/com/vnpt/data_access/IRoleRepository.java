package com.vnpt.data_access;

import com.vnpt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "select * from role", nativeQuery = true)
    List<Role> getAll();

    @Query(value = "SELECT p.permission_key FROM permission p WHERE p.id IN (\n" +
            "SELECT DISTINCT rp.permission_id FROM role_permission rp WHERE rp.role_id = ?1)", nativeQuery = true)
    List<String> getPermissionKeyByRoleId(long id);

    @Query(value = "select * from role where id = ?1", nativeQuery = true)
    Role findByRoleId(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO role_permission VALUES (?1, ?2)", nativeQuery = true)
    void saveRoleIdPermissionId(long idRole, long idPermission);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM role WHERE id = ?1",nativeQuery = true)
    void deleteRoleByRoleId(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM role_permission WHERE role_id = ?1",nativeQuery = true)
    void deleteRolePermissionByRoleId(long id);

    @Modifying
    @Transactional
    @Query(value = "update role set role_name = :name where id = :id", nativeQuery = true)
    void setPermissionById(@Param("id") long id, @Param("name") String name);
}
