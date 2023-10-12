package com.example.Swigatto.dto.request;

import com.example.Swigatto.Enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartnerRequest {

    String name;


    String mobileNo;


    Gender gender;
}
