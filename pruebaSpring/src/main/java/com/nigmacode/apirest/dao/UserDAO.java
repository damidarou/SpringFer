package com.nigmacode.apirest.dao;

import com.nigmacode.apirest.entity.User;

import java.util.List;


public interface UserDAO {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);
    public List<User> findByUsername(String username);

    public List<User> buscar(User user);
}