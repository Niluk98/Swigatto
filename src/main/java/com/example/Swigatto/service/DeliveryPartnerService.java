package com.example.Swigatto.service;

import com.example.Swigatto.dto.request.DeliveryPartnerRequest;
import com.example.Swigatto.model.DeliveryPartner;
import com.example.Swigatto.repository.DeliveryPartnerRepository;
import com.example.Swigatto.transformers.PartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeliveryPartnerService {
    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerService(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    public boolean addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner= PartnerTransformer.partnerRequestToPartner(deliveryPartnerRequest);

        //save
        DeliveryPartner savedDeliveryPartner=deliveryPartnerRepository.save(deliveryPartner);
        return true;
    }
}
