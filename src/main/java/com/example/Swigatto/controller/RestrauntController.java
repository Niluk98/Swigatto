package com.example.Swigatto.controller;

import com.example.Swigatto.dto.request.MenuRequest;
import com.example.Swigatto.dto.request.RestaurantRequestDTO;
import com.example.Swigatto.dto.response.RestaurantResponseDTO;
import com.example.Swigatto.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestrauntController {

    //constructor injection
    final RestaurantService restaurantService;

    @Autowired
    public RestrauntController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequestDTO restaurantRequest){

        try {
            RestaurantResponseDTO restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
            return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/status")
    public String changeOpenedStatus(@RequestParam int id){
        try{
            String message=restaurantService.changeStatus(id);
            return message;
        }catch (Exception e){
            return e.getMessage();
        }


    }

    @PostMapping("/addMenu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest){
        try{
            RestaurantResponseDTO restaurantResponse = restaurantService.addMenuToRestaurant(menuRequest);
            return new ResponseEntity<>(restaurantResponse,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
