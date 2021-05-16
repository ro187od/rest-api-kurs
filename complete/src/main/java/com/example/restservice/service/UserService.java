package com.example.restservice.service;

import com.example.restservice.model.Role;
import com.example.restservice.model.User;
import com.example.restservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public User currentUser;

    @Autowired
    private UserRepo userRepo;



    public User getOne(String username){
        User user = userRepo.findByUsername(username);
        return user;
    }

    public boolean registerUser(User user) {
        userRepo.save(user);
        return true;
    }

    public User loginUser(User user){
        currentUser = userRepo.findByUsername(user.getUsername());
        return currentUser;
    }

    public List<User> getAllUsers() {
        List<User> usersList = userRepo.findAllByRole(Role.USER);
        return usersList;
    }

    public List<User> getAllAdmins() {
        List<User> usersList = userRepo.findAllByRole(Role.ADMIN);
        return usersList;
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User getMy(){
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }
}
