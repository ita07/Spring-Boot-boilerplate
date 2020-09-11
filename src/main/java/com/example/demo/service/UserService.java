package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Get all users
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //Get user by id
    public Optional<User> findUser(long id) {
        return userRepository.findById(id);
    }
    //Post user

    public void insertUser(User user) {
        userRepository.save(user);
    }

    //Delete user
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    //Update user
    public void updateUser(User user, long id) {
        userRepository.findById(id)
                .map(oldUser -> {
                    oldUser.setFirstName(user.getFirstName());
                    oldUser.setLastName(user.getLastName());
                    oldUser.setAge(user.getAge());
                    return userRepository.save(oldUser);
                }).
                orElseGet(() -> userRepository.save(user));
    }
}
