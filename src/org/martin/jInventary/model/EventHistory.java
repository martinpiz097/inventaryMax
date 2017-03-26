/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.model;

import java.sql.Timestamp;

/**
 *
 * @author martin
 */
public class EventHistory {
    private final int id;
    private int idType;
    private Timestamp datetime;

    public EventHistory(int idType, Timestamp datetime) {
        this(-1, idType, datetime);
    }

    public EventHistory(int id, int idType, Timestamp datetime) {
        this.id = id;
        this.idType = idType;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public int getIdType() {
        return idType;
    }

    public Timestamp getDatetime() {
        return datetime;
    }
    
}
