package com.sp.supermarket.service;

import com.sp.supermarket.utility.Constants;
import com.sp.supermarket.utility.FileManagerUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

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
            runInteractiveMode();
        } else {
            runFileMode(commandsFileName);
        }

        // update inventory
        inventoryManager.updateInventory(inventoryFileName);

    }


    /**
     * File mode will read the file from commands and print commands in output file
     * and rest will do the same
     * @param commandsFileName
     */
    public void runFileMode(String commandsFileName) {

        //reading file
        List<String> commandLines = null;
        try {
            commandLines = FileManagerUtil.getFileContent(commandsFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format(Constants.FILE_NOT_FOUND_EXCEPTION,commandsFileName));
        }

        //flushing existing output file if any
        FileManagerUtil.flushFile(Constants.OUTPUT_FILE_NAME);

        boolean endGame = false;
        int count = 0 ;


        while(!endGame || count < commandLines.size()){

            endGame = processInput(commandLines.get(count),true);
            count++;
        }

    }

    /**
     *  Interactive mode of supermarket problem, data input will be done by the user and exits on checkout
     *  and will follow the instructions
     *
     */
    public void runInteractiveMode() {

        //scanner for taking inputs
        Scanner scanner = new Scanner(System.in);

        boolean endGame = false;
        while(!endGame){
            String input = scanner.nextLine();

            endGame = processInput(input,false);
        }
    }

    /**
     * This methods takes input either from interactive or file mode and process accordingly
     * @param input
     * @param fileMode
     * @return
     */
    private boolean processInput(String input, boolean fileMode) {

        try {


            //case checkout
            if (Constants.COMMAND_CHECKOUT.equalsIgnoreCase(input)) {
                if (cart.isEmpty()) {
                    cart.processCart(inventoryManager.getInventoryMap());
                    processOutput(Constants.RESPONSE_EMPTY_CARD_EMPTY_CART, fileMode);
                    return false;
                }
                cart.updateInventory(inventoryManager.getInventoryMap());
                processOutput(Constants.RESPONSE_EMPTY_CART_DONE,fileMode);

                return true;
            }

            //case bill
            else if (Constants.COMMAND_BILL.equalsIgnoreCase(input)) {
                cart.processCart(inventoryManager.getInventoryMap());
                String bill = String.format(Constants.RESPONSE_BILL
                        , cart.getSubTotal(), cart.getDiscount(), cart.getSubTotal().subtract(cart.getDiscount()));

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
                cart.addOffer(item,offerType);

                processOutput(Constants.RESPONSE_OFFER_ADDED,fileMode);

            }

        } catch (Exception e){
            processOutput("Error occurred : "+e.getMessage(),fileMode);
        }
        return false;
    }



    private void processOutput(String response, boolean fileMode) {
        if(fileMode){
            System.out.println(response);
            FileManagerUtil.printFileLn(response,Constants.OUTPUT_FILE_NAME);
        } else {
            System.out.println(response);
        }
    }

    private void loadInventory(String inventoryFileName) {
        inventoryManager.loadInventory(inventoryFileName);
    }








}
