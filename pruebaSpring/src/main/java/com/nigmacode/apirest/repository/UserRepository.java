package com.nigmacode.apirest.repository;

import com.nigmacode.apirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
