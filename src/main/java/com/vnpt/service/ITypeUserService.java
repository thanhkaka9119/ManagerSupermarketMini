package com.vnpt.service;

import com.vnpt.model.Product;
import com.vnpt.model.TypeUser;

import java.util.List;

public interface ITypeUserService {
    List<TypeUser> getTypeUserList();
    TypeUser getTypeUserById(long id);

    TypeUser updateTypeUserById(long id, TypeUser typeUser);

    TypeUser saveTypeUser(TypeUser typeUser);

    void deleteTypeUserById(long id);
}
