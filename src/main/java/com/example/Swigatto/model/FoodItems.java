package com.example.Swigatto.model;

import com.example.Swigatto.Enums.FoodCategory;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_item")
public class FoodItems {
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
    OrderEntity order;

    @ManyToOne
    @JoinColumn
    Restraunts restraunt;



}
