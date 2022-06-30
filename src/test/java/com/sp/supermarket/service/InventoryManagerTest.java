package com.sp.supermarket.service;


import com.sp.supermarket.model.Inventory;
import com.sp.supermarket.utility.BigDecimalUtil;
import com.sp.supermarket.utility.FileManagerUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class InventoryManagerTest {
    private static final String ITEM_TOOTHPASTE = "toothpaste" ;
    private static final String ITEM_SOAP = "soap";
    private static final String ITEM_CHOCOLATE= "chocolate";
    private InventoryManager inventoryManager;

    public InventoryManagerTest() {
        inventoryManager = new InventoryManager();
        inventoryManager.loadInventory("TestInventory.csv");

    }

    @Test
    public void testLoadInventory(){

        Map<String, Inventory> inventoryMap =  inventoryManager.getInventoryMap();

        Inventory toothpaste = new Inventory(ITEM_TOOTHPASTE, BigDecimalUtil.getBigDecimal(10.00),99);
        Inventory soap = new Inventory(ITEM_SOAP, BigDecimalUtil.getBigDecimal(2.50),9);

        Map<String,Inventory> expectedMap = new HashMap<>();
        expectedMap.put(toothpaste.getProductName(),toothpaste);
        expectedMap.put(soap.getProductName(),soap);


        assertEquals(expectedMap, inventoryMap);

    }

    @Test
    public void testIfItemExist(){
        assertTrue(inventoryManager.checkIfItemExists(ITEM_TOOTHPASTE));
        assertFalse(inventoryManager.checkIfItemExists(ITEM_CHOCOLATE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGetInventoryItemNegative(){
        String item = ITEM_CHOCOLATE;
        inventoryManager.validateItem(item);
        inventoryManager.getInventory(item);
    }

    @Test
    public void testGetInventoryItem(){
        Inventory inventory =  inventoryManager.getInventory(ITEM_TOOTHPASTE);

        Inventory expectedInventory = new Inventory(ITEM_TOOTHPASTE, BigDecimalUtil.getBigDecimal(10.00),99);

        assertEquals(expectedInventory,inventory);
    }

    @Test(expected = FileNotFoundException.class)
    public void testGetContentException() throws FileNotFoundException {
        FileManagerUtil.getFileContent("sadapay.txt");

    }

    @Test (expected = IllegalArgumentException.class)
    public void testValidateItemNegative(){
        inventoryManager.validateItem(ITEM_CHOCOLATE);
    }


}
