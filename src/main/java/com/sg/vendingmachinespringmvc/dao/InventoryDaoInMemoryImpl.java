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
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author trevor
 */
public class InventoryDaoInMemoryImpl implements InventoryDao {

    private Map<String, Item> inventory;
    
    public InventoryDaoInMemoryImpl() {
        this.inventory = new HashMap<>();
        addItems();
    }
    
    private void addItems() {
        Item item = new Item();
        item.setName("Mars Bar");
        item.setPrice(new BigDecimal("2.49"));
        item.setQuantity(0);
        this.inventory.put(item.getName(), item);
        
        Item item6 = new Item();
        item6.setName("Almond Joy");
        item6.setPrice(new BigDecimal("1.85"));
        item6.setQuantity(11);
        this.inventory.put(item6.getName(), item6);
        
        Item item5 = new Item();
        item5.setName("Payday");
        item5.setPrice(new BigDecimal("1.19"));
        item5.setQuantity(14);
        this.inventory.put(item5.getName(), item5);
        
        Item item4 = new Item();
        item4.setName("Peanuts");
        item4.setPrice(new BigDecimal("1.99"));
        item4.setQuantity(7);
        this.inventory.put(item4.getName(), item4);
        
        Item item3 = new Item();
        item3.setName("Mounds");
        item3.setPrice(new BigDecimal("1.45"));
        item3.setQuantity(10);
        this.inventory.put(item3.getName(), item3);
        
        Item item2 = new Item();
        item2.setName("Crunch Bar");
        item2.setPrice(new BigDecimal("1.39"));
        item2.setQuantity(9);
        this.inventory.put(item2.getName(), item2);
        
        Item item1 = new Item();
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.50"));
        item1.setQuantity(11);
        this.inventory.put(item1.getName(), item1);
    }
    
    @Override
    public Item addItem(Item item) throws
            VendingMachinePersistenceException {
        Item addedItem = inventory.put(item.getName(), item);
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
        return item;
    }

    @Override
    public List<Item> getItemsInStock() throws
            VendingMachinePersistenceException {
        return inventory.values()
                .stream()
                .filter(i -> i.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAllItems() throws
            VendingMachinePersistenceException {
        return inventory.values()
                .stream()
                .collect(Collectors.toList());
    }
}
