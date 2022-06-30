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

    private Map<String,BigDecimal> discountMap;

    public Cart() {
        cartMap = new HashMap<>();
        offerMap = new HashMap<>();
        discountMap = new HashMap<>();
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

    public Map<String, String> getOfferMap() {
        return offerMap;
    }

    public void setOfferMap(Map<String, String> offerMap) {
        this.offerMap = offerMap;
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

            //discount calculation
            cartMap.forEach((item, quantity) -> {
                //calculating subtotal
                subTotal = subTotal.add(inventoryMap.get(item).getAmount().multiply(BigDecimal.valueOf(quantity)));
                //calculating discount
                discount = discount.add(calculateDiscountUnitsForEveryItem(quantity,inventoryMap.get(item).getAmount(),offerMap.get(item)));

            }  );



        }
    }

    /**
     *
     * For offer 1 every third item is free
     * For offer 2 there is sequence computation of even and odd
     * sequence computation of  x + x/2 + x + x/2 + . . . .
     * x is cost and n is quantity
     * even case (n/2 * x) + (n/2 * x/2)
     * odd case (n-1/2 * x) + (n-1/2 * x/2) + x
     * @param quantity quantity of number of items
     * @param amount amount of item
     * @param offerType offer type
     * @return
     */
    public BigDecimal calculateDiscountUnitsForEveryItem(Integer quantity, BigDecimal amount, String offerType) {

        //edge case
        if(quantity < 2 )
            return BigDecimalUtil.getBigDecimal(0.00);


        if(Constants.OFFER_BUY2GET1FREE.equals(offerType)) {
            return amount.multiply(BigDecimal.valueOf(quantity / 3));
        } else if (Constants.OFFER_BUY1GETHALFOFF.equals(offerType)){

            if(quantity % 2 == 0){
                return (
                        amount.multiply(BigDecimal.valueOf(quantity)).subtract
                        ((amount.multiply(BigDecimal.valueOf(quantity/2)))
                                .add(amount.divide(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf(quantity/2))))
                );
            } else {
                return (
                        amount.multiply(BigDecimal.valueOf(quantity)).subtract
                                ((amount.multiply(BigDecimal.valueOf((quantity-1)/2)))
                                .add(amount.divide(BigDecimal.valueOf(2)).multiply(BigDecimal.valueOf((quantity-1)/2)))
                                .add(amount))
                );

            }
        }
        return BigDecimalUtil.getBigDecimal(0.00);
    }

    public void validateOffer(String offerType) {
        if(!(Constants.OFFER_BUY2GET1FREE.equals(offerType) || Constants.OFFER_BUY1GETHALFOFF.equals(offerType)))
            throw new IllegalArgumentException(Constants.INVALID_OFFER_STATEMENT_EXCEPTION);
    }


    public void addOffer(String item, String offerType) {
        offerMap.put(item,offerType);
    }

    public void updateInventory(Map<String, Inventory> inventoryMap) {
        cartMap.forEach((item,quantity)->{
            //subtracting from inventory
            Inventory inv = inventoryMap.get(item);
            inv.setQuantity(inv.getQuantity()-quantity);
        });
    }
}
