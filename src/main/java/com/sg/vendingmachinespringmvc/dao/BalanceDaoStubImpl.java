/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.math.BigDecimal;

/**
 *
 * @author trevor
 */
public class BalanceDaoStubImpl implements BalanceDao {

    private BigDecimal balance = new BigDecimal("1.50");
    
    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }
    
    @Override
    public void setBalance(BigDecimal amount) {
        this.balance = amount;
    }
    
    
}
