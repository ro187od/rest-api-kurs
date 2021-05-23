package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;
    @OneToMany
    @JsonProperty("carsList")
    private List<Car> cars;
    @JsonProperty("sizeParking")
    private Integer sizeParking;
    @JsonProperty("costParking")
    private Integer costParking;

    public Parking(Integer sizeParking, Integer costParking){
        this.sizeParking = sizeParking;
        this.costParking = costParking;
    }

    public void addCar(Car car){
        cars.add(car);
    }
}
