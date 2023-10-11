package com.example.Swigatto.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    String orderId;

    String orderTotal;

    Date time;

    List<FoodResponse> foodItems;

}
