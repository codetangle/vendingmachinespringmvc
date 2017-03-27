/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.BalanceDao;
import com.sg.vendingmachinespringmvc.dao.InventoryDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author trevor
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private InventoryDao inventoryDao;
    private BalanceDao balanceDao;

    public VendingMachineServiceLayerImpl(InventoryDao inventoryDao,
            BalanceDao balanceDao) throws 
            VendingMachinePersistenceException {
        this.inventoryDao = inventoryDao;
        this.balanceDao = balanceDao;
    }

    // TODO write test for this.
    @Override 
    public List<Item> getInStockItems() throws 
            VendingMachinePersistenceException {
        return inventoryDao.getItemsInStock();
    }
    
    @Override
    public Item getItemByName(String name) throws 
            VendingMachinePersistenceException {
        return inventoryDao.getItemByName(name);
    }
    
    @Override
    public boolean isInStock(String name) throws
            ItemOutOfStockException {
        Item item = inventoryDao.getItemByName(name);
        if (item != null) {
            return item.getQuantity() > 0;
        }
        return false;
    }

    @Override
    public boolean hasSufficientFunds(String name) throws 
            VendingMachinePersistenceException,
            inSufficientFundsException {
        BigDecimal bal = this.balanceDao.getBalance();
        Item item = inventoryDao.getItemByName(name);
        
        if(bal.compareTo(item.getPrice()) >= 0) {
            return true;
        } else {
            // throw new inSufficientFundsException("Insert more money to make purcase.");
            return false;
        }
    }

    @Override
    public Item vendItem(String name) throws
            VendingMachinePersistenceException,
            inSufficientFundsException,
            ItemOutOfStockException {
        // Item item = null;
        if(!hasSufficientFunds(name)) {
            throw new inSufficientFundsException("Insert more money to make purchase");
        }
        if (!isInStock(name)) {
            throw new ItemOutOfStockException("Item is out of stock.");
        }
        // TODO Implement OutOfStock Exception
        Item item = inventoryDao.decreaseItemQuantity(name);
        
        // Decrement balance
        decreaseBalance(item.getPrice());
        
        return item;
    }
    
    @Override
    public BigDecimal getBalance() throws 
            VendingMachinePersistenceException {
        return balanceDao.getBalance();
    }

    @Override
    public void increaseBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException {
//        MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
        BigDecimal currentBalance = balanceDao.getBalance();
        BigDecimal newBalance = currentBalance.add(amount).setScale(2, RoundingMode.HALF_UP);
        this.balanceDao.setBalance(newBalance);
    }

    @Override
    public void decreaseBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException {
        BigDecimal currentBalance = balanceDao.getBalance();
        BigDecimal newBalance = currentBalance.subtract(amount).setScale(2);
        this.balanceDao.setBalance(newBalance);
    }

    @Override
    public Change calcChange(BigDecimal remainingBalance) throws 
            VendingMachinePersistenceException {
        BigDecimal convertToInt = remainingBalance.multiply(new BigDecimal("100"));
        int numPennies = convertToInt.intValue();
        
        Change change = new Change();
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;
        final int PENNY = 1;
        
        while(numPennies >= QUARTER) {
            change.setQuarter(change.getQuarter() + 1);
            numPennies -= QUARTER;
        }
        
        while(numPennies >= DIME) {
            change.setDime(change.getDime() + 1);
            numPennies -= DIME;
        }
        
        while(numPennies >= NICKEL) {
            change.setNickel(change.getNickel() + 1);
            numPennies -= NICKEL;
        }
        
        while(numPennies >= PENNY) {
            change.setPenny(change.getPenny() + 1);
            numPennies -= PENNY;
        }
        
        return change;
    }
    
    @Override
    public void setBalanceToZero() throws VendingMachinePersistenceException {
        balanceDao.setBalance(BigDecimal.ZERO);
    }

//    @Override
//    public void getCurrentItem() throws VendingMachinePersistenceException {
//        
//    }
//
//    @Override
//    public void setCurrentItem() throws VendingMachinePersistenceException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void getCurrentMessage() throws VendingMachinePersistenceException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void setCurrentMessage() throws VendingMachinePersistenceException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
