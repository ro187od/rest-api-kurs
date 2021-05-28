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

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    @Autowired
    private ParkingRepo parkingRepo;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Parking> getAllParkings() {
        return parkingRepo.findAll();
    }

    public boolean createParking(Parking parking) {
        parkingRepo.save(parking);
        return true;
    }

    public Optional<Parking> getParking(Long id) {
        return parkingRepo.findById(id);
    }

    public List<Car> getAllCarsParking(Long id) {
        Parking parking = parkingRepo.findById(id).get();
        setCarsAdminInParking(parking);
        return parking.getCars();
    }

    private void setCarsAdminInParking(Parking parking) {
        User admin = userRepo.findById(1L).get();
        List<Car> cars = carRepo.findAll();
        List<Car> cars_parking = parking.getCars();
        cars.forEach( car -> {
            if (cars_parking.size() == 0 && car.getOwner().getRole().equals(Role.ADMIN)){
                parking.getCars().add(car);
            }
        });

    }

    public void deleteCar(Long id_parking, Long id_car) {
        Parking parking = parkingRepo.getOne(id_parking);
        Car car = carRepo.getOne(id_car);
        parking.getCars().remove(car);
        parkingRepo.save(parking);
    }

    public void addFloorParking(Parking parking) {
        int size = parking.getSizeParking();
        parking.setSizeParking(size * 2);
        parkingRepo.save(parking);
    }

    public void saleParking(Parking parking) {
        int cost = parking.getCostParking();
        parking.setCostParking(cost / 2);
        parkingRepo.save(parking);
    }

    public void expandParking(Parking parking) {
        int cost = parking.getCostParking();
        parking.setCostParking(cost + 5);
        parkingRepo.save(parking);
    }

    public void addCar(Car car) {
        if (validationService.validateUserCar(car.getOwner())) {
            Parking parking = getParking(car);
            parking.addCar(car);
            carAddParking(car, parking);
            parkingRepo.save(parking);
        }
    }

    private void carAddParking(Car car, Parking parking) {
        car.setParking_id(parking.getId());
        if(!car.getOwner().getRole().equals(Role.ADMIN)){
            car.getOwner().setMoneyInAccount(car.getOwner().getMoneyInAccount() - parking.getCostParking());
        }
        userRepo.save(car.getOwner());
        carRepo.save(car);
    }

    private Parking getParking(Car car) {
        Parking parking = parkingRepo.getOne(3L);
        return parking;
    }
}
