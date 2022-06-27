package com.sp.supermarket.service;


import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.FileReader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class InventoryManagerTest {

    private InventoryManager inventoryManager;

    public InventoryManagerTest() {
        inventoryManager = new InventoryManager();
        inventoryManager.loadInventory("TestInventory.csv");

    }

    @Test
    public void testLoadInventory(){

        Map<String, Inventory> inventoryMap =  inventoryManager.getInventoryMap();

        Inventory toothpaste = new Inventory("toothpaste",10.00,99);
        Inventory soap = new Inventory("soap",2.50,9);

        Map<String,Inventory> expectedMap = new HashMap<>();
        expectedMap.put(toothpaste.getProductName(),toothpaste);
        expectedMap.put(soap.getProductName(),soap);


        assertTrue(expectedMap.equals(inventoryMap));

    }

    @Test
    public void testIfItemExist(){
        assertTrue(inventoryManager.checkIfItemExists("toothpaste"));
        assertFalse(inventoryManager.checkIfItemExists("chocolate"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetInventoryItemNegative(){
        inventoryManager.getInventory("chocolate");
    }

    @Test
    public void testGetInventoryItem(){
        Inventory inventory =  inventoryManager.getInventory("toothpaste");

        Inventory expectedInventory = new Inventory("toothpaste",10.00,99);

        assertEquals(expectedInventory,inventory);
    }



    @Test(expected = FileNotFoundException.class)
    public void testGetContentException() throws FileNotFoundException {
        FileReader.getFileContent("sadapay.txt");

    }
}
