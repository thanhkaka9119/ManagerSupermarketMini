package com.vnpt.service;

import com.vnpt.data_access.ITypeUserRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.TypeUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUserService implements ITypeUserService {

    private ITypeUserRepository typeUserRepository;

    public TypeUserService(ITypeUserRepository typeUserRepository){
        this.typeUserRepository = typeUserRepository;
    }

    @Override
    public List<TypeUser> getTypeUserList() {
        return typeUserRepository.findAll();
    }

    @Override
    public TypeUser getTypeUserById(long id) {
        TypeUser typeUser = typeUserRepository.findById(id);
        return typeUser;
    }

    @Override
    public TypeUser updateTypeUserById(long id, TypeUser typeUser) {
        TypeUser oldTypeUser = typeUserRepository.findById(id);
        if(oldTypeUser == null) throw new NotFoundException("không tìm thấy!");
        oldTypeUser.setId(typeUser.getId());
        oldTypeUser.setName(typeUser.getName());
        TypeUser newTypeUser = typeUserRepository.save(oldTypeUser);
        return newTypeUser;
    }

    @Override
    public TypeUser saveTypeUser(TypeUser typeUser) {
        TypeUser newTypeUser = typeUserRepository.save(typeUser);
        if(newTypeUser == null) throw new NotFoundException("server error!");
        return newTypeUser;
    }

    @Override
    public void deleteTypeUserById(long id) {
        try{
            typeUserRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }


}
