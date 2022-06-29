package com.sp.supermarket.service;

import org.junit.Test;

/**
 * @author Waleed Naveed
 * 27/6/22
 */
public class CartTest {

    private Cart cart;

    public CartTest() {
        cart = new Cart();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateOfferNegative(){
        cart.validateOffer("buy_3_get_1_free");
    }

}
