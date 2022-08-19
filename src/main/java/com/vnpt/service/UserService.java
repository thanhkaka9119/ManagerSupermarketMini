package com.vnpt.service;

import com.vnpt.common.IBaseService;
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
public class UserService implements IBaseService<User,Long> {

    private final String httpUrl = "http://localhost:8080/uploads/images/";
    private IUserRespository userRespository;
    private UploadFile uploadFile;
    @Autowired
    private IBaseService typeUserService;

    public UserService(UploadFile uploadFile, IUserRespository userRespository){
        this.userRespository = userRespository;
        this.uploadFile = uploadFile;
    }

    @Override
    public List<User> getList() {
        try{
            List<User> userList = userRespository.findAll();
            return userList;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User getById(Long id) {
        try{
            User user = userRespository.findById((long)id);
            return user;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User updateById(Long id, User user) {
        try{
            User oldUser = userRespository.findById((long)id);
            TypeUser typeUser = (TypeUser) typeUserService.getById(Long.parseLong(user.getStringType()));
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
    public User save(User user) {
        try{
            String urlAvatar = httpUrl + uploadFile.setFile(user.getFileImg());
            TypeUser typeUser = (TypeUser) typeUserService.getById(Long.parseLong(user.getStringType()));
            User newUser = new User(user.getCode(),user.getName(),user.getBirthday(),user.getAddress(),
                    user.getEmail(),user.getIdentifier(),urlAvatar,typeUser);
            userRespository.save(newUser);
            return user;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            userRespository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    public Page<User> getFollowPage(int page, int per_page) {
        try{
            Page<User> users = userRespository.findAll(PageRequest.of(page,per_page));
            if(users.isEmpty()) throw new NotFoundException("server error!");
            return users;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }
}
