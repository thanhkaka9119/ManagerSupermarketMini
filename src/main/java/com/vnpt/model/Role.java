package com.vnpt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String roleName;

    @Transient
    private List<Long> permissionIdList;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission", //Tạo ra một join Table"
            joinColumns = @JoinColumn(name = "role_id"),  // TRong đó, khóa ngoại chính là role_id trỏ tới class hiện tại (role)
            inverseJoinColumns = @JoinColumn(name = "permission_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (permission)
    )
    private List<Permission> permissions;

//    @JsonBackReference

    public Role(){}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleName, List<Long> permissionIdList) {
        this.roleName = roleName;
        this.permissionIdList = permissionIdList;
    }

    public Role(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getPermissionIdList() {
        return permissionIdList;
    }

    public void setPermissionIdList(List<Long> permissionIdList) {
        this.permissionIdList = permissionIdList;
    }
}
