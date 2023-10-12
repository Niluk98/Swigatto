package com.example.Swigatto.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartStatusResponse {

    String customerName;

    String address;

    String customerMobileNo;


    double cartTotal;


    List<FoodResponse> foodList;

    String restaurantName;



}
