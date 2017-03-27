/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author trevor
 */
public class AuditDaoFileImpl implements AuditDao {

    private final String AUDIT_FILE;
    private final String ERROR_FILE;
    
    public AuditDaoFileImpl(String auditFile, String errorFile) {
        this.AUDIT_FILE = auditFile;
        this.ERROR_FILE = errorFile;
    }
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not persist audit informatoin", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
    @Override
    public void writeErrorEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ERROR_FILE, true));
        } catch(IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not persist error informatoin", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
