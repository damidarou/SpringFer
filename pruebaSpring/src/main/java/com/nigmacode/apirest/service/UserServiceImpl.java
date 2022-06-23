package com.nigmacode.apirest.service;

import com.nigmacode.apirest.repository.UserRepository;
import com.nigmacode.apirest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
       Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public List<User> findByUsername(String username) {
        List<User> user= userRepository.findByUsername(username);
        return user;
    }

    public List<User> buscar(User user){
        Example<User> userExample= Example.of(user);
        List<User> users = userRepository.findAll(userExample);
        return users;
    }


}