package com.vnpt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "user_name", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

    @Transient
    private ArrayList<String> permissions;

    @Transient
    private List<Long> roleIdList;

    public User() {
    }

    public User(long id, String code, String name, Date birthday, String address,
                String email, String identifier, String urlAvatar) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.identifier = identifier;
        this.urlAvatar = urlAvatar;
    }

    public User(long id, String code, String name, Date birthday, String address,
                String email, String identifier) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.identifier = identifier;
    }

    public User(long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String code, String name, Date birthday, String address,
                String email, String identifier, String urlAvatar, String username, String password) {
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.identifier = identifier;
        this.urlAvatar = urlAvatar;
        this.username = username;
        this.password = password;
    }

    public User(String code, String name, Date birthday, String address, String email, String identifier, String urlAvatar, String username) {
        this.code = code;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.identifier = identifier;
        this.urlAvatar = urlAvatar;
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
