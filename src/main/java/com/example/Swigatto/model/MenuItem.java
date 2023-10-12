package com.example.Swigatto.model;

import com.example.Swigatto.Enums.FoodCategory;
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
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    boolean veg;

    boolean available;

    double price;

    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;



    @ManyToOne
    @JoinColumn
    Restraunts restraunt;

    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
    List<FoodItem> foodItems=new ArrayList<>();



}
