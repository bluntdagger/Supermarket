package com.sp.supermarket.service;

import com.sp.supermarket.utility.BigDecimalUtil;
import com.sp.supermarket.utility.Constants;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Waleed Naveed
 * 27/6/22
 */
public class CartTest {

    private static final String ITEM_TOOTHPASTE = "toothpaste" ;
    private static final String ITEM_SOAP = "soap";
    private static final String ITEM_CHOCOLATE= "chocolate";
    private Cart cart;

    public CartTest() {
        cart = new Cart();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateOfferNegative(){
        cart.validateOffer("buy_3_get_1_free");
    }

    @Test
    public void addOfferTest(){

        cart.addOffer(ITEM_TOOTHPASTE, Constants.OFFER_BUY1GETHALFOFF);
        cart.addOffer(ITEM_SOAP, Constants.OFFER_BUY2GET1FREE);
        assertEquals(Constants.OFFER_BUY1GETHALFOFF,cart.getOfferMap().get(ITEM_TOOTHPASTE));
        assertEquals(Constants.OFFER_BUY2GET1FREE,cart.getOfferMap().get(ITEM_SOAP));
        cart.addOffer(ITEM_SOAP, Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(Constants.OFFER_BUY1GETHALFOFF,cart.getOfferMap().get(ITEM_TOOTHPASTE));
        assertEquals(Constants.OFFER_BUY1GETHALFOFF,cart.getOfferMap().get(ITEM_SOAP));
    }

    @Test
    public void calculateDiscountUnitsForEveryItemTest(){

        BigDecimal expected , actual ;

        //testing edge cases when quantity is 1
        expected = BigDecimalUtil.getBigDecimal(0.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(1, BigDecimalUtil.getBigDecimal(1.00),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);

        actual =  cart.calculateDiscountUnitsForEveryItem(1, BigDecimalUtil.getBigDecimal(1.00),Constants.OFFER_BUY2GET1FREE);
        assertEquals(expected,actual);

        //quantity 2
        expected = BigDecimalUtil.getBigDecimal(0.50);
        actual =  cart.calculateDiscountUnitsForEveryItem(2, BigDecimalUtil.getBigDecimal(1.00),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);

        expected = BigDecimalUtil.getBigDecimal(0.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(2, BigDecimalUtil.getBigDecimal(1.00),Constants.OFFER_BUY2GET1FREE);
        assertEquals(expected,actual);

        //quantity 3
        expected = BigDecimalUtil.getBigDecimal(5.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(3, BigDecimalUtil.getBigDecimal(10.00),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);

        expected = BigDecimalUtil.getBigDecimal(10.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(3, BigDecimalUtil.getBigDecimal(10.00),Constants.OFFER_BUY2GET1FREE);
        assertEquals(expected,actual);

        expected = BigDecimalUtil.getBigDecimal(49.50);
        actual =  cart.calculateDiscountUnitsForEveryItem(3, BigDecimalUtil.getBigDecimal(99.00),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);


        //quantity 4
        expected = BigDecimalUtil.getBigDecimal(99.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(4, BigDecimalUtil.getBigDecimal(99.00),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);

        expected = BigDecimalUtil.getBigDecimal(99.50);
        actual =  cart.calculateDiscountUnitsForEveryItem(4, BigDecimalUtil.getBigDecimal(99.50),Constants.OFFER_BUY1GETHALFOFF);
        assertEquals(expected,actual);


        expected = BigDecimalUtil.getBigDecimal(99.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(4, BigDecimalUtil.getBigDecimal(99.00),Constants.OFFER_BUY2GET1FREE);
        assertEquals(expected,actual);

        // quantity 5
        expected = BigDecimalUtil.getBigDecimal(198.00);
        actual =  cart.calculateDiscountUnitsForEveryItem(6, BigDecimalUtil.getBigDecimal(99.00),Constants.OFFER_BUY2GET1FREE);
        assertEquals(expected,actual);





    }

}
