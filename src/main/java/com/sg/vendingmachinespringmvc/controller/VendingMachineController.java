/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.service.ItemOutOfStockException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import com.sg.vendingmachinespringmvc.service.inSufficientFundsException;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author trevor
 */
@Controller
@RequestMapping(value="/vendingMachine")
public class VendingMachineController {
    
    VendingMachineServiceLayer service;
    
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/addChange", method=RequestMethod.GET)
    public String addChange(HttpServletRequest request, 
            Model model,
            @RequestParam("amount") String amountPassed) 
            throws 
            VendingMachinePersistenceException {
        BigDecimal amount = new BigDecimal(amountPassed);
        service.increaseBalance(amount);
        
        return "redirect:/";
    }
    
    @RequestMapping(value="/vendItem", method=RequestMethod.GET)
    public String vendItemNoSelection(RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("error", "No Selection");
        return "redirect:/";
    }
    
    @RequestMapping(value="/vendItem/{item}", method=RequestMethod.GET)
    public String vendItem(Model model, 
            @PathVariable("item") String item,
            RedirectAttributes redirectAttrs) 
            throws 
            VendingMachinePersistenceException, 
            inSufficientFundsException,
            ItemOutOfStockException {
        try {
            service.vendItem(item);
        } catch(inSufficientFundsException e) {
            redirectAttrs.addFlashAttribute("error", "Insufficient Funds");
            return "redirect:/";
        } catch(ItemOutOfStockException e) {
            redirectAttrs.addFlashAttribute("error", "Out of Stock");
            return "redirect:/";
        } catch(Exception e) {
            redirectAttrs.addFlashAttribute("error", "There was an error");
            return "redirect:/";
        }
        
        redirectAttrs.addFlashAttribute("success", "Enjoy Your " + HtmlUtils.htmlEscape(item));
        return "redirect:/";
    }
    
    @RequestMapping(value="/getChange", method=RequestMethod.GET)
    public String getChange(RedirectAttributes redirectAttrs) throws 
            VendingMachinePersistenceException {
        service.setBalanceToZero();
        redirectAttrs.addAttribute("success", "Please Take Your Change");
        return "redirect:/";
    }
}
