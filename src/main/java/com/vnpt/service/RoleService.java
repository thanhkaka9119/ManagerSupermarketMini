package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IPermissionRepository;
import com.vnpt.data_access.IRoleRepository;
import com.vnpt.dto.response.RoleResponse;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Permission;
import com.vnpt.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IBaseService<Role,Long> {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public List<Role> getList() {
        try {
            return roleRepository.getAll();
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Role getById(Long id) {
        try{
            return roleRepository.findByRoleId(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public Role updateById(Long id, Role role) {
        try{
            roleRepository.setPermissionById(id,role.getRoleName());
            roleRepository.deleteRolePermissionByRoleId(id);
            for(long idPermission:role.getPermissionIdList()){
                roleRepository.saveRoleIdPermissionId(id,idPermission);
            }
            return new Role(id,role.getRoleName());
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    @Override
    public Role save(Role role) {
        try{
            Role roleData = new Role(role.getRoleName());
            Role roleNew = roleRepository.save(roleData);
            for(long idPermission:role.getPermissionIdList()){
                roleRepository.saveRoleIdPermissionId(roleNew.getId(),idPermission);
            }
            return roleNew;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        try {
            roleRepository.deleteRolePermissionByRoleId(id);
            roleRepository.deleteRoleByRoleId(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public List<RoleResponse> getRoleAttachPermissionName(){
        List<RoleResponse> roleResponses = new ArrayList<>();
        try {
            List<Role> roles = roleRepository.getAll();
            for(Role role:roles){
                List<String> permissionNameList = roleRepository.getPermissionKeyByRoleId(role.getId());
                RoleResponse roleResponse = new RoleResponse(role.getId(),role.getRoleName(),permissionNameList);
                roleResponses.add(roleResponse);
            }
            return roleResponses;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public List<Permission> getPermissionList(){
        try {
            return permissionRepository.getAll();
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

}
