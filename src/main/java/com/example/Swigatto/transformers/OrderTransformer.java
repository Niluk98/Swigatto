package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.response.FoodResponse;
import com.example.Swigatto.dto.response.OrderResponse;
import com.example.Swigatto.model.Cart;
import com.example.Swigatto.model.Customer;
import com.example.Swigatto.model.FoodItem;
import com.example.Swigatto.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderTransformer {

    public static OrderEntity makeOrderEntity(Customer customer){
        Cart cart=customer.getCart();
        return OrderEntity.builder()
                .orderTotal(cart.getCartTotal())
                .customer(customer)
                .orderId(String.valueOf(UUID.randomUUID())).build();
    }

    public static OrderResponse orderToOrderResponse(OrderEntity savedOrder) {

        List<FoodResponse> foodList=new ArrayList<>();
        for(FoodItem foodItem1:savedOrder.getFoodItems()){
            if(foodItem1.getMenuItem()!=null){

                FoodResponse foodResponse=FoodResponse.builder()
                        .veg(foodItem1.getMenuItem().isVeg())
                        .foodCategory(foodItem1.getMenuItem().getFoodCategory())
                        .available(foodItem1.getMenuItem().isAvailable())
                        .dishName(foodItem1.getMenuItem().getDishName())
                        .quantity(foodItem1.getRequiredQuantity())
                        .build();
                foodList.add(foodResponse);
            }

        }
        return OrderResponse.builder()
                .orderId(savedOrder.getOrderId())
                .customerMobile(savedOrder.getOrderId())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .time(savedOrder.getTime())
                .restaurantName(savedOrder.getRestraunt().getName())
                .foodItems(foodList)
                .build();
    }
}
