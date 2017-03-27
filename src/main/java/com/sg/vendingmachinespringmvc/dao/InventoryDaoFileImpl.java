/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author trevor
 */
public class InventoryDaoFileImpl implements InventoryDao {

    private Map<String, Item> inventory;
    private final String INVENTORY_FILE;
    private final String DELIMITER = "::";
    
    public InventoryDaoFileImpl(String fileName) {
        this.inventory = new HashMap<>();
        this.INVENTORY_FILE = fileName;
        
        try{
            loadFromFile();
        } catch(VendingMachinePersistenceException e) {
            new VendingMachinePersistenceException(e.getMessage());
        }   
    }
    
    @Override
    public Item addItem(Item item) throws
            VendingMachinePersistenceException {
        Item addedItem = inventory.put(item.getName(), item);
        writeToFile();
        return addedItem;
    }
    
    @Override
    public Item getItemByName(String name) {
        return inventory.get(name);
    }

    @Override
    public Item decreaseItemQuantity(String name) throws VendingMachinePersistenceException {
        Item item = getItemByName(name);
        // TODO implement an ItemOutOfStockException if qty < 0
        int newQuantity = Math.max(0, item.getQuantity() - 1);
        item.setQuantity(newQuantity);
        writeToFile();
        return item;
    }

    @Override
    public List<Item> getItemsInStock() throws
            VendingMachinePersistenceException {
        loadFromFile();
        return inventory.values()
                .stream()
                .filter(i -> i.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllItems() throws
            VendingMachinePersistenceException {
        loadFromFile();
        return inventory.values()
                .stream()
                .collect(Collectors.toList());
    }
    
    private void loadFromFile() throws VendingMachinePersistenceException {
        Scanner sc;
        try {
            sc = new Scanner
                (new BufferedReader(
                    new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        
        String currentLine;
        String[] currentTokens;
        
        while(sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Item newItem = new Item();
            newItem.setName(currentTokens[0]);
            newItem.setPrice(new BigDecimal(currentTokens[1]).setScale(2, RoundingMode.HALF_UP));
            newItem.setQuantity(Integer.parseInt(currentTokens[2]));
            
            inventory.put(newItem.getName(), newItem);
        }
        sc.close();
    }
    
    private void writeToFile() throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch(IOException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        
        List<Item> items = this.getAllItems();
        for(Item item : items) {
            out.println(item.getName() + DELIMITER
                    + item.getPrice() + DELIMITER
                    + item.getQuantity() + DELIMITER + "END");
            out.flush();
        }
        out.close();
    }
}
