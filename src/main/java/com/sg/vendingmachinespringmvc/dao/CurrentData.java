/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

/**
 *
 * @author trevor
 */
public interface CurrentData {
    
    public String getCurrentItem();
    public String setCurrentItem(String item);
    public String getCurrentMessage();
    public String setCurrentMessage(String message);
}
