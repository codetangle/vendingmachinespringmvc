/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import static java.math.RoundingMode.HALF_UP;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author trevor
 */
public class BalanceDaoFileImpl implements BalanceDao{

    private BigDecimal balance;
    private final String BALANCE_FILE;
    private static final String DELIMITER = "::";
    
    
//    public BalanceDaoFileImpl() throws 
//            VendingMachinePersistenceException {
//        this("testFile.txt");
//    }
    
    public BalanceDaoFileImpl(String fileName) throws 
            VendingMachinePersistenceException {
        this.balance = new BigDecimal("0", new MathContext(9999999, HALF_UP));
        this.BALANCE_FILE = fileName;
        try {
            loadBalanceFromFile();
        } catch (VendingMachinePersistenceException e) {
            e.getMessage();
        }
    }
    
    @Override
    public BigDecimal getBalance() throws 
            VendingMachinePersistenceException {
        loadBalanceFromFile();
        return this.balance;
    }
    
    @Override
    public void setBalance(BigDecimal amount) throws 
            VendingMachinePersistenceException {
        this.balance = amount;
        writeBalanceToFile();
    }
    
    private void loadBalanceFromFile() throws 
            VendingMachinePersistenceException {
        Scanner sc;
        String balanceString;
        try {
            sc = new Scanner
                    (new BufferedReader(
                            new FileReader(BALANCE_FILE)));
            balanceString = sc.nextLine();
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        } catch (NoSuchElementException e) {
            balanceString = "0.00";
        }
        
        
        this.balance = new BigDecimal(balanceString).setScale(2);
        
    }
    
    private void writeBalanceToFile() throws 
            VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(BALANCE_FILE));
        } catch(IOException e) {
            throw new VendingMachinePersistenceException(e.getMessage());
        }
        out.println(this.balance);
        out.flush();
        out.close();
    }
}
