package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findUser(@PathVariable("id") long id) {
        return userService.findUser(id);
    }

    @PostMapping("/add")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@RequestBody User user, @PathVariable("id") long id) {
        userService.updateUser(user, id);
    }
}
