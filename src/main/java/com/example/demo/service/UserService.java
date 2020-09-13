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
    public User findUser(long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
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
                .map(tempUser -> {
                    tempUser.setFirstName(user.getFirstName());
                    tempUser.setLastName(user.getLastName());
                    tempUser.setAge(user.getAge());
                    return userRepository.save(tempUser);
                });
    }
}
