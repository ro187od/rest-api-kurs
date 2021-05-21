package com.example.restservice.controller;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import com.example.restservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getAllUsers() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "/car/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    boolean register(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void remove (@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @RequestMapping(value = "/car/update/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void updateCar(@RequestBody Car car) {
        carService.updateCar(car);
    }
}
