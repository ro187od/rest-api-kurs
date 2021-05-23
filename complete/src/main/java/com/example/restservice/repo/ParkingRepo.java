package com.example.restservice.repo;

import com.example.restservice.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepo extends JpaRepository<Parking, Long> {
    Optional<Parking> findById(Long id);
}
