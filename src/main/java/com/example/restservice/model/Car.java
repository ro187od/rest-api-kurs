package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("motor")
    private String motor;
    @ManyToOne(cascade=CascadeType.ALL)
    @JsonProperty("owner")
    private User owner;
    @JsonProperty("costOfProduction")
    private Integer costOfProduction;
    @JsonProperty("machineModel")
    private String machineModel;
    @JsonProperty("scope")
    private Integer scope;
    @JsonProperty("cost")
    private Integer cost;
    @JsonProperty("create_date")
    private LocalDate createDate;

    public Car(String motor, User owner, Integer costOfProduction, String machineModel) {
        this.motor = motor;
        this.owner = owner;
        this.costOfProduction = costOfProduction;
        this.machineModel = machineModel;
        this.cost = costOfProduction * 2;
        this.createDate = LocalDate.now();
    }
}

