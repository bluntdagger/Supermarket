package com.sp.supermarket.service;

import com.sp.supermarket.utility.Constants;
import com.sp.supermarket.utility.FileReader;

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


        while(true){
            String input = scanner.next();

            if(Constants.COMMAND_CHECKOUT.equalsIgnoreCase(input)){
                if(cart.isEmpty()){
                    System.out.println(Constants.RESPONSE_EMPTY_CARD_EMPTY_CART);
                    System.exit(0);
                }
            }

            if("exit".equalsIgnoreCase(input)){
                scanner.close();
                System.out.println("Done");
                System.exit(0);
            }
        }
    }

    private void loadInventory(String inventoryFileName) {
        inventoryManager.loadInventory(inventoryFileName);
    }


    public void runFileMode(String inventoryFileName, String commandsFileName) {
    }





}
