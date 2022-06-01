package com.nigmacode.apirest.service;

import com.nigmacode.apirest.dao.UserDAO;
import com.nigmacode.apirest.entity.User;
import com.nigmacode.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        User user = userDAO.findById(id);
        return user;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);

    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
    public User findByUsername(String username) {
        User user=userDAO.findByUsername(username);
        return user;
    }

    public List<User> buscar(User user){
        List<User> listUsers= userDAO.buscar(user);
        return listUsers;
    }


}