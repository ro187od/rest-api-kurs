package com.example.restservice.service;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import com.example.restservice.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private UserService userService;

    @Autowired
    private CarRepo carRepo;

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public boolean createCar(Car car) {
        carRepo.save(car);
        return true;
    }

    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }

    public void updateCar(Car car) {
        carRepo.save(car);
    }

    public List<Car> getAllMyCars() {
        return carRepo.findByOwner(userService.currentUser);
    }

    public void deactivateCar(Car car) {
        car.setActive(false);
        carRepo.save(car);
    }

    public void activateCar(Car car) {
        car.setActive(true);
        carRepo.save(car);
    }
}
