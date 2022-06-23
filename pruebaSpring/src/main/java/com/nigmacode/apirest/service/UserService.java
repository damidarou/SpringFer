package com.nigmacode.apirest.service;

import com.nigmacode.apirest.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAll();

    public Optional<User> findById(int id);

    public void save(User user);

    public void deleteById(int id);

    public List<User> findByUsername(String username);

    public List<User> buscar(User user);
}