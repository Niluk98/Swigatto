package com.example.Swigatto.service;

import com.example.Swigatto.dto.response.OrderResponse;
import com.example.Swigatto.exceptions.CustomerNotFoundException;
import com.example.Swigatto.exceptions.EmptyCartException;
import com.example.Swigatto.model.*;
import com.example.Swigatto.repository.*;
import com.example.Swigatto.transformers.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    final OrderRepository orderRepository;
    final CustomerRespository customerRespository;

    final DeliveryPartnerRepository deliveryPartnerRepository;

    final RestaurantRepository restrauntsRepository;

    final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRespository customerRespository, DeliveryPartnerRepository deliveryPartnerRepository, RestaurantRepository restrauntsRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.customerRespository = customerRespository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.restrauntsRepository = restrauntsRepository;
        this.cartRepository = cartRepository;
    }

    public OrderResponse addOrder(String mobile) {
        Customer customer= customerRespository.findByMobileNo(mobile);
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }

        if(customer.getCart().getFoodItems().size()==0){
           throw new EmptyCartException("Cart is empty");
        }


        OrderEntity order= OrderTransformer.makeOrderEntity(customer);

        DeliveryPartner partner=deliveryPartnerRepository.findRandomDeliveryPartner();

        Restraunts restraunts=customer.getCart().getFoodItems().get(0).getMenuItem().getRestraunt();



        OrderEntity savedOrder=orderRepository.save(order);

        order.setDeliveryPartner(partner);

        order.setRestraunt(restraunts);

        order.setFoodItems(customer.getCart().getFoodItems());

        //setting the parents
        customer.getOrders().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restraunts.getOrders().add(savedOrder);

        for(FoodItem foodItem:customer.getCart().getFoodItems()){
            foodItem.setCart(null);
            foodItem.setOrder(savedOrder);
        }
        clearCart(customer.getCart());

        customerRespository.save(customer);
        deliveryPartnerRepository.save(partner);
        restrauntsRepository.save(restraunts);

        //prepare response

        return OrderTransformer.orderToOrderResponse(savedOrder);




    }
    public void clearCart(Cart cart){
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
        cartRepository.save(cart);
    }
}
