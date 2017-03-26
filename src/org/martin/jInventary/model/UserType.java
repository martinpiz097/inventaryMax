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
public class UserType {
    private final byte id;
    private String name;

    public UserType(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserType(String name) {
        this((byte)-1, name);
    }

    public byte getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    
}
