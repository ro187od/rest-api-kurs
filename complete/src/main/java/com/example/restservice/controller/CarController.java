package com.example.restservice.controller;

import com.example.restservice.model.Car;
import com.example.restservice.service.CarService;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/cars")
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

    @GetMapping("/cars/my")
    public List<Car> getAllMy(){
        return carService.getAllMyCars();
    }

    @RequestMapping(value = "/car/deactivate/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void deactivate(@PathVariable Long id, @RequestBody Car car) {
        carService.deactivateCar(car);
    }

    @RequestMapping(value = "/car/activate/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void activate(@PathVariable Long id, @RequestBody Car car) {
        carService.activateCar(car);
    }
}
