package com.example.restservice.service;

import com.example.restservice.model.Role;
import com.example.restservice.model.User;
import com.example.restservice.model.UserStatus;
import com.example.restservice.repo.CarRepo;
import com.example.restservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private User currentUser;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarService carService;

    public User getOne(String username){
        User user = userRepo.findByUsername(username);
        return user;
    }

    public boolean registerUser(User user) {
        userRepo.save(user);
        return true;

    }

    public UserStatus loginUser(User user){
        if (userRepo.findByUsername(user.getUsername()) != null){
            currentUser = userRepo.findByUsername(user.getUsername());
            if(currentUser.getRole().equals(Role.ADMIN ) && currentUser.getPassword().equals(user.getPassword())){
                return UserStatus.CREATED_ADMIN;
            }else if(currentUser.getRole().equals(Role.USER) && currentUser.getPassword().equals(user.getPassword())) {
                return UserStatus.CREATED_USER;
            }else{
                return UserStatus.INCORRECT_DATA;
            }
        }else{
            return UserStatus.UNKNOWER;
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = userRepo.findAllByRole(Role.USER);
        for(User user : usersList){
            user.setNumberMachines(carService.getNumberMachinesUser(user));
        }
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



    public void save(User user) {
        User user1 = userRepo.findByUsername(user.getUsername());
        userRepo.save(user);
    }

    public void payForParking(String username, Integer costParking) {
        currentUser.pay(costParking);
        userRepo.save(currentUser);
    }

    public void cashIn(User user) {
        user.setMoneyInAccount(user.getMoneyInAccount() + 100);
        userRepo.save(user);
    }

}
