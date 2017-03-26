/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author martin
 */
public class TCRProduct implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel lblContent = new JLabel();
        lblContent.setText(value.toString());
        TMProducts tblModel = (TMProducts) table.getModel();
        ProductView selected = tblModel.getProducts().get(row);
        if (selected.hasSufficientStock())
            lblContent.setBackground(Color.YELLOW);
        else
            lblContent.setBackground(Color.RED);
        lblContent.setOpaque(true);
        lblContent.setVisible(true);
        return lblContent;
    }
    
}
