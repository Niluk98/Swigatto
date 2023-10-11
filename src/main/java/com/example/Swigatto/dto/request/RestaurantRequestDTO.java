package com.example.Swigatto.dto.request;

import com.example.Swigatto.Enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDTO {

    String name;

    String location;


    RestaurantCategory category;


    String contactNo;

}
