package com.example.restservice.controller;

import com.example.restservice.model.Car;
import com.example.restservice.model.Parking;
import com.example.restservice.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @RequestMapping(value = "/parkings", method = RequestMethod.GET)
    public List<Parking> getAllParkings() {
        return parkingService.getAllParkings();
    }

    @RequestMapping(value = "/parking/{id}", method = RequestMethod.GET)
    public Optional<Parking> getAllParkings(@PathVariable Long id) {
        return parkingService.getParking(id);
    }

    @RequestMapping(value = "/parking/{id}/cars", method = RequestMethod.GET)
    public List<Car> getAllCarsParking(@PathVariable Long id) {
        return parkingService.getAllCarsParking(id);
    }

    @RequestMapping(value = "/parking/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    boolean register(@RequestBody Parking parking) {
        return parkingService.createParking(parking);
    }

    @RequestMapping(value = "parking/{id_parking}/car/{id_car}", method = RequestMethod.DELETE)
    public @ResponseBody void remove (@PathVariable Long id_car, @PathVariable Long id_parking) {
        parkingService.deleteCar(id_parking, id_car);
    }

    @RequestMapping(value = "parking/add_floor", method = RequestMethod.PUT,  consumes = "application/json", produces = "application/json")
    public @ResponseBody void addFllor (@RequestBody Parking parking) {
        parkingService.addFloorParking(parking);
    }

    @RequestMapping(value = "parking/expand", method = RequestMethod.PUT,  consumes = "application/json", produces = "application/json")
    public @ResponseBody void expand (@RequestBody Parking parking) {
        parkingService.expandParking(parking);
    }

    @RequestMapping(value = "parking/sale", method = RequestMethod.PUT,  consumes = "application/json", produces = "application/json")
    public @ResponseBody void sale (@RequestBody Parking parking) {
        parkingService.saleParking(parking);
    }

    @RequestMapping(value = "parking/add/car/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public @ResponseBody void activate(@PathVariable Long id, @RequestBody Car car) {
        parkingService.addCar(car);
    }
}
