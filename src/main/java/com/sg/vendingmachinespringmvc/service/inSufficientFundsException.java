/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author trevor
 */
public class inSufficientFundsException extends Exception {
    public inSufficientFundsException(String message) {
        super(message);
    }
    
    public inSufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
