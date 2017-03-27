/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author trevor
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> getInStockItems() throws 
            VendingMachinePersistenceException;
    public Item getItemByName(String name) throws 
            VendingMachinePersistenceException;
    public boolean isInStock(String name) throws
            ItemOutOfStockException;
    public boolean hasSufficientFunds(String name) throws 
            VendingMachinePersistenceException, 
            inSufficientFundsException;
    public Item vendItem(String name) throws
            VendingMachinePersistenceException, 
            inSufficientFundsException,
            ItemOutOfStockException;
    public BigDecimal getBalance() throws 
            VendingMachinePersistenceException;
    public void increaseBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException;
    public void decreaseBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException;
    public Change calcChange(BigDecimal balance) throws 
            VendingMachinePersistenceException;
    public void setBalanceToZero() throws
            VendingMachinePersistenceException;
//    public void getCurrentItem() throws
//            VendingMachinePersistenceException;
//    public void setCurrentItem() throws
//            VendingMachinePersistenceException;
//    public void getCurrentMessage() throws
//            VendingMachinePersistenceException;
//    public void setCurrentMessage() throws
//            VendingMachinePersistenceException;
}
