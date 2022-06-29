package com.sp.supermarket.utility;

import java.util.Locale;

/**
 * Constants class with messages
 * @author Waleed Naveed
 * 23/6/22
 */
public final class Constants {
    public static final String FILE_NOT_FOUND_EXCEPTION = "%s not found";
    public static final String COMMAND_CHECKOUT = "checkout";
    public static final String COMMAND_BILL = "bill";

    public static final String RESPONSE_EMPTY_CARD_DONE = "done";
    public static final String RESPONSE_EMPTY_CARD_EMPTY_CART = "empty cart";
    public static final String RESPONSE_OFFER_ADDED = "offer added";
    public static final String RESPONSE_BILL= "subtotal:" + "%s" + ", discount:" + "%s" + ", total:" + "%s";


    public static final String PREFIX_ADD = "add " ;
    public static final String RESPONSE_ITEM_NOT_EXIST = "%s doesnot exist" ;
    public static final String RESPONSE_ITEM_OUT_OF_STOCK = "%s is out of stock." ;
    public static final String REGEX_ADD = "add [a-z]+ \\d{1,5}" ;
    public static final String INVALID_ADD_STATEMENT_EXCEPTION = "add statement is not valid" ;
    public static final String INVALID_OFFER_STATEMENT_EXCEPTION = "offer statement is not valid" ;
    public static final String PREFIX_OFFER = "offer" ;
    public static final String REGEX_OFFER = "offer [a-z0-9_.]+ [a-z]+";
    public static final String OFFER_BUY2GET1FREE = "buy_2_get_1_free" ;
    public static final String OFFER_BUY1GETHALFOFF = "buy_1_get_half_off" ;
    public static final String RESPONSE_ITEM_ADDED = "added %s %s" ;
}
