package com.sp.supermarket.service;

import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.Constants;
import com.sp.supermarket.utility.FileReader;

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
    Map<String, Inventory> inventoryMap;

    public InventoryManager() {
        inventoryMap = new HashMap<>();
    }

    public Map<String, Inventory> getInventoryMap() {
        return inventoryMap;
    }

    public void loadInventory(String inventoryFileName) {

        List<String> lines = null;
        try {
            lines = FileReader.getFileContent(inventoryFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format(Constants.FILE_NOT_FOUND_EXCEPTION,inventoryFileName));
        }

        lines.forEach( line ->{

            List<String> lineSeperated = Stream.of(line.split(","))
                    .collect(Collectors.toList());

            Inventory inventory = new Inventory(lineSeperated.get(0)
                    ,Double.parseDouble(lineSeperated.get(1))
                    ,Integer.parseInt(lineSeperated.get(2))
            );

            inventoryMap.put(lineSeperated.get(0),inventory);
        });

        inventoryMap.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
