package com.vnpt.security.userpincal;

import com.vnpt.data_access.IPermissionRepository;
import com.vnpt.data_access.IUserRepository;
import com.vnpt.exception.DuplicateRecordException;
import com.vnpt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new DuplicateRecordException("tài khoản không tồn tại!");
        }
        ArrayList<String> permissions = permissionRepository.findPermissionKeyByUserId(user.getId());
        user.setPermissions(permissions);
        return UserPrinciple.build(user);
    }
}
