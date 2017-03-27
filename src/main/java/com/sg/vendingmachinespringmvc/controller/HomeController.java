/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import com.sg.vendingmachinespringmvc.service.WebServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author trevor
 */
@Controller
public class HomeController {
    
    private VendingMachineServiceLayer service;
    private WebServiceLayer webService;
    
    @Inject
    public HomeController(VendingMachineServiceLayer service, WebServiceLayer webService) {
        this.service = service;
        this.webService = webService;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String homePage(HttpServletRequest request, 
            Model model,
            @ModelAttribute("error") String error,
            @ModelAttribute("success") String success) 
            throws 
            VendingMachinePersistenceException {
        
        List<Item> items = service.getInStockItems();
        BigDecimal balance = service.getBalance();
        Change change = service.calcChange(balance);
        
        String currentMessage = webService.getMessage();
        model.addAttribute("currentMessage", currentMessage);
        
        String itemName = request.getParameter("name");
        if(itemName != null && !itemName.isEmpty()) {
            model.addAttribute("currentMessage", "You've selected " + itemName);
            model.addAttribute("itemName", itemName);
        }
        
        if(!"".equalsIgnoreCase(error)) {
            model.addAttribute("currentMessage", "ERROR: " + error);
        }
        
        if(!"".equalsIgnoreCase(success)) {
            model.addAttribute("currentMessage", success);
        }
        
        // Set initial Value for currentMessage
        model.addAttribute("change", change);
        model.addAttribute("items", items);
        model.addAttribute("balance", balance);
        return "index";
    }
}
