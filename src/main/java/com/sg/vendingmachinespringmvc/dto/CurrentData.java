/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

/**
 *
 * @author trevor
 */
public class CurrentData {
    
    private String currentMessage;
    private String currentSelection;

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(String currentSelection) {
        this.currentSelection = currentSelection;
    }
}
