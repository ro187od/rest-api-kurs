package com.example.restservice.service;

import com.example.restservice.model.Car;
import com.example.restservice.model.Parking;
import com.example.restservice.model.Role;
import com.example.restservice.model.User;
import com.example.restservice.repo.CarRepo;
import com.example.restservice.repo.ParkingRepo;
import com.example.restservice.repo.UserRepo;
import com.example.restservice.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    protected ValidationService validationService;

    @Autowired
    private ParkingRepo parkingRepo;

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public boolean createCar(Car car) {
        carRepo.save(car);
        List<Car> cars = carRepo.findAll();
        if (cars.get(cars.size() - 1).getOwner().getRole() == Role.ADMIN){
            parkingService.addCar(car);
        }
        return true;
    }

    public void deleteCar(Long id) {
        Parking parking = parkingRepo.getOne(1L);
        parking.getCars().remove(carRepo.getOne(id));
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


    public void addScope(Car car) {
        carRepo.save(car);
    }

    public Integer getNumberMachinesUser(User user) {
        List<Car> cars = carRepo.findByOwner(user);
        return cars.size();
    }
}
