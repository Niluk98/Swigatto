package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.request.DeliveryPartnerRequest;
import com.example.Swigatto.model.DeliveryPartner;

import java.util.ArrayList;

public class PartnerTransformer {

    public static DeliveryPartner partnerRequestToPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .gender(deliveryPartnerRequest.getGender())
                .name(deliveryPartnerRequest.getName()).mobileNo(deliveryPartnerRequest.getMobileNo())
                .orders(new ArrayList<>())
                .build();
    }
}
