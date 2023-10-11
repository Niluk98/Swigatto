package com.example.Swigatto.controller;

import com.example.Swigatto.dto.request.CustomerRequestDTO;
import com.example.Swigatto.dto.response.CustomerResponseDTO;
import com.example.Swigatto.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {



    //  constructor injection
   final CustomerService customerService;


   @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDTO customerRequest){
         CustomerResponseDTO customerResponse = customerService.addCustomer(customerRequest);

         return new ResponseEntity(customerResponse, HttpStatus.CREATED);

    }
    @GetMapping("/get/mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile){
       try{
           CustomerResponseDTO customerResponse =customerService.getCustomerByMobile(mobile);
           return new ResponseEntity(customerResponse,HttpStatus.FOUND);

       }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }

    }
}
