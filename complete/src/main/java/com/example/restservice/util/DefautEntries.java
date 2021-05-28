package com.example.restservice.util;

import com.example.restservice.model.Car;
import com.example.restservice.model.Parking;
import com.example.restservice.model.Role;
import com.example.restservice.model.User;
import com.example.restservice.repo.CarRepo;
import com.example.restservice.repo.ParkingRepo;
import com.example.restservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DefautEntries {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ParkingRepo parkingRepo;

    @PostConstruct
    public void init(){
        if(userRepo.count() == 0) {
            createUser();
            createCar();
            createParking();
        }
    }


    private void createUser() {
            User admin = new User("admin", "admin", Role.ADMIN, 200);
            userRepo.save(admin);
    }

    private void createCar() {
            Car car = new Car("Bmw", "12312FX", userRepo.getOne(1L));
            carRepo.save(car);
    }

    private void createParking() {
        Parking parking = new Parking(10, 15);
        parkingRepo.save(parking);
    }

}
