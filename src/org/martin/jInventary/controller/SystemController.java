/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class SystemController {
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    private static final String DEFAULT_PATH_FILE = OS_NAME.equals("linux") ? 
            null : "C:/";
    private static final Properties PROPERS = new Properties();
    private static final File FILE_PROPER = new  File(DEFAULT_PATH_FILE, "autologin.xml");
    static{
        loadFile();
    }
    
    private static void loadFile(){
        try{
            if (FILE_PROPER.exists())
                PROPERS.loadFromXML(new FileInputStream(FILE_PROPER));
            else{
                FILE_PROPER.createNewFile();
                updateFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void updateFile() {
        try {
            PROPERS.storeToXML(new FileOutputStream(FILE_PROPER), "Configuración básica del software");
        } catch (IOException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isAutolog(){
        Object autolog = PROPERS.get("autolog");
        return autolog != null ? Boolean.valueOf(String.valueOf(autolog)) : false;
    }
    
    public static void setAutologin(boolean autolog){
        PROPERS.setProperty("autolog", String.valueOf(autolog));
        updateFile();
    }
    
    public static String getUser(){
        return PROPERS.getProperty("user");
    }
    
    public static void setUser(String user){
        PROPERS.setProperty("user", user);
        updateFile();
    }
    
    public static String getPassword(){
        return PROPERS.getProperty("passw");
    }
    
    public static void setPassword(String passw){
        PROPERS.setProperty("passw", passw);
        updateFile();
    }
  
    public static void deleteAll(){
        try {
            FILE_PROPER.createNewFile();
            updateFile();
        } catch (IOException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
