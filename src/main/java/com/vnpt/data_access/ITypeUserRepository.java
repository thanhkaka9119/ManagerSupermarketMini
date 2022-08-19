package com.vnpt.data_access;

import com.vnpt.model.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeUserRepository extends JpaRepository<TypeUser,Long> {
    TypeUser findById(long id);
}
