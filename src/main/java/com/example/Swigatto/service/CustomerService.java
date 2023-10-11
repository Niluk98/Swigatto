package com.example.Swigatto.service;

import com.example.Swigatto.dto.request.CustomerRequestDTO;
import com.example.Swigatto.dto.response.CustomerResponseDTO;
import com.example.Swigatto.exceptions.CustomerNotFoundException;
import com.example.Swigatto.model.Cart;
import com.example.Swigatto.model.Customer;
import com.example.Swigatto.repository.CustomerRespository;
import com.example.Swigatto.transformers.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    //field injection
    @Autowired
    CustomerRespository customerRespository;

    public  CustomerResponseDTO getCustomerByMobile(String mobile) {

        Customer customer = customerRespository.findByMobileNo(mobile);
        if(customer==null){
            throw new CustomerNotFoundException("Customer Not Found !");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequest) {

        //   dto -> customer entity

        Customer customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        // allocate a cart
        Cart cart= Cart.builder().cartTotal(0).customer(customer).build();

        customer.setCart(cart);

        //save both customer and cart

        Customer savedCustomer=customerRespository.save(customer);

        //prepare responseDto

        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);



    }
}
