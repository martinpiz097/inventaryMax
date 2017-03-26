/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author martin
 */
public class Clock extends Thread{
    private final JLabel lblClock;
    private final JLabel lblDate;
    private Calendar cal;
    private final StringBuilder sbTime;
    private String currentDate;
    private static Clock clock;
    
    public static void newInstance(JLabel lblClock, JLabel lblDate){
        clock = new Clock(lblClock, lblDate);
    }
    
    public static Clock getInstance(){
        return clock;
    }
    
    public Clock(JLabel lblClock, JLabel lblDate) {
        this.lblClock = lblClock;
        this.lblDate = lblDate;
        sbTime = new StringBuilder();
        currentDate = getDateAsString();
    }

    private void clearSBTime(){
        if (sbTime.length() > 0)
            sbTime.delete(0, sbTime.length());
    }
    
    public String getTimeAsString(){
        clearSBTime();
        cal = new GregorianCalendar();
        byte hours = (byte) cal.get(Calendar.HOUR_OF_DAY);
        byte minutes = (byte) cal.get(Calendar.MINUTE);
        byte seconds = (byte) cal.get(Calendar.SECOND);
    
        sbTime.append("Hora: ");
        sbTime.append(hours < 10 ? '0' + hours : hours);
        sbTime.append(':');
        sbTime.append(minutes < 10 ? '0' + minutes : minutes);
        sbTime.append(':');
        sbTime.append(seconds < 10 ? '0' + seconds : seconds);
        return sbTime.toString();
    }
    
    public String getDateAsString(){
        StringBuilder sbDate = new StringBuilder();
        Calendar calendar = new GregorianCalendar();
        byte day = (byte) calendar.get(Calendar.DAY_OF_MONTH);
        byte month = (byte) ((byte) (calendar.get(Calendar.MONTH))+1);
        short year = (short) calendar.get(Calendar.YEAR);
        
        sbDate.append(day < 10 ? '0' + day : day);
        sbDate.append('/');
        sbDate.append(month < 10 ? '0'+month : month);
        sbDate.append('/');
        sbDate.append(year);
        System.out.println(sbDate.toString());
        return sbDate.toString();
    }
    
    @Override
    public void run(){
        long counter = 0;
        while (true) {  
            try {
                counter++;
                if (counter >= 2 && counter % 2 == 0){
                    lblClock.setText(getTimeAsString());
                    if (!getDateAsString().equals(currentDate))
                        lblDate.setText("Fecha: "+getDateAsString());
                }
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
