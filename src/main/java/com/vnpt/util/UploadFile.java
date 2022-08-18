package com.vnpt.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

    private static final String UPLOAD_FOLDER = "//static//images//";

    public static String setFile(MultipartFile file){
//        if(file.isEmpty()){
//            return null;
//        }
//        try {
////            //đọc và viết file
////            byte[] bytes = file.getBytes();
//
//        String urlAvatar = UPLOAD_FOLDER + file.getOriginalFilename();
////            Path path = Paths.get(urlAvatar);
////            Files.write(path, bytes);
//            return urlAvatar;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

}
