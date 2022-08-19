package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.ITypeUserRepository;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.TypeUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUserService implements IBaseService<TypeUser,Long> {

    private ITypeUserRepository typeUserRepository;

    public TypeUserService(ITypeUserRepository typeUserRepository){
        this.typeUserRepository = typeUserRepository;
    }

    @Override
    public List<TypeUser> getList() {
        try{
            return typeUserRepository.findAll();
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public TypeUser getById(Long id) {
        try{
            TypeUser typeUser = typeUserRepository.findById((long)id);
            return typeUser;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public TypeUser updateById(Long id, TypeUser typeUser) {
        try{
            TypeUser oldTypeUser = typeUserRepository.findById((long)id);
            oldTypeUser.setId(typeUser.getId());
            oldTypeUser.setName(typeUser.getName());
            TypeUser newTypeUser = typeUserRepository.save(oldTypeUser);
            return newTypeUser;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public TypeUser save(TypeUser typeUser) {
        try{
            TypeUser newTypeUser = typeUserRepository.save(typeUser);
            return newTypeUser;
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            typeUserRepository.deleteById(id);
        }catch (Exception ex){
            throw new NotFoundException("server error!");
        }
    }
}
