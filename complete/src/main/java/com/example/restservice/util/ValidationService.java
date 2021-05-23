package com.example.restservice.util;

import com.example.restservice.model.User;
import com.example.restservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final ValidationUser validationUser;

    @Autowired
    private UserService userService;

    public boolean validateUserCar(User owner) {
        if(userService.getMy().getId() != owner.getId()){
            return false;
        }
        return true;
    }
}
