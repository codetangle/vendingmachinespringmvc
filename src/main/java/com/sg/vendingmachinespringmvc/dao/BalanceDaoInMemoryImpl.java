/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import static java.math.RoundingMode.HALF_UP;

/**
 *
 * @author trevor
 */
public class BalanceDaoInMemoryImpl implements BalanceDao{

    private BigDecimal balance;
    
    
    public BalanceDaoInMemoryImpl() throws 
            VendingMachinePersistenceException {
        this.balance = new BigDecimal("0", new MathContext(9999999, HALF_UP));
    }
    
    @Override
    public BigDecimal getBalance() throws 
            VendingMachinePersistenceException {
        return this.balance;
    }
    
    @Override
    public void setBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException {
        this.balance = amount;
    }
}
