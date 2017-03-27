package com.sg.vendingmachinespringmvc.advice;

import com.sg.vendingmachinespringmvc.dao.AuditDao;
import com.sg.vendingmachinespringmvc.dao.BalanceDao;
import com.sg.vendingmachinespringmvc.dao.InventoryDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Item;
import org.aspectj.lang.JoinPoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trevor
 */
public class VendingMachineAdvice {
    InventoryDao inventory;
    BalanceDao balance;
    AuditDao audit;
    
    public VendingMachineAdvice(
            InventoryDao inventory,
            BalanceDao balance,
            AuditDao auditDao) {
        this.inventory = inventory;
        this.balance = balance;
        this.audit = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for(Object currentArgs : args) {
            auditEntry += currentArgs;
        }
        try {
            audit.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                "ERROR: Could not create audit entry in VendingMachineAdvice.");
        }
    }
    
    public void writeExceptionAudit(JoinPoint jp, Exception ex) throws 
            VendingMachinePersistenceException {
        
        // Get arguments from the joinPoint (method)
        Object[] args = jp.getArgs();
        
        Item item = inventory.getItemByName((String) args[0]);
        String auditEntry = jp.getSignature().getName() + ": ";
        auditEntry += ex.getClass().getSimpleName() + ": ";
        auditEntry += "balance: " + balance.getBalance() + ": ";
        auditEntry += "price: " + item.getPrice() + ": ";
        auditEntry += item.getName();
        try {
            audit.writeErrorEntry(auditEntry);
        } catch(VendingMachinePersistenceException e) {
            System.err.println(
                "ERROR: Could not create exception audit entry in VendingMachineAdvice");
        }
    }
}
