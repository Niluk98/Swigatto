package com.example.Swigatto.transformers;

import com.example.Swigatto.dto.request.CustomerRequestDTO;
import com.example.Swigatto.dto.response.CartResponseDTO;
import com.example.Swigatto.dto.response.CustomerResponseDTO;
import com.example.Swigatto.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestToCustomer(CustomerRequestDTO customerRequest){

        return Customer.builder().name(customerRequest.getName()).email(customerRequest.getEmail()).mobileNo(customerRequest.getMobileNo()).address(customerRequest.getAddress()).build();
    }

    public static CustomerResponseDTO CustomerToCustomerResponse(Customer customer){
        CartResponseDTO cartResponse = CartTransformer.cartToCartResponse(customer.getCart());

        return CustomerResponseDTO.builder().name(customer.getName()).mobileNo(customer.getMobileNo()).address(customer.getAddress()).cart(cartResponse).build();
    }
}
