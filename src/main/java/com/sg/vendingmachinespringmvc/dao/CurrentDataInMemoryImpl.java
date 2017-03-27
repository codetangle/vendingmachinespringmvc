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
public class CurrentDataInMemoryImpl implements CurrentData {

    private String currentMessage;
    private String currentItem;

    public CurrentDataInMemoryImpl() {
        this.currentMessage = "Make Selection";
    }
    
    @Override
    public String getCurrentItem() {
        return this.currentItem;
    }

    @Override
    public String setCurrentItem(String item) {
        this.currentItem = item;
        return this.currentItem;
    }

    @Override
    public String getCurrentMessage() {
        return this.currentMessage;
    }

    @Override
    public String setCurrentMessage(String message) {
        this.currentMessage = message;
        return this.currentMessage;
    }
    
}
