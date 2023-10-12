package com.example.Swigatto.dto.request;

import com.example.Swigatto.Enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {

    int restaurantId;

    String dishName;

    boolean veg;

    boolean available;

    double price;

    FoodCategory foodCategory;

}
