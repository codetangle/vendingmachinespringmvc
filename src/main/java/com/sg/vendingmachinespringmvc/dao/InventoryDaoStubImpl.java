/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author trevor
 */
public class InventoryDaoStubImpl implements InventoryDao {

    private HashMap<String, Item> inventory = new HashMap<>();
    private Item inStockItem;
    private Item outOfStockItem;
    private Item stockItem;
    
    public InventoryDaoStubImpl() {
        inStockItem = new Item();
        inStockItem.setName("Candy");
        inStockItem.setPrice(new BigDecimal("1.25"));
        inStockItem.setQuantity(99);
        
        outOfStockItem = new Item();
        outOfStockItem.setName("No Candy");
        outOfStockItem.setPrice(new BigDecimal("1.75")); // Over balance
        outOfStockItem.setQuantity(0);
        
        stockItem = new Item();
        stockItem.setName("No Candy");
        stockItem.setPrice(new BigDecimal("1.75")); // Over balance
        stockItem.setQuantity(1);
        
        inventory.put(inStockItem.getName(), inStockItem);
        inventory.put(outOfStockItem.getName(), outOfStockItem);
    }
    
    @Override
    public Item addItem(Item item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItemByName(String name) {
        if(name.equals(inStockItem.getName())) {
            return inStockItem;
        } else if (name.equals(outOfStockItem.getName())) {
            return outOfStockItem;
        } else if (name.equals(stockItem.getName())) {
            return stockItem;
        } else {
            return null;
        }
    }

    @Override
    public Item decreaseItemQuantity(String name) {
        Item item = getItemByName(name);
        int quantity = item.getQuantity();
        item.setQuantity(quantity -1);
        return item;
    }

    @Override
    public List<Item> getItemsInStock() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() {
        return inventory.values()
                .stream()
                .collect(Collectors.toList());
    }
    
}
