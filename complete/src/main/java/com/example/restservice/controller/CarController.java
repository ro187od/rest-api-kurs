package com.example.restservice.controller;

import com.example.restservice.model.Car;
import com.example.restservice.service.CarService;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getAll(){
        return carService.getAllCars();
    }

    @RequestMapping(value = "/car/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody boolean register(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @RequestMapping(value = "/car/update/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void update(@PathVariable Long id, @RequestBody Car car) {
        carService.updateCar(car);
    }

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void remove (@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @RequestMapping(value = "/cars/my", method = RequestMethod.GET)
    public List<Car> getAllMy(){
        return carService.getAllMyCars();
    }

    @RequestMapping(value = "/car/add/scope", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void addScope(@RequestBody Car car) {
        carService.addScope(car);
    }

}
