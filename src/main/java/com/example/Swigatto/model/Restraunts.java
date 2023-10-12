package com.example.Swigatto.model;


import com.example.Swigatto.Enums.RestaurantCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")

public class Restraunts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String location;

    @Enumerated(EnumType.STRING)
    RestaurantCategory category;

    @Column(unique = true)
    String contactNo;

    boolean open;

    @OneToMany(mappedBy = "restraunt" ,cascade = CascadeType.ALL)
    List<MenuItem> availableFoodItems=new ArrayList<>();

    @OneToMany(mappedBy = "restraunt" ,cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();

}
