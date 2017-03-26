/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class DbConnection {
    private Connection con;

    public DbConnection(String dbName) throws SQLException{
        this("localhost", dbName, "root", "admin");
    }
    
    public DbConnection(String serverName, String dbName, String userName, String passw) throws SQLException {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName(serverName);
        datasource.setUser(userName);
        datasource.setPassword(passw);
        datasource.setDatabaseName(dbName);
        con = datasource.getConnection();
    }
    
    public void execQuery(String query) {
        try {
            con.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet execSelect(String query) {
        try {
            return con.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
