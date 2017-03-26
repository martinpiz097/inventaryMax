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
public class Product {
    private final int id;
    private String name;
    private byte idType;
    private int quantity;
    private int minQuantity;
    private boolean enabled;

    public Product(String name, byte idType, int quantity, int minQuantity) {
        this(name, idType, quantity, minQuantity, true);
    }

    public Product(String name, byte idType, int quantity, int minQuantity, boolean enabled) {
        this(-1, name, idType, quantity, minQuantity, enabled);
    }

    public Product(int id, String name, byte idType, int quantity, int minQuantity, boolean enabled) {
        this.id = id;
        this.name = name;
        this.idType = idType;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.enabled = enabled;
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

    public byte getIdType() {
        return idType;
    }

    public void setIdType(byte idType) {
        this.idType = idType;
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

    public boolean isEnabled() {
        return enabled;
    }

    private void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void enable(){
        setEnabled(true);
    }
    
    public void disable(){
        setEnabled(false);
    }
    
}
