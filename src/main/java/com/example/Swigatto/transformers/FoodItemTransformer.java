package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.request.MenuRequest;
import com.example.Swigatto.dto.response.MenuResponse;
import com.example.Swigatto.model.MenuItem;

public class FoodItemTransformer {

    public static MenuResponse foodToFoodResponse(MenuItem menuItem){

        return MenuResponse.builder().veg(menuItem.isVeg()).dishName(menuItem.getDishName()).available(menuItem.isAvailable())
                .foodCategory(menuItem.getFoodCategory()).build();
    }

    public static MenuItem foodRequestToFood(MenuRequest menuRequest){
        return MenuItem.builder().price(menuRequest.getPrice()).foodCategory(menuRequest.getFoodCategory())
                .available(menuRequest.isAvailable()).dishName(menuRequest.getDishName()).veg(menuRequest.isVeg()).build();
    }
}
