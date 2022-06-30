package com.sp.supermarket.service;

import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.BigDecimalUtil;
import com.sp.supermarket.utility.Constants;
import com.sp.supermarket.utility.FileManagerUtil;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class manages the items listed in the inventory
 * InventoryManager is map of inventory name String.class as key and Inventory.class as value
 * @author Waleed Naveed
 * 24/6/22
 */
public class InventoryManager {
    private Map<String, Inventory> inventoryMap;

    public InventoryManager() {
        inventoryMap = new HashMap<>();
    }

    public Map<String, Inventory> getInventoryMap() {
        return inventoryMap;
    }


    public void loadInventory(String inventoryFileName) {

        List<String> lines = null;
        try {
            lines = FileManagerUtil.getFileContent(inventoryFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format(Constants.FILE_NOT_FOUND_EXCEPTION,inventoryFileName));
        }

        lines.forEach( line ->{

            List<String> lineSeperated = Stream.of(line.split(","))
                    .collect(Collectors.toList());

            Inventory inventory = new Inventory(lineSeperated.get(0)
                    , BigDecimalUtil.getBigDecimal(lineSeperated.get(1))
                    ,Integer.parseInt(lineSeperated.get(2))
            );

            inventoryMap.put(lineSeperated.get(0),inventory);
        });

    }

    public boolean checkIfItemExists(String key) {
        return inventoryMap.containsKey(key);
    }

    public int getQuantityOfItem(String item) {
        return getInventory(item).getQuantity();
    }


    public Inventory getInventory(String item) {
        return inventoryMap.get(item);

    }


    public void validateItem(String item) {
        if(!checkIfItemExists(item))
            throw new IllegalArgumentException(String.format(Constants.RESPONSE_ITEM_NOT_EXIST,item));

    }

    /**
     * This method will update inventory by flushing existing inventory file and generate new one
     * @param inventoryFileName
     */
    public void updateInventory(String inventoryFileName) {
        FileManagerUtil.flushFile(inventoryFileName);
        inventoryMap.forEach((itemName,inventory)->{
            FileManagerUtil.printFileLn(inventory.toCsvLine(),inventoryFileName);
        });

    }
}
