package com.sp.supermarket.service;

import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.Constants;
import com.sp.supermarket.utility.FileReader;
import com.sun.tools.jconsole.JConsoleContext;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * This class will manage the lifecycle of Supermarket which will contain Inventory and Cart
 * It will load inventory on start
 * Do the operations add item to cart, apply discount, billing, checkout
 * Update inventory on checkout
 * @author Waleed Naveed
 * 24/6/22
 */
public class Supermarket {

    private InventoryManager inventoryManager;
    private Cart cart;

    public Supermarket(){
        inventoryManager = new InventoryManager();
        cart = new Cart();

    }

    /**
     *  File mode of supermarket problem, data will be fetched form commands.txt
     *  and will follow the instructions
     *
     *
     * @param inventoryFileName
     * inventory.csv text file
     * @param commandsFileName
     * commands.txt file
     */
    public void run(String inventoryFileName, String commandsFileName) {
        loadInventory(inventoryFileName);
        if(commandsFileName == null){
            runInteractiveMode(inventoryFileName);
        } else {
            runFileMode(inventoryFileName,commandsFileName);
        }

    }


    /**
     *  Interactive mode of supermarket problem, data input will be done by the user and exits on checkout
     *  and will follow the instructions
     *
     *
     * @param inventoryFileName
     * inventory.csv file
     */
    public void runInteractiveMode(String inventoryFileName) {

        //scanner for taking inputs
        Scanner scanner = new Scanner(System.in);

        boolean endGame = false;

        while(!endGame){
            String input = scanner.nextLine();

            endGame = processInput(input,false);
        }
    }

    private boolean processInput(String input, boolean fileMode) {

        try {


            //case checkout
            if (Constants.COMMAND_CHECKOUT.equalsIgnoreCase(input)) {
                if (cart.isEmpty()) {
                    cart.processCart(inventoryManager.getInventoryMap());
                    processOutput(Constants.RESPONSE_EMPTY_CARD_EMPTY_CART, fileMode);
                    return true;
                }

                return true;
            }

            //case bill
            else if (Constants.COMMAND_BILL.equalsIgnoreCase(input)) {
                cart.processCart(inventoryManager.getInventoryMap());
                String bill = String.format(Constants.RESPONSE_BILL
                        , cart.getSubTotal(), cart.getDiscount(), cart.getSubTotal() - cart.getDiscount());

                processOutput(bill, fileMode);
            }

            //case add
            if (input.startsWith(Constants.PREFIX_ADD)) {

                //checking add regex
                if (!input.matches(Constants.REGEX_ADD))
                    throw new IllegalArgumentException(Constants.INVALID_ADD_STATEMENT_EXCEPTION);
                //spitting string to get contents of add string
                String[] addArr = input.split(" ");

                String item = addArr[1];
                Integer quantity = Integer.valueOf(addArr[2]);

                //validate if item exists in inventory
                inventoryManager.validateItem(item);
                //fetch map data with item
                if (quantity <= inventoryManager.getQuantityOfItem(item)) {
                    cart.add(item, quantity);
                    cart.processCart(inventoryManager.getInventoryMap());
                    processOutput(String.format(Constants.RESPONSE_ITEM_ADDED,item,quantity),fileMode);
                } else {
                    processOutput(String.format(Constants.RESPONSE_ITEM_OUT_OF_STOCK, item), fileMode);
                }

            }

            if(input.startsWith(Constants.PREFIX_OFFER)){
                //checking add regex
                if (!input.matches(Constants.REGEX_OFFER))
                    throw new IllegalArgumentException(Constants.INVALID_OFFER_STATEMENT_EXCEPTION);

                String[] offerArr = input.split(" ");
                String offerType = offerArr[1];
                cart.validateOffer(offerType);
                String item = offerArr[2];
                inventoryManager.validateItem(item);

                processOutput(Constants.RESPONSE_OFFER_ADDED,fileMode);

            }

        } catch (Exception e){
            processOutput("Error occurred : "+e.getMessage(),fileMode);
            e.printStackTrace();
        }
        return false;
    }

    private void processOutput(String response, boolean fileMode) {
        if(fileMode){

        } else {
            System.out.println(response);
        }
    }

    private void loadInventory(String inventoryFileName) {
        inventoryManager.loadInventory(inventoryFileName);
    }


    public void runFileMode(String inventoryFileName, String commandsFileName) {

    }





}
