package com.vnpt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;
    private List<User> userList = new LinkedList<>();

    @PostConstruct
    public void init(){
        userList.add(new User("U01","mai thanh", "Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
        userList.add(new User("U02","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
        userList.add(new User("U03","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
        userList.add(new User("U04","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
        userList.add(new User("U05","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","quản lý"));
        userList.add(new User("U06","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
        userList.add(new User("U07","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","quản lý"));
        userList.add(new User("U08","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","quản lý"));
        userList.add(new User("U09","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","quản lý"));
        userList.add(new User("U10","mai thanh","Wed Aug 10 2022 00:00:00 GMT+0700 (Indochina Time)",
                "lai chau","thanh@gmail.com", "02yshdd2222","/.","nhân viên"));
    }

    @GetMapping("/user")
    public List<User> getUserList(){
        return userList;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name="id")int id){
        return ResponseEntity.ok(userList.get(id));
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST , consumes = { "multipart/form-data" })
    public ResponseEntity<User>  saveUser(@ModelAttribute User user){
        if(userService.uploadImg(user)){
            User newUser = new User(user.getCode(),user.getName(),user.getBirthday(),user.getAddress(),
                    user.getEmail(),user.getIdentifier(),user.getFileImg().getOriginalFilename(),user.getTypeUser());
            userList.add(newUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT , consumes = { "multipart/form-data" })
    public ResponseEntity<User> updateUserById(@PathVariable(name = "id")int id, @ModelAttribute User userNew){
        if(userService.uploadImg(userNew)){
            User newUser = new User(userNew.getCode(),userNew.getName(),userNew.getBirthday(),userNew.getAddress(),
                    userNew.getEmail(),userNew.getIdentifier(),userNew.getFileImg().getOriginalFilename(),userNew.getTypeUser());
            userList.set(id,newUser);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable(name = "id")int id){
        userList.remove(id);
        return ResponseEntity.ok().build();
    }
}
