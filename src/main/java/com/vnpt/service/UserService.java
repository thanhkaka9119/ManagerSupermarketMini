package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IRoleRepository;
import com.vnpt.data_access.IUserRepository;
import com.vnpt.dto.response.PaginationData;
import com.vnpt.exception.DuplicateRecordException;
import com.vnpt.exception.NotFoundException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Role;
import com.vnpt.model.User;
import com.vnpt.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Service
public class UserService implements IBaseService<User, Long> {

    private final String HTTPURL = "http://localhost:8080/uploads/images/";

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private UploadFile uploadFile;

    public UserService(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    @Override
    public List<User> getList() {
        try {
            List<User> userList = userRepository.findAll();
            return userList;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public User getById(Long id) {
        try {
//            User user = userRepository.findByUserId(id);
//            return user;
            return null;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public Map<String,Object> findById(Long id) {
        try {
            Map<String,Object> user = userRepository.findByUserId(id);
            return user;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public User updateById(Long id, User user) {
        try {
//            String urlAvatar = uploadFile.setFile(user.getFileImg());
            userRepository.setUserByUserId(user.getAddress(),user.getBirthday(),
                    user.getEmail(),user.getIdentifier(),user.getName(),passwordEncoder.encode(user.getPassword()),id);
            userRepository.deleteUserRoleByUserId(id);
            if(user.getRoleIdList().size() > 0){
                for(long roleId:user.getRoleIdList()){
                    userRepository.saveUserIdRoleId(id,roleId);
                }
            }
            return userRepository.findById((long)id);
        } catch (Exception ex) {
            System.out.println(ex);
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    @Override
    public User save(User user) {
        int existCode = userRepository.existByCode(user.getCode());
        if(existCode > 0) throw new DuplicateRecordException("mã nhân viên đã tồn tại");
        int existUsername = userRepository.existByUsername(user.getUsername());
        if(existUsername > 0) throw new DuplicateRecordException("Tên đăng nhập đã tồn tại");
        try {
            String urlAvatar = HTTPURL + uploadFile.setFile(user.getFileImg());
            User newUser = new User(user.getCode(), user.getName(), user.getBirthday(), user.getAddress(),
                    user.getEmail(), user.getIdentifier(), urlAvatar,user.getUsername(),passwordEncoder.encode(user.getPassword()));
            userRepository.save(newUser);
            if(user.getRoleIdList().size() > 0){
                for(long roleId:user.getRoleIdList()){
                    userRepository.saveUserIdRoleId(newUser.getId(),roleId);
                }
            }
            return newUser;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        int existUserId = userRepository.existIdInOrder(id);
        if(existUserId > 0) throw new DuplicateRecordException("Tài khoản có hoá đơn liên quan, không thể xoá!");
        try {
            userRepository.deleteUserRoleByUserId(id);
            userRepository.deleteUserByUserId(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public PaginationData getFollowPage(int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            int totalRecord = userRepository.getTotalUser();
            int start = (page-1)*per_page;
            List<Map<String,Object>> users = userRepository.getUserByNumberPage(start,per_page);
            responseData.setContent(users);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public PaginationData filterUsers(String searchString, int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            String keyword ='%' + searchString + '%';
            int totalRecord = userRepository.getTotalUserSearch(keyword);
            int start = (page-1)*per_page;
            List<Map<String,Object>> products = userRepository.findByCodeAndPagination(keyword,start,per_page);
            responseData.setContent(products);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

}
