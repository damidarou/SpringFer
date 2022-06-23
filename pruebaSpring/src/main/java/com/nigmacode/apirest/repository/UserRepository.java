package com.nigmacode.apirest.repository;

import com.nigmacode.apirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByUsername(String username);
}
