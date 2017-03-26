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
public class User {
    private final int id;
    private byte idType;
    private String name;
    private String nick;
    private String email;
    private String password;

    public User(byte idType, String name, String nick, String email, String password) {
        this(-1, idType, name, nick, email, password);
    }

    public User(int id, byte idType, String name, String nick, String email, String password) {
        this.id = id;
        this.idType = idType;
        this.name = name;
        this.nick = nick;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public byte getIdType() {
        return idType;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return nick;
    }
    
}
