package com.example.restservice.service;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import com.example.restservice.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

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

    public Integer getNumberMachinesUser(User user) {
        List<Car> cars = carRepo.findByOwner(user);
        return cars.size();
    }
}
