package com.example.Swigatto.repository;

import com.example.Swigatto.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuItem,Integer> {
}
