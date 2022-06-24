package com.sp.supermarket.service;

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

    public Supermarket() {
        inventoryManager = new InventoryManager();
    }

    public void run(String inventoryFileName, String commandsFileName) {
        loadInventory(inventoryFileName);
        if(commandsFileName == null){
            runInteractiveMode(inventoryFileName);
        } else {
            runFileMode(inventoryFileName,commandsFileName);
        }
    }

    private void loadInventory(String inventoryFileName) {
        inventoryManager.loadInventory(inventoryFileName);
    }

    public void runInteractiveMode(String inventoryFileName) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.next();

            if("exit".equalsIgnoreCase(input)){
                scanner.close();
                System.out.println("Done");
                System.exit(0);
            }
        }
    }

    public void runFileMode(String inventoryFileName, String commandsFileName) {
    }





}
