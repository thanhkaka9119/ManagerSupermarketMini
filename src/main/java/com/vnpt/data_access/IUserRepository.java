package com.vnpt.data_access;

import com.vnpt.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new User(id, name, username, password) FROM User WHERE username like ?1")
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findById(long id);

    Page<User> findAllByNameContaining(String name, Pageable pageable);

}
