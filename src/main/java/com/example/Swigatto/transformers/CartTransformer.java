package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.response.CartResponseDTO;
import com.example.Swigatto.model.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponseDTO cartToCartResponse(Cart cart){
        return CartResponseDTO.builder().cartTotal(cart.getCartTotal()).foodItems(new ArrayList<>()).build();

    }
}
