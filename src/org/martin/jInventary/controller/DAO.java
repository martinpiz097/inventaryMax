/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.martin.jInventary.db.DbConnection;

/**
 *
 * @author martin
 */
public abstract class DAO<T> implements DAOFactory<T>{
    private static final String DB_NAME;
    protected DbConnection connection;
    
    static{
        DB_NAME = "dbInventario";
    }
    
    public DAO() {
        try {
            connection = new DbConnection(DB_NAME);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
