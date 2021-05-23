package com.example.restservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("numberMachines")
    private Integer numberMachines;
    @JsonProperty("moneyInAccount")
    private Integer moneyInAccount;

    public User(String username, String password, Role role, Integer moneyInAccount) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.moneyInAccount = moneyInAccount;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void pay(Integer cost){
        this.moneyInAccount = this.moneyInAccount - cost;
    }
}
