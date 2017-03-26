/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.model;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.martin.jInventary.db.DbManager;
import org.martin.jInventary.gui.FormInventary;

/**
 *
 * @author martin
 */
public class TMProducts implements TableModel{
    private static DbManager dbManager;
    private LinkedList<ProductView> products;
    
    static{
        try {
            dbManager = new DbManager();
        } catch (SQLException ex) {
            Logger.getLogger(TMProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public TMProducts() {
        products = dbManager.getProducts();
    }
    
    public TMProducts(LinkedList<ProductView> listProducts){
        products = listProducts;
    }
    
    public TMProducts(String filterName) {
        products = dbManager.getProductsByName(filterName);
    }

    public LinkedList<ProductView> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<ProductView> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0: return "ID";
            case 1: return "Nombre";
            case 2: return "Tipo";
            case 3: return "Cantidad";
            default: return "Cantidad Mínima";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1 || columnIndex == 2)
            return String.class;
        else
            return Integer.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 3 || columnIndex == 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductView pv = products.get(rowIndex);
        
        switch(columnIndex){
            case 0: return pv.getId();
            case 1: return pv.getName();
            case 2: return pv.getType();
            case 3: return pv.getQuantity();
            default: return pv.getMinQuantity();
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int rowSelected = rowIndex;
        ProductView pvSelected = products.get(rowIndex);
        Product prodSelected = dbManager.getProductBy(pvSelected.getId());
        
        if (columnIndex == 1){
            prodSelected.setName(aValue.toString());
            pvSelected.setName(aValue.toString());
        }
        else if (columnIndex == 3){
            if (Integer.class.isInstance(aValue)) {
                int newQuantity = (int) aValue;
                prodSelected.setQuantity(newQuantity >= 0 ? newQuantity : 0);
                pvSelected.setMinQuantity(newQuantity >= 0 ? newQuantity : 0);
            }
        }
        else if (columnIndex == 4) {
            if (Integer.class.isInstance(aValue)) {
                int newMinQuantity = (int) aValue;
                prodSelected.setMinQuantity(newMinQuantity >= 0 ? newMinQuantity : 0);
                pvSelected.setMinQuantity(newMinQuantity >= 0 ? newMinQuantity : 0);
            }
        }
        
        products.set(rowIndex, pvSelected);
        dbManager.updateProduct(prodSelected);
        //FormInventary.getInstance().updateTableProds();
        FormInventary.getInstance().refreshTableProds();
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
}
