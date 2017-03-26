/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.martin.jInventary.model.TMProducts;

/**
 *
 * @author martin
 */
public class TSearcher extends Thread{
    private final String filter;
    private final JTable tblSearch;
    
    public TSearcher(String filter, JTable tblSearch) {
        this.filter = filter;
        this.tblSearch = tblSearch;
    }

    public boolean isFinished(){
        return getState() == State.TERMINATED;
    }
    
    @Override
    public void run(){
        try {
            Thread.sleep(500);
            if (filter != null)
                tblSearch.setModel(new TMProducts(filter));
            else
                tblSearch.setModel(new TMProducts());
            tblSearch.updateUI();
        } catch (InterruptedException ex) {
            Logger.getLogger(TSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
