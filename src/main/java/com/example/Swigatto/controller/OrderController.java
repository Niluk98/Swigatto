package com.example.Swigatto.controller;

import com.example.Swigatto.dto.response.OrderResponse;
import com.example.Swigatto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    final OrderService orderService;

@Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/{mobile}")
    public ResponseEntity addOrder(@PathVariable("mobile") String mobile){

        try {
            OrderResponse orderResponse=orderService.addOrder(mobile);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
