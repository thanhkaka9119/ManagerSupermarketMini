package com.vnpt.user;

import org.springframework.web.multipart.MultipartFile;

public class User {
   private String code;
   private String name;
   private String birthday;
   private String address;
   private String email;
   private String identifier;

   private MultipartFile fileImg;

   private String urlAvatar;
   private String typeUser;

   public User(){}

   public User(String code, String name, String birthday, String address,
               String email, String identifier, MultipartFile fileImg, String typeUser) {
      this.code = code;
      this.name = name;
      this.birthday = birthday;
      this.address = address;
      this.email = email;
      this.identifier = identifier;
      this.fileImg= fileImg;
      this.typeUser = typeUser;
   }

   public User(String code, String name, String birthday,
               String address, String email, String identifier, String urlAvatar, String typeUser) {
      this.code = code;
      this.name = name;
      this.birthday = birthday;
      this.address = address;
      this.email = email;
      this.identifier = identifier;
      this.urlAvatar = urlAvatar;
      this.typeUser = typeUser;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getBirthday() {
      return birthday;
   }

   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getIdentifier() {
      return identifier;
   }

   public void setIdentifier(String identifier) {
      this.identifier = identifier;
   }

   public String getUrlAvatar() {
      return urlAvatar;
   }

   public void setUrlAvatar(String urlAvatar) {
      this.urlAvatar = urlAvatar;
   }

   public String getTypeUser() {
      return typeUser;
   }

   public void setTypeUser(String typeUser) {
      this.typeUser = typeUser;
   }

   public MultipartFile getFileImg() {
      return fileImg;
   }

   public void setFileImg(MultipartFile fileImg) {
      this.fileImg = fileImg;
   }

   @Override
   public String toString() {
      return "User{" +
              "code='" + code + '\'' +
              ", name='" + name + '\'' +
              ", birthday='" + birthday + '\'' +
              ", address='" + address + '\'' +
              ", email='" + email + '\'' +
              ", identifier='" + identifier + '\'' +
              ", fileImg=" + fileImg +
              ", urlAvatar='" + urlAvatar + '\'' +
              ", typeUser='" + typeUser + '\'' +
              '}';
   }
}
