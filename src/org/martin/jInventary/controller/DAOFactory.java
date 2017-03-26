/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.util.List;

/**
 *
 * @author martin
 */
public interface DAOFactory<T> {
    public abstract void addElement(T element);
    public abstract List<T> getElements();
    public abstract T getElementById(Number id);
    public abstract T getElementBy(String fieldName);
    public abstract List<T> getElementsBy(String fieldName);
    public abstract void enableById(Number id);
    public abstract void disableById(Number id);
    public abstract void removeById(Number id);
    public abstract void updateById(int id, T newObject);
    
}
