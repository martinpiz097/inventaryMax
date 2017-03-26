/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.martin.jInventary.model.Product;

/**
 *
 * @author martin
 */
public class ProductDAO extends DAO<Product>{
    
    private static final String TABLE_NAME;

    static{
        TABLE_NAME = Product.class.getSimpleName().toLowerCase();
    }
    
    public ProductDAO() {}
    
    @Override
    public void addElement(Product element) {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("insert into ").append(TABLE_NAME)
                .append(' ').append("values ")
                .append("('null', '")
                .append(element.getName())
                .append("', '")
                .append(element.getIdType())
                .append("', '")
                .append(element.getQuantity())
                .append("', '")
                .append(element.isEnabled())
                .append("')");
        connection.execQuery(sbQuery.toString());
    }

    @Override
    public List<Product> getElements() {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("select * from ").append(TABLE_NAME);
        ResultSet res = connection.execSelect(sbQuery.toString());
        sbQuery = null;
        List<Product> products = new LinkedList<>();
        
        try {
            while (res.next()) {
                products.add(new Product(res.getInt(1), res.getString(2), 
                        res.getByte(3), res.getInt(4), res.getInt(5),res.getBoolean(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public Product getElementById(Number id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getElementBy(String fieldName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getElementsBy(String fieldName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enableById(Number id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disableById(Number id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeById(Number id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateById(int id, Product newObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
