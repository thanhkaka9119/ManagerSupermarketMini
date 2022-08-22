package com.vnpt.data_access;

import com.vnpt.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRespository extends JpaRepository<User,Long> {
    User findById(long id);
    Page<User> findAllByNameContaining(String name, Pageable pageable);
}
