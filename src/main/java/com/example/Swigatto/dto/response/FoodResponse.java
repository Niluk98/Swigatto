package com.example.Swigatto.dto.response;

import com.example.Swigatto.Enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodResponse {
    String dishName;

    boolean veg;

    boolean available;

    FoodCategory foodCategory;
}
