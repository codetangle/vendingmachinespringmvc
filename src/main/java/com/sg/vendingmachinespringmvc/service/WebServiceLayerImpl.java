/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.CurrentData;
import javax.inject.Inject;

/**
 *
 * @author trevor
 */
public class WebServiceLayerImpl implements WebServiceLayer {

    CurrentData data;
    
    @Inject
    public WebServiceLayerImpl(CurrentData data) {
        this.data = data;
    }
    @Override
    public String getMessage() {
        return this.data.getCurrentMessage();
    }

    @Override
    public String setMessage(String message) {
        return this.data.setCurrentMessage(message);
    }

    @Override
    public String getItem() {
        return this.data.getCurrentItem();
    }

    @Override
    public String setItem(String item) {
        return this.data.setCurrentItem(item);
    }
    
}
