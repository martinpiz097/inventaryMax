/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.jInventary.gui;

import com.mxrck.autocompleter.TextAutoCompleter;
import com.sun.glass.events.KeyEvent;
import java.awt.Dimension;
import java.awt.Window;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import org.martin.jInventary.controller.Clock;
import org.martin.jInventary.controller.SystemController;
import org.martin.jInventary.controller.TSearcher;
import org.martin.jInventary.db.DbManager;
import org.martin.jInventary.model.Product;
import org.martin.jInventary.model.ProductType;
import org.martin.jInventary.model.ProductView;
import org.martin.jInventary.model.TMProducts;
import org.martin.jInventary.model.User;
import org.martin.jInventary.model.UserType;

/**
 *
 * @author martin
 */
public class FormInventary extends javax.swing.JFrame {

    public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    Clock clock;
    DbManager dbManager;
    User myUser;
    TSearcher tSearcher;
    TextAutoCompleter recepCompleter;
    TextAutoCompleter consumCompleter;
    LinkedList<String> listTest = new LinkedList<>();
    private static FormInventary formInventary;
    
    public static void newInstance(){
        formInventary = new FormInventary();
    }
    
    public static FormInventary getInstance(){
        return formInventary;
    }
    
    public FormInventary() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        formMenu.setSize(610, 360);
        formMenu.setLocationRelativeTo(null);
        formMenu.setResizable(false);
        resize(formAdd, null);
        formAdd.setResizable(false);
        resize(formList, null);
        formList.setMaximumSize(new Dimension(formList.getPreferredSize().width, 768));
        try {
            dbManager = new DbManager();
        } catch (SQLException ex) {
            Logger.getLogger(FormInventary.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*tblProducts.setRowHeight(23);
        tblProducts.getColumnModel().getColumn(0).setMinWidth(34);
        tblProducts.getColumnModel().getColumn(0).setMaxWidth(34);
        tblProducts.getColumnModel().getColumn(0).setWidth(34);
        tblProducts.getColumnModel().getColumn(0).setPreferredWidth(34);
        tblProducts.getColumnModel().getColumn(0).setResizable(false);
        tblProducts.getColumnModel().getColumn(3).setMinWidth(68);
        tblProducts.getColumnModel().getColumn(3).setMaxWidth(68);
        tblProducts.getColumnModel().getColumn(3).setWidth(68);
        tblProducts.getColumnModel().getColumn(3).setPreferredWidth(68);
        tblProducts.getColumnModel().getColumn(3).setResizable(false);
        tblProducts.updateUI();*/
    
        dialogProdTypeEdit.setSize(dialogProdTypeEdit.getPreferredSize());
        dialogProdTypeEdit.setLocationRelativeTo(null);
        /*
            -1 --> Prefijo
            0 --> infijo
            1 --> sufijo
        */
        recepCompleter = new TextAutoCompleter(txtProdRec);
        recepCompleter.setMode(0);
        consumCompleter = new TextAutoCompleter(txtProdCons);
        consumCompleter.setMode(0);
        
        formEditUsers.setSize(400, 389);
        formEditUsers.setLocationRelativeTo(null);
        formManageType.setSize(626, 283);
        formManageType.setLocationRelativeTo(null);
        formManageType.setResizable(false);
        if (SystemController.isAutolog()) {
            myUser = dbManager.getUserByNickAndPassw(SystemController.getUser(), 
                    SystemController.getPassword());
            formMenu.setVisible(true);
            lblUserName.setText("Usuario: "+myUser.getName());
        }
        else
            setVisible(true);
    }
    
    private void searchProduct(){
        txtNamePSearch.setEnabled(false);
        String nameFilter = txtNamePSearch.getText().trim();
        boolean hasTypeFilter = chkFilterType.isSelected();
        LinkedList<ProductView> listProds;
        LinkedList<ProductView> listProdsFinal = new LinkedList<>();
        
        if (nameFilter == null)
            listProds = dbManager.getProducts();
        
        else
            listProds = dbManager.getProductsByName(nameFilter);
        if (hasTypeFilter) {
            listProds.stream().filter(p->p.getType().equals(cboFilterType
                    .getSelectedItem().toString()))
                    .forEach(listProdsFinal::add);
            listProds = listProdsFinal;
        }
        updateTableProds(listProds);
        txtNamePSearch.setEnabled(true);
        chkFilterType.setSelected(false);
    }
    
    private void openAndResize(Window win, Window parent){
        resize(win, parent);
        win.setVisible(true);
    }
    
    private void resize(Window win, Window parent){
        win.setSize(win.getPreferredSize());
        win.setLocationRelativeTo(parent);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formMenu = new javax.swing.JFrame();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblUserName = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        tabMain = new javax.swing.JTabbedPane();
        panelMain = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        panelRecStock = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        spinCantRec = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        txtProdRec = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnRecepcionar = new javax.swing.JButton();
        panelConsumo = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        spinCantCons = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        txtProdCons = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnRecepcionar1 = new javax.swing.JButton();
        lblAvailableStock = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemSalir = new javax.swing.JMenuItem();
        itemDeshStart = new javax.swing.JMenu();
        itemHabTipo = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        itemDesAutoStart = new javax.swing.JMenuItem();
        formAdd = new javax.swing.JFrame();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtNameNewP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboTipoNewP = new javax.swing.JComboBox<>();
        btnAddProducto = new javax.swing.JButton();
        spinCantNewP = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spinCantMinNewP1 = new javax.swing.JSpinner();
        formList = new javax.swing.JFrame();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtNamePSearch = new javax.swing.JTextField();
        btnSearchProduct = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        cboFilterType = new javax.swing.JComboBox<>();
        chkFilterType = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnCreateProd = new javax.swing.JButton();
        btnOrderAZ = new javax.swing.JButton();
        btnOrderZA = new javax.swing.JButton();
        btnUpdateTblProds = new javax.swing.JButton();
        btnUrgentProducts = new javax.swing.JButton();
        btnOrderQuantityAsc = new javax.swing.JButton();
        btnOrderQuantityDesc = new javax.swing.JButton();
        btnDelProduct = new javax.swing.JButton();
        btnUrgentProducts1 = new javax.swing.JButton();
        dialogProdTypeEdit = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cboProdType = new javax.swing.JComboBox<>();
        btnChangeProdType = new javax.swing.JButton();
        formEditUsers = new javax.swing.JFrame();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cboUserEdit = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        cboTipoUser = new javax.swing.JComboBox<>();
        txtNameUser = new javax.swing.JTextField();
        txtNickUser = new javax.swing.JTextField();
        txtEmailUser = new javax.swing.JTextField();
        txtPassUser = new javax.swing.JPasswordField();
        txtRepPassUser = new javax.swing.JPasswordField();
        btnEditUser = new javax.swing.JButton();
        formManageType = new javax.swing.JFrame();
        jLabel28 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtTypeName = new javax.swing.JTextField();
        btnRegType = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cboEditType = new javax.swing.JComboBox<>();
        btnLoadType = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        txtEditTypeName = new javax.swing.JTextField();
        btnEditType = new javax.swing.JButton();
        btnCancelEdit = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cboDelType = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPassw = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        chkSesionPermanent = new javax.swing.JCheckBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        formMenu.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Menú Principal");

        lblUserName.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblUserName.setText("Bienvenido sr ");

        tabMain.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabMain.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N

        jPanel6.setLayout(new java.awt.GridLayout(2, 2));

        jButton3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/list64x64.png"))); // NOI18N
        jButton3.setText("Ver Stock");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3);

        jButton8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/user_manage.png"))); // NOI18N
        jButton8.setText("Gestionar Tipos");
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8);

