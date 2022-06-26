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
    //todo review this data structure this might not be suitable
    private Map<Inventory,Integer> cartMap;
    //Total price of cart
    private Double subTotal;
    //discount on basis of offers
    private Double discount;

    public Cart() {
        cartMap = new HashMap<>();
        subTotal = 0.00;
        discount = 0.00;
    }

    public boolean isEmpty() {
        return cartMap.isEmpty();
    }


    public Map<Inventory, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Inventory, Integer> cartMap) {
        this.cartMap = cartMap;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Add item to the cart
     * @param item
     * item name to be added
     * @param quantity
     * quantoty of item to be added
     */
    public void add(String item, Integer quantity) {
        //todo
    }
}
