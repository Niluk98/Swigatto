package com.example.Swigatto.dto.response;

import com.example.Swigatto.Enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDTO {
    String name;

    String location;


    RestaurantCategory category;


    String contactNo;

    boolean open;


    List<FoodResponse> availableFoodItems=new ArrayList<>();

}
