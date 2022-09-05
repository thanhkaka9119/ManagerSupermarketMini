package com.vnpt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

//    @OneToMany(mappedBy = "permission", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JsonBackReference
//    private List<UserRolePermission> userRolePermissions;

    @ManyToMany(mappedBy = "permissions")
//    @JsonManagedReference
    private List<Role> roles;

    public Permission(){}

    public Permission(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

}
