package com.vnpt.service;

import com.vnpt.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<User> getUserList();
    User getUserById(long id);
    User saveUser(User newUser);
    User updateUserById(long id,User user);
    void deleteUserById(long id);
    Page<User> getFollowPage(int page, int per_page);
}
