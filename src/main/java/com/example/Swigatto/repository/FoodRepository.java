package com.example.Swigatto.repository;

import com.example.Swigatto.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodItem,Integer> {
}
