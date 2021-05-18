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

    private User currentUser;

    @Autowired
    private UserRepo userRepo;



    public User getOne(String username){
        User user = userRepo.findByUsername(username);
        return user;
    }

    public boolean registerUser(User user) {
        if(userRepo.findByUsername(user.getUsername()) == null){
            userRepo.save(user);
            return true;
        }
        return false;

    }

    public User loginUser(User user){
        currentUser = userRepo.findByUsername(user.getUsername());
        if(userRepo.findByUsername(user.getUsername()) == null){
            return null;
        }
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
