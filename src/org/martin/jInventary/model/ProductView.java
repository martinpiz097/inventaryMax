/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.model;

/**
 *
 * @author martin
 */
public class ProductView {
    private final int id;
    private String name;
    private String type;
    private int quantity;
    private int minQuantity;
    
    public ProductView(String name, String type, int quantity, int minQuantity) {
        this(-1, name, type, quantity, minQuantity);
    }

    public ProductView(int id, String name, String type, int quantity, int minQuantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

     public boolean hasStock(){
        return quantity > 0;
    }
    
    public boolean hasSufficientStock(){
        return quantity >= minQuantity;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
    
}
