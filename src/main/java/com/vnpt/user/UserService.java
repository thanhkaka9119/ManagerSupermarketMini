package com.vnpt.user;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UserService implements IUserService{
    private final String UPLOAD_FOLDER = "D://workspace//java_oop//ManagerSupermarketMini//src//main//java//com//vnpt//img//";

    @Override
    public boolean uploadImg(User user) {
        if(user.getFileImg().isEmpty()){
            return false;
        }
        try {
            // read and write the file to the selected location-
            byte[] bytes = user.getFileImg().getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + user.getFileImg().getOriginalFilename());
            Files.write(path, bytes);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
