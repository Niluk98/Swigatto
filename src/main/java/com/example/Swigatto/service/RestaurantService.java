package com.example.Swigatto.service;

import com.example.Swigatto.dto.request.FoodRequest;
import com.example.Swigatto.dto.request.RestaurantRequestDTO;
import com.example.Swigatto.dto.response.RestaurantResponseDTO;
import com.example.Swigatto.exceptions.RestaurantNotFoundException;
import com.example.Swigatto.model.FoodItems;
import com.example.Swigatto.model.Restraunts;
import com.example.Swigatto.repository.RestaurantRepository;
import com.example.Swigatto.transformers.FoodItemTransformer;
import com.example.Swigatto.transformers.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO restaurantRequest) {
        Restraunts restaurant= RestaurantTransformer.restaurantRequestToRestraunt(restaurantRequest);

        //save
        Restraunts savedRestaurant=restaurantRepository.save(restaurant);

        // savedRestaurant -> response
        return RestaurantTransformer.restaurantToRestrauntResponse(savedRestaurant);




    }

    public String changeStatus(int id) {
        Optional<Restraunts> restrauntsOptional=restaurantRepository.findById(id);
        if(restrauntsOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant not found!!");
        }

        Restraunts restraunt = restrauntsOptional.get();
        restraunt.setOpen(!restraunt.isOpen());

        restaurantRepository.save(restraunt);

        if(restraunt.isOpen()){
            return "Restaurant is open";
        }else{
            return "Restaurant is closed";
        }


    }

    public RestaurantResponseDTO addFoodToRestaurant(FoodRequest foodRequest) {
        int restaurantId= foodRequest.getRestaurantId();
        Optional<Restraunts> restrauntsOptional= restaurantRepository.findById(restaurantId);
        if(restrauntsOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant not found!!");
        }
        Restraunts restraunts=restrauntsOptional.get();
        //get food item from request
        FoodItems foodItem = FoodItemTransformer.foodRequestToFood(foodRequest);
        //allocate restaurant to foodItem
        foodItem.setRestraunt(restraunts);
        // set the food item to restraurant
        List<FoodItems> menu;
        if(restraunts.getAvailableFoodItems()==null){
            menu=new ArrayList<>();
            menu.add(foodItem);
        }else{
            menu= restraunts.getAvailableFoodItems();
            menu.add(foodItem);
        }
        restraunts.setAvailableFoodItems(menu);
        //save
        restaurantRepository.save(restraunts);

        return RestaurantTransformer.restaurantToRestrauntResponse(restraunts);
    }
}
