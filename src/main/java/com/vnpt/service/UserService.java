package com.vnpt.service;

import com.vnpt.common.DataPaginate;
import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IUserRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.User;
import com.vnpt.security.jwt.JwtProvider;
import com.vnpt.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService implements IBaseService<User, Long> {

    private final String httpUrl = "http://localhost:8080/uploads/images/";
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private IUserRepository userRepository;

    private UploadFile uploadFile;

    @Autowired
    private JwtProvider jwtUtil;

    public UserService(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    @Override
    public List<User> getList() {
        try {
            List<User> userList = userRepository.findAll();
            return userList;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User getById(Long id) {
        try {
            User user = userRepository.findById((long) id);
            return user;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public User updateById(Long id, User user) {
        try {
            User oldUser = userRepository.findById((long) id);
            String urlAvatar = uploadFile.setFile(user.getFileImg());

            oldUser.setCode(user.getCode());
            oldUser.setName(user.getName());
            oldUser.setBirthday(user.getBirthday());
            oldUser.setAddress(user.getAddress());
            oldUser.setEmail(user.getEmail());
            oldUser.setIdentifier(user.getIdentifier());
            oldUser.setUrlAvatar(httpUrl + urlAvatar);
            oldUser.setGender(user.getGender());
            oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(oldUser);
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Transactional
    @Override
    public User save(User user) {
        try {
            String urlAvatar = httpUrl + uploadFile.setFile(user.getFileImg());
            User newUser = new User(user.getCode(), user.getName(), user.getBirthday(), user.getAddress(),
                    user.getEmail(), user.getIdentifier(), urlAvatar,user.getGender(),
                    bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(newUser);
            return user;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<User> getFollowPage(int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<User> users = userRepository.findAll(paging);
            DataPaginate<User> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(users.getContent());
            dataPaginate.setPageNumber(users.getNumber());
            dataPaginate.setTotalPages(users.getTotalPages());
            return dataPaginate;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

    public DataPaginate<User> filterUsers(String query, int page, int per_page) {
        try {
            Pageable paging = PageRequest.of(page, per_page);
            Page<User> users = userRepository.findAllByNameContaining(query, paging);
            DataPaginate<User> dataPaginate = new DataPaginate<>();
            dataPaginate.setContent(users.getContent());
            dataPaginate.setPageNumber(users.getNumber());
            dataPaginate.setTotalPages(users.getTotalPages());
            return dataPaginate;
        } catch (Exception ex) {
            throw new NotFoundException("server error!");
        }
    }

//    public Token login(User user) {
////        User infoUser = userRepository.findByUserName(user.getUsername());
//        UserPrincipal userPrincipal = new UserPrincipal();
//
//        if (null == user || !bCryptPasswordEncoder.matches(user.getPassword(),
//                userPrincipal.getPassword()))
//        {
//            throw new DuplicateRecordException("tài khoản hoặc mật khẩu không đúng!");
//        }
//
////        if (null != infoUser) {
////            Set<String> authorities = new HashSet<>();
////
////            if (null != user.getRoles()) {
////                for(Role role:user.getRoles()){
////                    authorities.add(role.getRoleKey());
////                    for(Permission permission:role.getPermissions()){
////                        authorities.add(permission.getPermissionKey());
////                    }
////                }
////            }
////            userPrincipal.setUserId(infoUser.getId());
////            userPrincipal.setUsername(infoUser.getUsername());
////            userPrincipal.setPassword(infoUser.getPassword());
////            userPrincipal.setAuthorities(authorities);
////        }
//
//        Token token = new Token();
////        token.setToken(jwtUtil.generateToken(userPrincipal));
////        token.setTokenExpDate(jwtUtil.generateExpirationDate());
////        tokenService.save(token);
//
//        return token;
//    }

}
