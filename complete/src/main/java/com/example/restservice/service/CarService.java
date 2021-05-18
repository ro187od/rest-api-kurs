package com.example.restservice.service;

import com.example.restservice.model.Car;
import com.example.restservice.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (userService.getMy().getId() == car.getOwner().getId()) {
            carRepo.save(car);
        }
    }

    public List<Car> getAllMyCars() {
        return carRepo.findByOwner(userService.getMy());
    }

    public void deactivateCar(Car car) {
        if (userService.getMy().getId() == car.getOwner().getId()){
            car.setActive(false);
            carRepo.save(car);
        }
    }

    public void activateCar(Car car) {
        if (userService.getMy().getId() == car.getOwner().getId()) {
            car.setActive(true);
            carRepo.save(car);
        }
    }
}
