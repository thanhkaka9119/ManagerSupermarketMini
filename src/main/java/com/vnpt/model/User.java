package com.vnpt.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column(name = "code")
   private String code;
   @Column(name = "name")
   private String name;
   @Column(name = "birthday", columnDefinition = "DATETIME")
   @Temporal(TemporalType.TIMESTAMP)
   private Date birthday;
   @Column(name = "address")
   private String address;
   @Column(name = "email")
   private String email;
   @Column(name = "identifier")
   private String identifier;
   @Transient
   private MultipartFile fileImg;

   @Column(name = "url_avatar")
   private String urlAvatar;

   @ManyToOne
   @JoinColumn(name = "user_type")
   private TypeUser typeUser;

   @Transient
   private String stringType;

   public User(){}

   public User(String code, String name, Date birthday, String address,
               String email, String identifier, String urlAvatar, TypeUser typeUser) {
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

   public Date getBirthday() {
      return birthday;
   }

   public void setBirthday(Date birthday) {
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

   public MultipartFile getFileImg() {
      return fileImg;
   }

   public void setFileImg(MultipartFile fileImg) {
      this.fileImg = fileImg;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public TypeUser getTypeUser() {
      return typeUser;
   }
   public void setTypeUser(TypeUser typeUser) {
      this.typeUser = typeUser;
   }

   public String getStringType() {
      return stringType;
   }

   public void setStringType(String stringType) {
      this.stringType = stringType;
   }
}
