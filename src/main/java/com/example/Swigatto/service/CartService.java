package com.example.Swigatto.service;

import com.example.Swigatto.dto.request.FoodRequest;
import com.example.Swigatto.dto.response.CartStatusResponse;
import com.example.Swigatto.dto.response.FoodResponse;
import com.example.Swigatto.exceptions.CustomerNotFoundException;
import com.example.Swigatto.exceptions.MenuItemNotFoundException;
import com.example.Swigatto.model.Cart;
import com.example.Swigatto.model.Customer;
import com.example.Swigatto.model.FoodItem;
import com.example.Swigatto.model.MenuItem;
import com.example.Swigatto.repository.CartRepository;
import com.example.Swigatto.repository.CustomerRespository;
import com.example.Swigatto.repository.FoodRepository;
import com.example.Swigatto.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    final CustomerRespository customerRespository;
    final MenuRepository menuRepository;

   final CartRepository cartRepository;

   final FoodRepository foodRepository;

    @Autowired
    public CartService(CustomerRespository customerRespository, MenuRepository menuRepository, CartRepository cartRepository, FoodRepository foodRepository) {
        this.customerRespository = customerRespository;

        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
        this.foodRepository = foodRepository;
    }


    public CartStatusResponse addFoodToCart(FoodRequest foodRequest) {

        Customer customer= customerRespository.findByMobileNo(foodRequest.getCustomerMobileNumber());
        if(customer==null){
            throw new CustomerNotFoundException("Customer not found");
        }

        Optional<MenuItem> menuItemOptional=menuRepository.findById(foodRequest.getMenuItemId());
        if(menuItemOptional.isEmpty()){
            throw new MenuItemNotFoundException("Menu item not found");

        }
        MenuItem menuItem=menuItemOptional.get();
        if(!menuItem.isAvailable()){
            throw new MenuItemNotFoundException("Given item is not available!!");
        }
        if(!menuItem.getRestraunt().isOpen()){
            throw new MenuItemNotFoundException("Restaurant is closed !!");
        }

        FoodItem foodItem=FoodItem.builder().cart(customer.getCart()).requiredQuantity(foodRequest.getRequiredQuantity())
                .totalCost(menuItem.getPrice()*foodRequest.getRequiredQuantity()).menuItem(menuItem).build();

        Cart cart =customer.getCart();

        //save food(child) first to avoid duplicate entry as saved one will have foreign keys
        FoodItem savedFoodItem=foodRepository.save(foodItem);

        //save cart with already saved food
        cart.getFoodItems().add(foodItem);



        menuItem.getFoodItems().add(foodItem);

        MenuItem savedMenu=menuRepository.save(menuItem);

        //prepare response

        int cartTotal=0;
        List<FoodResponse> foodList=new ArrayList<>();
        for(FoodItem foodItem1:cart.getFoodItems()){
            if(foodItem1.getMenuItem()!=null){
                cartTotal+=foodItem1.getMenuItem().getPrice()*foodItem1.getRequiredQuantity();
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
        cart.setCartTotal(cartTotal);
        Cart savedCart=cartRepository.save(cart);


        CartStatusResponse cartStatusResponse=CartStatusResponse.builder().cartTotal(cartTotal)
                .address(customer.getAddress()).customerName(customer.getName()).customerMobileNo(customer.getMobileNo())
                .foodList(foodList) .restaurantName(menuItem.getRestraunt().getName()).build();


          return cartStatusResponse;


    }
}
