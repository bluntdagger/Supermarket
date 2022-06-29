package com.sp.supermarket.service;

import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.BigDecimalUtil;
import com.sp.supermarket.utility.Constants;

import java.math.BigDecimal;
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
    private BigDecimal subTotal;
    //discount on basis of offers
    private BigDecimal discount;

    public Cart() {
        cartMap = new HashMap<>();
        subTotal = BigDecimalUtil.getBigDecimal(0.00);
        discount = BigDecimalUtil.getBigDecimal(0.00);
    }

    private void resetPriceOfCart(){
        subTotal = BigDecimalUtil.getBigDecimal(0.00);
        discount = BigDecimalUtil.getBigDecimal(0.00);
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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
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
            cartMap.forEach((item, quantity) -> subTotal = subTotal.add(inventoryMap.get(item).getAmount().multiply(BigDecimal.valueOf(quantity))));
            //todo discount implementation
        }
    }

    public void validateOffer(String offerType) {
        if(!(Constants.OFFER_BUY2GET1FREE.equals(offerType) || Constants.OFFER_BUY1GETHALFOFF.equals(offerType)))
            throw new IllegalArgumentException(Constants.INVALID_OFFER_STATEMENT_EXCEPTION);
    }
}
