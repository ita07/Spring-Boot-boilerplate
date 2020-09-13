package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.dom4j.dom.DOMNodeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "index";
    }

    @GetMapping("/insertUserForm")
    public String insertUserForm(Model model) {
        //Create empty object which will bind in the PostMapping later
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/insertUser")
    public String insertUser(@ModelAttribute("user") User user) {
        userService.insertUser(user);
        return "redirect:/";
    }

    @GetMapping("/updateUser/{id}")
    public String  updateUserForm(Model model, @PathVariable("id") long id) {

        User user = userService.findUser(id);
        model.addAttribute("user", user);
        return "updateUser";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