        jButton9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/manageUsers64x64.png"))); // NOI18N
        jButton9.setText("Gestionar Usuarios");
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton9);

        jButton7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/exit63x64.png"))); // NOI18N
        jButton7.setText("Cerrar Sesión");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        tabMain.addTab("Menu Principal", panelMain);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        spinCantRec.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel15.setText("Producto a recepcionar: ");

        txtProdRec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdRecKeyReleased(evt);
            }
        });

        jLabel16.setText("Cantidad: ");

        btnRecepcionar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnRecepcionar.setText("Recepcionar");
        btnRecepcionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProdRec)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinCantRec, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRecepcionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 114, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtProdRec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinCantRec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecepcionar)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRecStockLayout = new javax.swing.GroupLayout(panelRecStock);
        panelRecStock.setLayout(panelRecStockLayout);
        panelRecStockLayout.setHorizontalGroup(
            panelRecStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        panelRecStockLayout.setVerticalGroup(
            panelRecStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecStockLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        tabMain.addTab("Recepción de Stock", panelRecStock);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        spinCantCons.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel17.setText("Producto a consumir: ");

        txtProdCons.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdConsKeyReleased(evt);
            }
        });

        jLabel18.setText("Cantidad: ");

        btnRecepcionar1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnRecepcionar1.setText("Consumir");
        btnRecepcionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionar1ActionPerformed(evt);
            }
        });

        lblAvailableStock.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblAvailableStock.setForeground(java.awt.Color.red);
        lblAvailableStock.setText("Disponible: ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProdCons)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btnRecepcionar1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(spinCantCons, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(lblAvailableStock, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtProdCons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinCantCons, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(lblAvailableStock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRecepcionar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelConsumoLayout = new javax.swing.GroupLayout(panelConsumo);
        panelConsumo.setLayout(panelConsumoLayout);
        panelConsumoLayout.setHorizontalGroup(
            panelConsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsumoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panelConsumoLayout.setVerticalGroup(
            panelConsumoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsumoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        tabMain.addTab("Consumo", panelConsumo);

        jMenu1.setText("Archivo");

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        itemDeshStart.setText("Herramientas administrativas");

        itemHabTipo.setText("Hab/Deshabil, Productos");
        itemDeshStart.add(itemHabTipo);

        jMenuItem5.setText("Hab/Deshabil, Tipos");
        itemDeshStart.add(jMenuItem5);

        itemDesAutoStart.setText("Deshabilitar Inicio Automático");
        itemDesAutoStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDesAutoStartActionPerformed(evt);
            }
        });
        itemDeshStart.add(itemDesAutoStart);

        jMenuBar1.add(itemDeshStart);

        formMenu.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout formMenuLayout = new javax.swing.GroupLayout(formMenu.getContentPane());
        formMenu.getContentPane().setLayout(formMenuLayout);
        formMenuLayout.setHorizontalGroup(
            formMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formMenuLayout.createSequentialGroup()
                        .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formMenuLayout.createSequentialGroup()
                        .addComponent(tabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        formMenuLayout.setVerticalGroup(
            formMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMain, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        formAdd.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formAddWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formAddWindowClosing(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Añadir Producto");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("Tipo: ");

        btnAddProducto.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnAddProducto.setText("Agregar");
        btnAddProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductoActionPerformed(evt);
            }
        });

        spinCantNewP.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel8.setText("Nombre: ");

        jLabel10.setText("Cantidad: ");

        jLabel13.setText("Cantidad Mínima: ");

        spinCantMinNewP1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNameNewP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinCantNewP, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboTipoNewP, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinCantMinNewP1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNameNewP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboTipoNewP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(spinCantNewP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(spinCantMinNewP1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddProducto)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout formAddLayout = new javax.swing.GroupLayout(formAdd.getContentPane());
        formAdd.getContentPane().setLayout(formAddLayout);
        formAddLayout.setHorizontalGroup(
            formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        formAddLayout.setVerticalGroup(
            formAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        formList.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formListWindowOpened(evt);
            }
        });
        formList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formListKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Stock Actual");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel11.setText("Buscar por nombre: ");

        txtNamePSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNamePSearchKeyReleased(evt);
            }
        });

        btnSearchProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/search32x32.png"))); // NOI18N
        btnSearchProduct.setToolTipText("Buscar Productos");
        btnSearchProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchProductActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel26.setText("¿Filtrar por tipo? ");

        cboFilterType.setEnabled(false);

        chkFilterType.setText("Si");
        chkFilterType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFilterTypeItemStateChanged(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel27.setText("Tipo: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamePSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkFilterType)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearchProduct)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNamePSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(cboFilterType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkFilterType)
                            .addComponent(jLabel27)))
                    .addComponent(btnSearchProduct))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Productos"));

        tblProducts.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Cantidad", "Cantidad Mínima"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductsMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblProductsMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);
        if (tblProducts.getColumnModel().getColumnCount() > 0) {
            tblProducts.getColumnModel().getColumn(0).setResizable(false);
            tblProducts.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblProducts.getColumnModel().getColumn(3).setResizable(false);
            tblProducts.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnCreateProd.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnCreateProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/add32x32.png"))); // NOI18N
        btnCreateProd.setToolTipText("Crear un nuevo producto");
        btnCreateProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProdActionPerformed(evt);
            }
        });

        btnOrderAZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/orderaz32x32.png"))); // NOI18N
        btnOrderAZ.setToolTipText("Ordenar alfabéticamente");
        btnOrderAZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderAZActionPerformed(evt);
            }
        });

        btnOrderZA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/orderza32x32.png"))); // NOI18N
        btnOrderZA.setToolTipText("Ordenar al revés(Z-A)");
        btnOrderZA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderZAActionPerformed(evt);
            }
        });

        btnUpdateTblProds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/refresh32x32.png"))); // NOI18N
        btnUpdateTblProds.setToolTipText("Actualizar");
        btnUpdateTblProds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTblProdsActionPerformed(evt);
            }
        });

        btnUrgentProducts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/urgent32x32.png"))); // NOI18N
        btnUrgentProducts.setToolTipText("Mostrar compras urgentes");
        btnUrgentProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUrgentProductsActionPerformed(evt);
            }
        });

        btnOrderQuantityAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/orderQuantity32x32Asc.png"))); // NOI18N
        btnOrderQuantityAsc.setToolTipText("Ordenar por cantidad ascendentemente");
        btnOrderQuantityAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderQuantityAscActionPerformed(evt);
            }
        });

        btnOrderQuantityDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/orderQuantity32x32Desc.png"))); // NOI18N
        btnOrderQuantityDesc.setToolTipText("Ordenar  por cantidad descendentemente");
        btnOrderQuantityDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderQuantityDescActionPerformed(evt);
            }
        });

        btnDelProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/delete32x32.png"))); // NOI18N
        btnDelProduct.setToolTipText("Eliminar");
        btnDelProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelProductActionPerformed(evt);
            }
        });

        btnUrgentProducts1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/goBack32x32.png"))); // NOI18N
        btnUrgentProducts1.setToolTipText("Regresar al menú");
        btnUrgentProducts1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUrgentProducts1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formListLayout = new javax.swing.GroupLayout(formList.getContentPane());
        formList.getContentPane().setLayout(formListLayout);
        formListLayout.setHorizontalGroup(
            formListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formListLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4))
                    .addGroup(formListLayout.createSequentialGroup()
                        .addGroup(formListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator3)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(formListLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnCreateProd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrderAZ, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrderZA, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrderQuantityAsc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrderQuantityDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateTblProds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelProduct)
                        .addGap(9, 9, 9)
                        .addComponent(btnUrgentProducts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUrgentProducts1)
                        .addContainerGap())))
        );
        formListLayout.setVerticalGroup(
            formListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formListLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrderAZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrderZA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdateTblProds, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUrgentProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrderQuantityAsc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrderQuantityDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUrgentProducts1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dialogProdTypeEdit.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogProdTypeEditWindowOpened(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel12.setText("Nuevo Tipo: ");

        btnChangeProdType.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnChangeProdType.setText("Cambiar");
        btnChangeProdType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeProdTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnChangeProdType, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(cboProdType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboProdType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChangeProdType)
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogProdTypeEditLayout = new javax.swing.GroupLayout(dialogProdTypeEdit.getContentPane());
        dialogProdTypeEdit.getContentPane().setLayout(dialogProdTypeEditLayout);
        dialogProdTypeEditLayout.setHorizontalGroup(
            dialogProdTypeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogProdTypeEditLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        dialogProdTypeEditLayout.setVerticalGroup(
            dialogProdTypeEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogProdTypeEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        formEditUsers.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formEditUsersWindowOpened(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Gestión de Usuarios");

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("Usuario a editar: ");

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboUserEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cboUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("Tipo: ");

        jLabel21.setText("Nombre: ");

        jLabel22.setText("Nick: ");

        jLabel23.setText("E-mail: ");

        jLabel24.setText("Contraseña: ");

        jLabel25.setText("Repita contraseña: ");

        cboTipoUser.setEnabled(false);

        txtNameUser.setEnabled(false);

        txtNickUser.setEnabled(false);

        txtEmailUser.setEnabled(false);

        txtPassUser.setText("jPasswordField1");
        txtPassUser.setEnabled(false);

        txtRepPassUser.setText("jPasswordField1");
        txtRepPassUser.setEnabled(false);

        btnEditUser.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnEditUser.setText("Finalizar Edición");
        btnEditUser.setEnabled(false);
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNickUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNameUser)
                    .addComponent(cboTipoUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmailUser)
                    .addComponent(txtPassUser)
                    .addComponent(txtRepPassUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cboTipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtNameUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNickUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtEmailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtPassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtRepPassUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout formEditUsersLayout = new javax.swing.GroupLayout(formEditUsers.getContentPane());
        formEditUsers.getContentPane().setLayout(formEditUsersLayout);
        formEditUsersLayout.setHorizontalGroup(
            formEditUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formEditUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formEditUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        formEditUsersLayout.setVerticalGroup(
            formEditUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formEditUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        formManageType.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formManageTypeWindowOpened(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Gestión de Tipos de Productos");

        jTabbedPane1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel29.setText("Nombre del nuevo tipo: ");

        txtTypeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTypeNameKeyReleased(evt);
            }
        });

        btnRegType.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnRegType.setText("Registrar");
        btnRegType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegType, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addComponent(txtTypeName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegType)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crear Tipos", jPanel11);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel30.setText("Tipo a editar: ");

        btnLoadType.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnLoadType.setText("Cargar");
        btnLoadType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadTypeActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel31.setText("Nombre: ");

        txtEditTypeName.setEnabled(false);
        txtEditTypeName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEditTypeNameKeyReleased(evt);
            }
        });

        btnEditType.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnEditType.setText("Finalizar Edición");
        btnEditType.setEnabled(false);
        btnEditType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTypeActionPerformed(evt);
            }
        });

        btnCancelEdit.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnCancelEdit.setText("Cancelar");
        btnCancelEdit.setEnabled(false);
        btnCancelEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboEditType, 0, 293, Short.MAX_VALUE)
                            .addComponent(txtEditTypeName))
                        .addGap(18, 18, 18)
                        .addComponent(btnLoadType, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnEditType, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cboEditType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoadType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtEditTypeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditType)
                    .addComponent(btnCancelEdit))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar Tipos", jPanel12);

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel32.setText("Tipo a eliminar: ");

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton2.setText("Eliminar Tipo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(cboDelType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cboDelType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eliminar Tipos", jPanel13);

        javax.swing.GroupLayout formManageTypeLayout = new javax.swing.GroupLayout(formManageType.getContentPane());
        formManageType.getContentPane().setLayout(formManageTypeLayout);
        formManageTypeLayout.setHorizontalGroup(
            formManageTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formManageTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formManageTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        formManageTypeLayout.setVerticalGroup(
            formManageTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formManageTypeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(5, 5, 5)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/jInventary/resources/login2.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel3.setText("Usuario: ");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("Contraseña: ");

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });

        txtPassw.setText("jPasswordField1");
        txtPassw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswKeyReleased(evt);
            }
        });

        btnIngresar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        chkSesionPermanent.setText("Mantener sesión iniciada");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUser)
                    .addComponent(txtPassw)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSesionPermanent))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassw, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSesionPermanent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIngresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator4)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login InventaryMax");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String user = txtUser.getText();
        String passw = txtPassw.getText();
        
        if (user.isEmpty() || passw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Al menos uno de los campos está vacío");
            txtUser.selectAll();
            txtUser.requestFocus();
        }
        else{
            myUser = dbManager.getUserByNickAndPassw(user, passw);
            boolean isRightLogin = myUser != null;
            if (isRightLogin) {
                SystemController.setAutologin(chkSesionPermanent.isSelected());
                SystemController.setUser(user);
                SystemController.setPassword(passw);
                //Clock.newInstance(lblTime, lblDate);
                //Clock.getInstance().start();
                lblUserName.setText("Usuario: "+myUser.getName());
                JOptionPane.showMessageDialog(null, "Bienvenido sr "+myUser.getName());
                formMenu.setVisible(true);
                txtUser.setText(null);
                txtPassw.setText(null);
                chkSesionPermanent.setSelected(false);
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
                txtUser.selectAll();
                txtUser.requestFocus();
            }
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPassw.selectAll();
            txtPassw.requestFocus();
        }
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPasswKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnIngresar.doClick();
    }//GEN-LAST:event_txtPasswKeyReleased

    private void formListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formListKeyReleased
        formList.requestFocus();
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            tblProducts.setModel(new TMProducts());
            tblProducts.updateUI();
        }
    }//GEN-LAST:event_formListKeyReleased

    private void formListWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formListWindowOpened
        LinkedList<ProductType> listProdTypes = dbManager.getAllTypes();
        for (ProductType type : listProdTypes) {
            cboFilterType.addItem(type);
        }
        updateTableProds();
    }//GEN-LAST:event_formListWindowOpened

    private void txtNamePSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamePSearchKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            searchProduct();
            txtNamePSearch.requestFocus();
        }
    }//GEN-LAST:event_txtNamePSearchKeyReleased

    private void tblProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseExited
        /*int width = tblProducts.getColumnModel().getColumn(3).getWidth();
        System.out.println(width);*/
    }//GEN-LAST:event_tblProductsMouseExited

    private void btnSearchProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchProductActionPerformed
        searchProduct();
    }//GEN-LAST:event_btnSearchProductActionPerformed

    private void btnOrderAZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderAZActionPerformed
        TMProducts tblProdModel = (TMProducts) tblProducts.getModel();
        LinkedList<ProductView> listProdTbl = tblProdModel.getProducts();
        Collections.sort(listProdTbl, 
                (ProductView o1, ProductView o2) -> 
                        o1.getName().compareToIgnoreCase(o2.getName()));
        tblProdModel.setProducts(listProdTbl);
        tblProducts.setModel(tblProdModel);
        tblProducts.updateUI();
    }//GEN-LAST:event_btnOrderAZActionPerformed

    private void btnOrderZAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderZAActionPerformed
        TMProducts tblProdModel = (TMProducts) tblProducts.getModel();
        LinkedList<ProductView> listProdTbl = tblProdModel.getProducts();
        Collections.sort(listProdTbl, 
                (ProductView o1, ProductView o2) -> 
                        o2.getName().compareToIgnoreCase(o1.getName()));
        tblProdModel.setProducts(listProdTbl);
        tblProducts.setModel(tblProdModel);
        tblProducts.updateUI();
    }//GEN-LAST:event_btnOrderZAActionPerformed

    private void formAddWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formAddWindowOpened
        updateCombobox(dbManager.getAllTypes(), cboTipoNewP);
        cboTipoNewP.setSelectedIndex(-1);
    }//GEN-LAST:event_formAddWindowOpened

    private void btnAddProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductoActionPerformed
        String nameNew = txtNameNewP.getText().trim();
        boolean existsProduct = dbManager.getProductBy(nameNew) != null;
        
        if (existsProduct) {
            JOptionPane.showMessageDialog(null, "El producto ya existe", "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            int quantity = (int) spinCantNewP.getValue();
            int minQuantity = (int) spinCantMinNewP1.getValue();
            byte type = ((ProductType)cboTipoNewP.getSelectedItem()).getId();
            Product newProd = new Product(nameNew, type, quantity, minQuantity);
            dbManager.addProduct(newProd);
            txtNameNewP.setText(null);
            cboTipoNewP.setSelectedIndex(-1);
            spinCantNewP.setValue(0);
            spinCantMinNewP1.setValue(0);
            updateTableProds();
            JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
            txtNameNewP.requestFocus();
        }
    }//GEN-LAST:event_btnAddProductoActionPerformed

    private void btnCreateProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProdActionPerformed
        formAdd.setVisible(true);
        formAdd.setLocationRelativeTo(formList);
    }//GEN-LAST:event_btnCreateProdActionPerformed

    private void btnUpdateTblProdsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTblProdsActionPerformed
        updateTableProds();
    }//GEN-LAST:event_btnUpdateTblProdsActionPerformed

    private void dialogProdTypeEditWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogProdTypeEditWindowOpened
        if (cboProdType.getItemCount() > 0) {
            cboProdType.removeAllItems();
        }
        LinkedList<ProductType> prodTypes = dbManager.getAllTypes();
        
        for (ProductType prodType : prodTypes) {
            cboProdType.addItem(prodType);
        }
    }//GEN-LAST:event_dialogProdTypeEditWindowOpened

    private void btnChangeProdTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeProdTypeActionPerformed
        int selectedRow = tblProducts.getSelectedRow();
        TMProducts tblModel = (TMProducts) tblProducts.getModel();
        int selectedId = tblModel.getProducts().get(selectedRow).getId();
        Product prodSelected = dbManager.getProductBy(selectedId);
        prodSelected.setIdType(((ProductType)cboProdType.getSelectedItem()).getId());
        dbManager.updateProduct(prodSelected);
        cboProdType.removeAllItems();
        cboProdType.setSelectedIndex(-1);
        dialogProdTypeEdit.setVisible(false);
        updateTableProds();
    }//GEN-LAST:event_btnChangeProdTypeActionPerformed

    private void tblProductsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseReleased
        if (evt.getClickCount() == 2 && tblProducts.getSelectedColumn() == 2)
            dialogProdTypeEdit.setVisible(true);
    }//GEN-LAST:event_tblProductsMouseReleased

    private void btnUrgentProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUrgentProductsActionPerformed
        TMProducts productsModel = (TMProducts) tblProducts.getModel();
        LinkedList<ProductView> listUrgents = dbManager.getUrgentProducts();
        productsModel.setProducts(listUrgents);
        tblProducts.setModel(productsModel);
        tblProducts.updateUI();
    }//GEN-LAST:event_btnUrgentProductsActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        byte idType = ((UserType)cboTipoUser.getSelectedItem()).getId();
        String userName = txtNameUser.getText().trim();
        String nick = txtNickUser.getText().trim();
        String email = txtEmailUser.getText().trim();
        String passw1 = txtPassUser.getText();
        String passw2 = txtRepPassUser.getText();
        
        if (!passw1.equals(passw2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            txtPassUser.requestFocus();
            txtPassUser.selectAll();
        }
        else if (userName.isEmpty() || nick.isEmpty() || email.isEmpty() || 
                passw1.isEmpty() || passw2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Uno de los campos está vacío");
            txtNameUser.requestFocus();
            txtNameUser.selectAll();
        }
        else{
            int idUser = ((User)cboUserEdit.getSelectedItem()).getId();
            User edited = new User(idUser, idType, userName, nick, email, passw1);
            dbManager.updateUser(edited);
            txtNameUser.setText(null);
            txtNickUser.setText(null);
            txtEmailUser.setText(null);
            txtPassUser.setText(null);
            txtRepPassUser.setText(null);
            JOptionPane.showMessageDialog(null, "Usuario editado con éxito");
            refreshWinEditUser();
            enableFields(false);
        }
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void formEditUsersWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formEditUsersWindowOpened
        refreshWinEditUser();
    }//GEN-LAST:event_formEditUsersWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if (cboUserEdit.getSelectedIndex() != -1) {
            enableFields(true);
            User selected = (User)cboUserEdit.getSelectedItem();
            for (int i = 0; i < cboTipoUser.getItemCount(); i++) {
                if (cboTipoUser.getItemAt(i).getId() == selected.getIdType()) {
                    cboTipoUser.setSelectedIndex(i);
                    break;
                }
            }
            
            txtEmailUser.setText(selected.getEmail());
            txtNameUser.setText(selected.getName());
            txtNickUser.setText(selected.getNick());
            txtPassUser.setText("jPasswordField");
            txtRepPassUser.setText("jPasswordField");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        JOptionPane.showMessageDialog(null, "Gracias por su preferencia");
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnRecepcionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionar1ActionPerformed
        String nameProd = txtProdCons.getText();
        int quantity = (int) spinCantCons.getValue();

        if (nameProd == null || nameProd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar algún producto para continuar");
        }

        else{
            boolean isConsumed = dbManager.consumeProduct(nameProd, quantity);

            if (isConsumed) {
                JOptionPane.showMessageDialog(null, "¡Productos Consumidos! El stock ha sido "
                    + "\nactualizado con éxito");
                txtProdCons.setText(null);
            }
            else if (dbManager.getProductBy(txtProdCons.getText()) == null) {
                JOptionPane.showMessageDialog(null, "No se ha podido consumir producto. "
                    + "El producto no existe");
            }
            else
            JOptionPane.showMessageDialog(null, "¡Stock Insuficiente!");
            lblAvailableStock.setText("Disponible: ");
            consumCompleter.removeAllItems();
            
        }
    }//GEN-LAST:event_btnRecepcionar1ActionPerformed

    private void txtProdConsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdConsKeyReleased
        consumCompleter.removeAllItems();
        LinkedList<String> listNames = dbManager.getProductNamesBy(txtProdCons.getText().trim());
        for (String prodName : listNames) {
            consumCompleter.addItem(prodName);
        }
        Product selected = dbManager.getProductBy(txtProdCons.getText());
        if (selected != null)
        lblAvailableStock.setText("Disponible: "+selected.getQuantity());

    }//GEN-LAST:event_txtProdConsKeyReleased

    private void btnRecepcionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionarActionPerformed
        String nameProd = txtProdRec.getText();
        int quantity = (int) spinCantRec.getValue();

        if (nameProd == null || nameProd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar algún producto para continuar");
        }

        else{
            boolean isReceived = dbManager.receiveProduct(nameProd, quantity);

            if (isReceived) {
                JOptionPane.showMessageDialog(null, "¡Productos Recepcionados! El stock ha sido "
                    + "\nactualizado con éxito");
                txtProdRec.setText(null);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se ha podido recepcionar producto. "
                    + "El producto no existe");
            }
            recepCompleter.removeAllItems();
        }
    }//GEN-LAST:event_btnRecepcionarActionPerformed

    private void txtProdRecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdRecKeyReleased
        recepCompleter.removeAllItems();
        LinkedList<String> listNames = dbManager.getProductNamesBy(txtProdRec.getText().trim());
        for (String prodName : listNames) {
            recepCompleter.addItem(prodName);
        }
    }//GEN-LAST:event_txtProdRecKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        myUser = null;
        txtUser.setText(null);
        txtPassw.setText(null);
        formMenu.setVisible(false);
        setVisible(true);
        lblUserName.setText("Usuario: ");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        formEditUsers.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        formManageType.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tblProducts.setModel(new TMProducts());
        formList.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnOrderQuantityAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderQuantityAscActionPerformed
        TMProducts tblModel = (TMProducts) tblProducts.getModel();
        LinkedList<ProductView> listProducts = tblModel.getProducts();
        listProducts.sort(new Comparator<ProductView>() {
            @Override
            public int compare(ProductView o1, ProductView o2) {
                if (o1.getQuantity() > o2.getQuantity())
                    return 1;
                else if (o1.getQuantity() == o2.getQuantity())
                    return 0;
                else
                    return -1;
            }
        });
        updateTableProds(listProducts);
    }//GEN-LAST:event_btnOrderQuantityAscActionPerformed

    private void btnOrderQuantityDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderQuantityDescActionPerformed
        TMProducts tblModel = (TMProducts) tblProducts.getModel();
        LinkedList<ProductView> listProducts = tblModel.getProducts();
        listProducts.sort(new Comparator<ProductView>() {
            @Override
            public int compare(ProductView o1, ProductView o2) {
                if (o1.getQuantity() < o2.getQuantity())
                    return 1;
                else if (o1.getQuantity() == o2.getQuantity())
                    return 0;
                else
                    return -1;
            }
        });
        updateTableProds(listProducts);
    }//GEN-LAST:event_btnOrderQuantityDescActionPerformed

    private void btnRegTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegTypeActionPerformed
        String typeName = txtTypeName.getText().trim();
        if (typeName == null || typeName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de texto está vacío");
            txtTypeName.requestFocus();
        }
        else if (dbManager.getTypeByName(typeName) != null) {
            JOptionPane.showMessageDialog(null, "El tipo de producto a registrar ya existe");
            txtTypeName.selectAll();
            txtTypeName.requestFocus();
        }
        else{
            dbManager.addType(typeName);
            JOptionPane.showMessageDialog(null, "¡Tipo registrado exitosamente!");
            typeName = null;
            txtTypeName.setText(null);
            txtTypeName.requestFocus();
            updateAllComboType();
        }
    }//GEN-LAST:event_btnRegTypeActionPerformed

    private void btnEditTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTypeActionPerformed
        String newTypeName = txtEditTypeName.getText().trim();
        if (txtEditTypeName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor escriba algún nombre");
            txtEditTypeName.requestFocus();
        }
        else{
            byte idType = ((ProductType)cboEditType.getSelectedItem()).getId();
            dbManager.updateType(new ProductType(idType, newTypeName));
            JOptionPane.showMessageDialog(null, "¡Tipo editado con éxito!");
            txtEditTypeName.setText(null);
            txtEditTypeName.setEnabled(false);
            btnEditType.setEnabled(false);
            cboEditType.setEnabled(true);
            btnCancelEdit.setEnabled(false);
            LinkedList<ProductType> listTypes = dbManager.getAllTypes();
            updateAllComboType();
        }
    }//GEN-LAST:event_btnEditTypeActionPerformed

    private void btnLoadTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadTypeActionPerformed
        cboEditType.setEnabled(false);
        btnLoadType.setEnabled(false);
        ProductType selected = (ProductType) cboEditType.getSelectedItem();
        txtEditTypeName.setText(selected.getName());
        txtEditTypeName.setEnabled(true);
        btnEditType.setEnabled(true);
        btnCancelEdit.setEnabled(true);
    }//GEN-LAST:event_btnLoadTypeActionPerformed

    private void btnCancelEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelEditActionPerformed
        cboEditType.setEnabled(true);
        btnLoadType.setEnabled(true);
        btnEditType.setEnabled(false);
        btnCancelEdit.setEnabled(false);
        txtEditTypeName.setEnabled(false);
        txtEditTypeName.setText(null);
    }//GEN-LAST:event_btnCancelEditActionPerformed

    private void formManageTypeWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formManageTypeWindowOpened
        LinkedList<ProductType> listTypes = dbManager.getAllTypes();
        updateCombobox(listTypes, cboEditType);
        updateCombobox(listTypes, cboDelType);
    }//GEN-LAST:event_formManageTypeWindowOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (cboDelType.getSelectedIndex() > -1) {
            dbManager.deleteType(((ProductType)cboDelType.getSelectedItem()).getId());
            JOptionPane.showMessageDialog(null, "¡Tipo eliminado!");
            LinkedList<ProductType> listTypes = dbManager.getAllTypes();
            updateAllComboType();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTypeNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTypeNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnRegType.doClick();
    }//GEN-LAST:event_txtTypeNameKeyReleased

    private void txtEditTypeNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditTypeNameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btnEditType.doClick();
    }//GEN-LAST:event_txtEditTypeNameKeyReleased

    private void chkFilterTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFilterTypeItemStateChanged
        cboFilterType.setEnabled(chkFilterType.isSelected());
    }//GEN-LAST:event_chkFilterTypeItemStateChanged

    private void itemDesAutoStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDesAutoStartActionPerformed
        if (SystemController.isAutolog()) {
            SystemController.deleteAll();
            JOptionPane.showMessageDialog(null, "¡Configuraciones de inicio de sesión eliminadas!");
        }
        else{
            JOptionPane.showMessageDialog(null, 
                    "No se ha habilitado el inicio de sesión automático.", 
                    "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_itemDesAutoStartActionPerformed

    private void btnDelProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelProductActionPerformed
        int option = JOptionPane.showConfirmDialog(null, 
                "¿Realmente desea eliminar el producto?", 
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            int selectedIndex = tblProducts.getSelectedRow();
            int selectedId = (int) ((TMProducts)tblProducts.getModel()).getValueAt(selectedIndex, 0);
            dbManager.deleteProductById(selectedId);
            JOptionPane.showMessageDialog(null, "¡Producto Eliminado!");
            updateTableProds();
        }
    }//GEN-LAST:event_btnDelProductActionPerformed

    private void formAddWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formAddWindowClosing
        txtNameNewP.setText(null);
        cboTipoNewP.setSelectedIndex(-1);
        spinCantNewP.setValue(0);
        spinCantMinNewP1.setValue(0);
    }//GEN-LAST:event_formAddWindowClosing

    private void btnUrgentProducts1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUrgentProducts1ActionPerformed
        txtNamePSearch.setText(null);
        formList.setVisible(false);
    }//GEN-LAST:event_btnUrgentProducts1ActionPerformed

    public void updateTableProds(){
        tblProducts.setModel(new TMProducts());
        /*TableColumnModel tcm = tblProducts.getColumnModel();
        for (int i = 0; i < tblProducts.getColumnCount(); i++)
            setColumnWidth(tcm.getColumn(0), i);
        tblProducts.setColumnModel(tcm);*/
        tblProducts.updateUI();
    }
    
    public void updateTableProds(LinkedList<ProductView> listProds){
        TMProducts tblModel = (TMProducts) tblProducts.getModel();
        tblModel.setProducts(listProds);
        tblProducts.setModel(tblModel);
        tblProducts.updateUI();
    }
    
    public void refreshTableProds(){
        tblProducts.updateUI();
    }
    
    public void enableFields(boolean enable){
        cboTipoUser.setEnabled(enable);
        txtNameUser.setEnabled(enable);
        txtNickUser.setEnabled(enable);
        txtEmailUser.setEnabled(enable);
        txtPassUser.setEnabled(enable);
        txtRepPassUser.setEnabled(enable);
        btnEditUser.setEnabled(enable);
    }
    
    public void refreshWinEditUser(){
        cboUserEdit.removeAllItems();
        cboTipoUser.removeAllItems();
        LinkedList<User> listNicks = dbManager.getUsers();
        for (User user : listNicks)
            cboUserEdit.addItem(user);
        listNicks = null;
        
        LinkedList<UserType> userTypes = dbManager.getUserTypes();
        for (UserType userType : userTypes)
            cboTipoUser.addItem(userType);
        userTypes = null;
        cboUserEdit.setSelectedIndex(-1);
        cboTipoUser.setSelectedIndex(-1);
    }
    
    public void setColumnWidth(TableColumn tc, int colIndex){
        int maxWidth = tblProducts.getColumnName(colIndex).length();
        int rowCount = tblProducts.getRowCount();
        int lenStr = 0;
        
        for (int row = 0; row < rowCount; row++) {
            lenStr = tblProducts.getValueAt(row, colIndex).toString().length();
            if (lenStr > maxWidth)
                maxWidth = lenStr;
        }
        tc.setWidth(maxWidth);
        tc.setPreferredWidth(maxWidth);
        tc.setMaxWidth(maxWidth);
    }
    
    public void updateCombobox(LinkedList objList, JComboBox combobox){
        combobox.removeAllItems();
        for (Object object : objList) {
            combobox.addItem(object);
        }
        combobox.setSelectedIndex(-1);
    }
    
    public void updateAllComboType(){
        LinkedList<ProductType> listTypes = dbManager.getAllTypes();
        updateCombobox(listTypes, cboEditType);
        updateCombobox(listTypes, cboDelType);
        updateCombobox(listTypes, cboFilterType);
        updateCombobox(listTypes, cboProdType);
        updateCombobox(listTypes, cboTipoNewP);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            if (OS_NAME.contains("windows"))
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            else
                javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | 
                InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FormInventary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            FormInventary.newInstance();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProducto;
    private javax.swing.JButton btnCancelEdit;
    private javax.swing.JButton btnChangeProdType;
    private javax.swing.JButton btnCreateProd;
    private javax.swing.JButton btnDelProduct;
    private javax.swing.JButton btnEditType;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnLoadType;
    private javax.swing.JButton btnOrderAZ;
    private javax.swing.JButton btnOrderQuantityAsc;
    private javax.swing.JButton btnOrderQuantityDesc;
    private javax.swing.JButton btnOrderZA;
    private javax.swing.JButton btnRecepcionar;
    private javax.swing.JButton btnRecepcionar1;
    private javax.swing.JButton btnRegType;
    private javax.swing.JButton btnSearchProduct;
    private javax.swing.JButton btnUpdateTblProds;
    private javax.swing.JButton btnUrgentProducts;
    private javax.swing.JButton btnUrgentProducts1;
    private javax.swing.JComboBox<ProductType> cboDelType;
    private javax.swing.JComboBox<ProductType> cboEditType;
    private javax.swing.JComboBox<ProductType> cboFilterType;
    private javax.swing.JComboBox<ProductType> cboProdType;
    private javax.swing.JComboBox<ProductType> cboTipoNewP;
    private javax.swing.JComboBox<UserType> cboTipoUser;
    private javax.swing.JComboBox<User> cboUserEdit;
    private javax.swing.JCheckBox chkFilterType;
    private javax.swing.JCheckBox chkSesionPermanent;
    private javax.swing.JDialog dialogProdTypeEdit;
    private javax.swing.JFrame formAdd;
    private javax.swing.JFrame formEditUsers;
    private javax.swing.JFrame formList;
    private javax.swing.JFrame formManageType;
    private javax.swing.JFrame formMenu;
    private javax.swing.JMenuItem itemDesAutoStart;
    private javax.swing.JMenu itemDeshStart;
    private javax.swing.JMenuItem itemHabTipo;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAvailableStock;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel panelConsumo;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelRecStock;
    private javax.swing.JSpinner spinCantCons;
    private javax.swing.JSpinner spinCantMinNewP1;
    private javax.swing.JSpinner spinCantNewP;
    private javax.swing.JSpinner spinCantRec;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtEditTypeName;
    private javax.swing.JTextField txtEmailUser;
    private javax.swing.JTextField txtNameNewP;
    private javax.swing.JTextField txtNamePSearch;
    private javax.swing.JTextField txtNameUser;
    private javax.swing.JTextField txtNickUser;
    private javax.swing.JPasswordField txtPassUser;
    private javax.swing.JPasswordField txtPassw;
    private javax.swing.JTextField txtProdCons;
    private javax.swing.JTextField txtProdRec;
    private javax.swing.JPasswordField txtRepPassUser;
    private javax.swing.JTextField txtTypeName;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

}
