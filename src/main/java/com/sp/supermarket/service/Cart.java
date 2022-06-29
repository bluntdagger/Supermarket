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
    private Map<String,Integer> cartMap;

    //product name and offer
    private Map<String,String> offerMap ;
    //Total price of cart
    private Double subTotal;
    //discount on basis of offers
    private Double discount;

    public Cart() {
        cartMap = new HashMap<>();
        subTotal = 0.00;
        discount = 0.00;
    }

    private void resetPriceOfCart(){
        subTotal = 0.00;
        discount = 0.00;
    }

    public boolean isEmpty() {
        return cartMap.isEmpty();
    }


    public Map<String, Integer> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<String, Integer> cartMap) {
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
     *
     * @param item
     * item name to be added
     * @param quantity
     * quantity of item to be added
     *
     */
    public void add(String item, Integer quantity) {
        if(cartMap.containsKey(item)){
            cartMap.put(item,cartMap.get(item)+quantity);
        } else{
            cartMap.put(item,quantity);
        }
    }


    /**
     * Process cart is method which process all bills and offers added in the cart map
     * and offer map. It will calculate the subtotal total and discount when called.
     *
     */
    public void processCart(Map<String, Inventory> inventoryMap) {
        if(!cartMap.isEmpty()){
            resetPriceOfCart();
            //iterate map
            cartMap.forEach((item, quantity) ->{
                subTotal = subTotal + inventoryMap.get(item).getAmount() * quantity;
            });
            //todo discount implementation
        }
    }
}
