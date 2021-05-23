package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("serialCarNumber")
    private String serialNumber;
    @ManyToOne
    @JsonProperty("owner")
    private User owner;
    @JsonProperty("scope")
    private Integer scope;
    @JsonProperty("parking_id")
    private Long parking_id;


    public Car(String brand, String serialNumber, User owner) {
        this.active = true;
        this.owner = owner;
        this.brand = brand;
        this.serialNumber = serialNumber;
    }

}
