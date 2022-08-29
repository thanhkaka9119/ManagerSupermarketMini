package com.vnpt.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String permissionKey;
    private String permissionName;

    @OneToMany(mappedBy = "permission", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserRolePermission> userRolePermissions;

    public Permission(){}

    public Permission(String permissionKey, String permissionName) {
        this.permissionKey = permissionKey;
        this.permissionName = permissionName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public List<UserRolePermission> getUserRolePermissions() {
        return userRolePermissions;
    }

    public void setUserRolePermissions(List<UserRolePermission> userRolePermissions) {
        this.userRolePermissions = userRolePermissions;
    }
}
