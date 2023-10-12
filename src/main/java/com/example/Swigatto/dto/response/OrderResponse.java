package com.example.Swigatto.dto.response;

import com.example.Swigatto.model.FoodItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    String orderId;

    double orderTotal;


    Date time;


    String customerName;


    String customerMobile;


    String deliveryPartnerName;


    String deliveryPartnerMobile;


    String restaurantName;


    List<FoodResponse> foodItems;
}
