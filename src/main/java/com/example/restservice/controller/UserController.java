package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.model.UserStatus;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public List<User> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    UserStatus login(@RequestBody User user) { return userService.loginUser(user); }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public User getMyUser(){
        return userService.getMy();
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public @ResponseBody
    User getOne(@PathVariable String username) {
        return userService.getOne(username);
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void update(@RequestBody User user) {
        userService.updateUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void remove (@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/registreation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody boolean register(@RequestBody User user) {
        return userService.registerUser(user);
    }
}

