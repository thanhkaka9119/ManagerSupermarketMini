package com.vnpt.dto.response;

import java.util.List;

public class RoleResponse {
    private long roleId;
    private String roleName;
    private List<String> permissionName;

    public RoleResponse(){}

    public RoleResponse(long roleId, String roleName, List<String> permissionName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissionName = permissionName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(List<String> permissionName) {
        this.permissionName = permissionName;
    }
}
