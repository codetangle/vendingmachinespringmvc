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
public interface BalanceDao {
    public BigDecimal getBalance() throws 
            VendingMachinePersistenceException;
    public void setBalance(BigDecimal amt) throws 
            VendingMachinePersistenceException;
}
