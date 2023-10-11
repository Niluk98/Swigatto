package com.example.Swigatto.repository;

import com.example.Swigatto.model.Restraunts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restraunts,Integer> {
}
