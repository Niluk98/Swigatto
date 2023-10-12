package com.example.Swigatto.controller;

import com.example.Swigatto.dto.request.DeliveryPartnerRequest;
import com.example.Swigatto.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }


    @PostMapping("/add")
    public ResponseEntity addPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){

        boolean message=deliveryPartnerService.addPartner(deliveryPartnerRequest);

        return new ResponseEntity<>("You have been registered", HttpStatus.CREATED);

    }
}
