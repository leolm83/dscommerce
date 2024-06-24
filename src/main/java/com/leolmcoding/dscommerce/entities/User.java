package com.leolmcoding.dscommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, LocalDate birthDate, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
