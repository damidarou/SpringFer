package com.nigmacode.apirest.repository;

import com.Landra.apirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
