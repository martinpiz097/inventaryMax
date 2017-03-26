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
public class ProductType {
    private byte id;
    private String name;
    private boolean enabled;
    
    public ProductType(byte id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

    public ProductType(byte id, String name) {
        this(id, name, true);
    }

    public ProductType(String name, boolean enabled) {
        this((byte)-1, name, enabled);
    }

    public ProductType(String name) {
        this((byte)-1, name, true);
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
