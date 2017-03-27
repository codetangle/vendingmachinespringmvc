/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.util.List;

/**
 *
 * @author trevor
 */
public interface InventoryDao {
    
    public Item addItem(Item item)throws
            VendingMachinePersistenceException;
    public Item getItemByName(String name);
    public Item decreaseItemQuantity(String name) throws
            VendingMachinePersistenceException;
    public List<Item> getItemsInStock()throws
            VendingMachinePersistenceException;;
    public List<Item> getAllItems()throws
            VendingMachinePersistenceException;;
}
