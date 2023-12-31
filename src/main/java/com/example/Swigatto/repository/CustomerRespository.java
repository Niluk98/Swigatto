package com.example.Swigatto.repository;


import com.example.Swigatto.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespository extends JpaRepository<Customer,Integer> {
    public  Customer findByMobileNo(String mobile);


}
