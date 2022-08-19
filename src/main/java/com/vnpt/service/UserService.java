package com.vnpt.service;

import com.vnpt.data_access.IUserRespository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.TypeUser;
import com.vnpt.model.User;
import com.vnpt.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final String httpUrl = "http://localhost:8080/uploads/images/";

    private IUserRespository userRespository;
    @Autowired
    private ITypeUserService typeUserService;
    private UploadFile uploadFile;

    public UserService(UploadFile uploadFile, IUserRespository userRespository){
        this.userRespository = userRespository;
        this.uploadFile = uploadFile;
    }

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

            String urlAvatar = httpUrl + uploadFile.setFile(newUser.getFileImg());
            if(urlAvatar == null) throw new NotFoundException("server error!");
            TypeUser typeUser = typeUserService.getTypeUserById(Long.parseLong(newUser.getStringType()));
            User user = new User(newUser.getCode(),newUser.getName(),newUser.getBirthday(),newUser.getAddress(),
                        newUser.getEmail(),newUser.getIdentifier(),urlAvatar,typeUser);
            userRespository.save(user);
            return user;
        }catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User updateUserById(long id, User user) {
        try{
            User oldUser = userRespository.findById(id);
            TypeUser typeUser = typeUserService.getTypeUserById(Long.parseLong(user.getStringType()));
            String urlAvatar = uploadFile.setFile(user.getFileImg());
            oldUser.setCode(user.getCode());
            oldUser.setName(user.getName());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setAddress(user.getAddress());
            oldUser.setEmail(user.getEmail());
            oldUser.setIdentifier(user.getIdentifier());
            oldUser.setUrlAvatar(httpUrl + urlAvatar);
            oldUser.setTypeUser(typeUser);
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
