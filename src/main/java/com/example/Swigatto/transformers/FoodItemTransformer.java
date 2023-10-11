package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.request.FoodRequest;
import com.example.Swigatto.dto.response.FoodResponse;
import com.example.Swigatto.model.FoodItems;

public class FoodItemTransformer {

    public static FoodResponse foodToFoodResponse(FoodItems foodItems){

        return FoodResponse.builder().veg(foodItems.isVeg()).dishName(foodItems.getDishName()).available(foodItems.isAvailable())
                .foodCategory(foodItems.getFoodCategory()).build();
    }

    public static FoodItems foodRequestToFood(FoodRequest foodRequest){
        return FoodItems.builder().price(foodRequest.getPrice()).foodCategory(foodRequest.getFoodCategory())
                .available(foodRequest.isAvailable()).dishName(foodRequest.getDishName()).veg(foodRequest.isVeg()).build();
    }
}
