package com.vnpt.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "type_user")
public class TypeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "typeUser", cascade = CascadeType.ALL)
    private Collection<User> users;

    public TypeUser(){}

    public TypeUser(long id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
