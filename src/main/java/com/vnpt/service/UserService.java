package com.vnpt.service;

import com.vnpt.data_access.IUserRespository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.User;
import com.vnpt.util.UploadFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService implements IUserService {

    private IUserRespository userRespository;

    public UserService(IUserRespository userRespository){
        this.userRespository = userRespository;
    }
    private final String UPLOAD_FOLDER = "D://workspace//java_oop//ManagerSupermarketMini//src//main//java//com//vnpt//img//";

    @Override
    public List<User> getUserList() {
        List<User> userList = userRespository.findAll();
        if(userList.isEmpty()) throw new NotFoundException("server error!");
        return userList;
    }

    @Override
    public User getUserById(long id) {
        User user = userRespository.findById(id);
        if(user == null) throw new NotFoundException("không có dữ liệu!");
        return user;
    }

    @Override
    public User saveUser(User newUser) {
        try{
            String urlAvatar = UploadFile.setFile(newUser.getFileImg());
            if(urlAvatar == null) throw new NotFoundException("server error!");
                User user = new User(newUser.getCode(),newUser.getName(),newUser.getAddress(),
                        newUser.getEmail(),newUser.getIdentifier(),newUser.getUrlAvatar());
                userRespository.save(user);
                return user;
        }catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User updateUserById(long id, User user) {
        User oldUser = userRespository.findById(id);
        try{
            String urlAvatar = UploadFile.setFile(user.getFileImg());
            oldUser.setCode(user.getCode());
            oldUser.setName(user.getName());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setAddress(user.getAddress());
            oldUser.setEmail(user.getEmail());
            oldUser.setIdentifier(user.getIdentifier());
            oldUser.setUrlAvatar(urlAvatar);
            oldUser.setTypeUser(user.getTypeUser());
            return userRespository.save(oldUser);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public void deleteUserById(long id) {
        try{
            userRespository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public Page<User> getFollowPage(int page, int per_page) {
        Page<User> users = userRespository.findAll(PageRequest.of(page,per_page));
        if(users.isEmpty()) throw new NotFoundException("server error!");
        return users;
    }

}
