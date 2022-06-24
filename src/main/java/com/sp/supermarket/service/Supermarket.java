package com.sp.supermarket.service;

import com.sp.supermarket.utility.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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


    public void runInteractiveMode(String inventoryFileName) {
        getFileContent(inventoryFileName).forEach(System.out::println);
    }

    public void runFileMode(String inventoryFileName, String commandsFileName) {
        getFileContent(inventoryFileName).forEach(System.out::println);
        getFileContent(commandsFileName).forEach(System.out::println);
    }


    private List<String> getFileContent(String fileName) {
        FileReader fileReader = new FileReader();

        List<String> lines = new ArrayList<>();
        try {
            lines = fileReader.getFileContent(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return lines;
    }


}
