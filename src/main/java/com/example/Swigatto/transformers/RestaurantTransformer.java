package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.request.RestaurantRequestDTO;
import com.example.Swigatto.dto.response.FoodResponse;
import com.example.Swigatto.dto.response.RestaurantResponseDTO;
import com.example.Swigatto.model.FoodItems;
import com.example.Swigatto.model.Restraunts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {

    public static Restraunts restaurantRequestToRestraunt(RestaurantRequestDTO restaurantRequest){
        return Restraunts.builder().name(restaurantRequest.getName())
                .availableFoodItems(new ArrayList<>()).orders(new ArrayList<>()).category(restaurantRequest.getCategory()).open(true).contactNo(restaurantRequest.getContactNo()).location(restaurantRequest.getLocation()).build();
    }

    public static RestaurantResponseDTO restaurantToRestrauntResponse(Restraunts restaurant){
        List<FoodResponse> menu=restaurant.getAvailableFoodItems().stream().map(foodItems -> FoodItemTransformer.foodToFoodResponse(foodItems)).collect(Collectors.toList());
        return RestaurantResponseDTO.builder().name(restaurant.getName()).category(restaurant.getCategory())
                .open(restaurant.isOpen()).contactNo(restaurant.getContactNo()).location(restaurant.getLocation()).availableFoodItems(menu).build();

    }
}
