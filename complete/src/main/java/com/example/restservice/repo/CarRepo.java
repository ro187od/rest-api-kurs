package com.example.restservice.repo;

import com.example.restservice.model.Car;
import com.example.restservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Optional<Car> findById(Long id);

    List<Car> findByOwner(User currentUser);
}
