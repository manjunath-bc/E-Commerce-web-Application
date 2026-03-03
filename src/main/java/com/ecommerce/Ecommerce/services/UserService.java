package com.ecommerce.Ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Get All Users
    public List<User> getUsers() {

        return userRepository.findAll();
    }


    // Add User
    public User addUser(User user) {

        return userRepository.save(user);
    }


    // Check Login
    public User checkLogin(String username,
                           String password) {

        return userRepository
                .findByUsernameAndPassword(
                        username,
                        password);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Check User Exists
    public boolean checkUserExists(String username) {

        return userRepository
                .existsByUsername(username);
    }


    // Get User By Username
    public User getUserByUsername(String username) {

        return userRepository
                .findByUsername(username);
    }

}