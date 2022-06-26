package com.sp.supermarket.service;

import com.sp.supermarket.model.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will manage cart, calculate total price, discounts
 * @author Waleed Naveed
 * 24/6/22
 */
public class Cart {

    //Inventory and Number of same items
    private Map<Inventory,Integer> cartMap;
    //Total price of cart
    private Double totalPrice;
    //discount on basis of offers
    private Double discount;

    public Cart() {
        cartMap = new HashMap<>();
        totalPrice = 0.00;
        discount = 0.00;
    }

    public boolean isEmpty() {
        return cartMap.isEmpty();
    }
}
