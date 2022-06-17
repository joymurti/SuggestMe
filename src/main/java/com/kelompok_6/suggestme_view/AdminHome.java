/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kelompok_6.suggestme_view;

import com.kelompok_6.suggestme_controller.FoodController;
import com.kelompok_6.suggestme_model.Admin;
import com.kelompok_6.suggestme_model.Food;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import scrollbar.ModernScrollBarUI;

/**
 *
 * @author wwijo
 */
public class AdminHome extends javax.swing.JFrame {
    private Admin loginUser;
    private FoodController a;
    private File foodImg;
    private int food_id;
    /**
     * Creates new form Home
     */
    public AdminHome() {
        initComponents();
        jLabel1.setIcon(new ImageIcon("images/icons8_home_25px_1.png"));
        jLabel5.setIcon(new ImageIcon("images/icons8_street_food_25px.png"));
        jLabel15.setIcon(new ImageIcon("images/logout.png"));
        iconContainer.setIcon(new ImageIcon("suggestMe.png"));
        panelHome.setBackground(new Color(255,207,136));
        panelFood.setBackground(new Color(255,188,90));
        namaItem.setLabelText("Nama Item");
        hargaItem.setLabelText("Harga Item");
        jenisItem.setLabeText("Jenis Item");
        moodItem.setLabeText("Mood");
        addBtn.setIcon(new ImageIcon("images/icons8_add_25px.png"));
        editBtn.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
        deleteBtn.setIcon(new ImageIcon("images/icons8_delete_25px.png"));
        clearBtn.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
        labelNama2.setIcon(new ImageIcon("images/icons8_name_32px_1.png"));
        labelAlamat2.setIcon(new ImageIcon("images/icons8_address_32px.png"));
        labelTelepon2.setIcon(new ImageIcon("images/icons8_telephone_32px.png"));
        selectImgBtn.setIcon(new ImageIcon("images/icons8_image_25px.png"));
    }
    
    public AdminHome(Admin loginUser) {
        initComponents(); 
        jLabel1.setIcon(new ImageIcon("images/icons8_home_25px_1.png"));
        jLabel5.setIcon(new ImageIcon("images/icons8_street_food_25px.png"));
        jLabel15.setIcon(new ImageIcon("images/logout.png"));
        iconContainer.setIcon(new ImageIcon("suggestMe.png"));
        panelHome.setBackground(new Color(255,207,136));
        panelFood.setBackground(new Color(255,188,90));
        addBtn.setIcon(new ImageIcon("images/icons8_add_25px.png"));
        editBtn.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
        deleteBtn.setIcon(new ImageIcon("images/icons8_delete_25px.png"));
        clearBtn.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
        labelNama2.setIcon(new ImageIcon("images/icons8_name_32px_1.png"));
        labelAlamat2.setIcon(new ImageIcon("images/icons8_address_32px.png"));
        labelTelepon2.setIcon(new ImageIcon("images/icons8_telephone_32px.png"));
        selectImgBtn.setIcon(new ImageIcon("images/icons8_image_25px.png"));
        homeView.setVisible(true);
        url.setText("Home");
        titleText.setText("Home");
        this.loginUser = loginUser;
        hiContainer.setText("Hello, " + loginUser.getNama()  + " :)");
        labelNama.setText(loginUser.getNama());
        labelAlamat.setText(loginUser.getAlamat());
        labelTelepon.setText(loginUser.getTelepon());
        namaItem.setLabelText("Nama Item");
        hargaItem.setLabelText("Harga Item");
        jenisItem.setLabeText("Jenis Item");
        moodItem.setLabeText("Mood");
        File file = new File("src/foto/"+ loginUser.getGambar());
        BufferedImage bufferedImg;
        try {
            bufferedImg = ImageIO.read(file);
            fotoProfile.setIcon(new ImageIcon(bufferedImg.getScaledInstance(fotoProfile.getWidth(), fotoProfile.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear() {
        try {
            File file = new File("src/foto/default_food.jpg");
            BufferedImage bufferedImg;
            bufferedImg = ImageIO.read(file);
            fotoFood.setIcon(null);
            namaItem.setText("");
            hargaItem.setText("");
            jenisItem.setSelectedIndex(-1);
            moodItem.setSelectedIndex(-1);
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initData() {
        try {
            clear();
            List<Food> listFood = a.getFoodData(loginUser);
            DefaultTableModel tblModel = new DefaultTableModel();
            
            tblModel.addColumn("ID");
            tblModel.addColumn("Nama");
            tblModel.addColumn("Harga");
            tblModel.addColumn("Jenis");
            tblModel.addColumn("Mood");

            for(Food f: listFood)
            {    
                tblModel.addRow(new Object[]{f.getFood()[0], f.getFood()[1], f.getFood()[2], f.getFood()[3], f.getMood()});
            }
            
            foodTable.setModel(tblModel);

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(new Color(0,80,208));
            headerRenderer.setForeground(Color.WHITE);

            for (int i = 0; i < foodTable.getModel().getColumnCount(); i++) {
                foodTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        sidePane = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelFood = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        iconContainer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        titleText = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        homeView = new javax.swing.JPanel();
        fotoProfile = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        labelAlamat = new javax.swing.JLabel();
        labelTelepon = new javax.swing.JLabel();
        labelAlamat2 = new javax.swing.JLabel();
        labelNama2 = new javax.swing.JLabel();
        labelTelepon2 = new javax.swing.JLabel();
        foodView = new javax.swing.JPanel();
        namaItem = new textfield.TextField();
        hargaItem = new textfield.TextField();
        moodItem = new combobox.Combobox();
        jenisItem = new combobox.Combobox();
        fotoFood = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        addBtn = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        editBtn = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        selectImgBtn = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        clearBtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        foodTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        hiContainer = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        url = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(960, 540));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePane.setBackground(new java.awt.Color(255, 172, 58));
        sidePane.setPreferredSize(new java.awt.Dimension(300, 540));
        sidePane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(255, 207, 136));
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelHomeMousePressed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(58, 140, 255));
        jLabel2.setText("Home");

        javax.swing.GroupLayout panelHomeLayout = new javax.swing.GroupLayout(panelHome);
        panelHome.setLayout(panelHomeLayout);
        panelHomeLayout.setHorizontalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelHomeLayout.setVerticalGroup(
            panelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 330, 50));

        panelFood.setBackground(new java.awt.Color(255, 188, 90));
        panelFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFoodMousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(58, 140, 255));
        jLabel6.setText("Food Suggestion Management");

        javax.swing.GroupLayout panelFoodLayout = new javax.swing.GroupLayout(panelFood);
        panelFood.setLayout(panelFoodLayout);
        panelFoodLayout.setHorizontalGroup(
            panelFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFoodLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelFoodLayout.setVerticalGroup(
            panelFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        panelLogout.setBackground(new java.awt.Color(255, 188, 90));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLogoutMouseClicked(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(58, 140, 255));
        jLabel16.setText("Log Out");

        javax.swing.GroupLayout panelLogoutLayout = new javax.swing.GroupLayout(panelLogout);
        panelLogout.setLayout(panelLogoutLayout);
        panelLogoutLayout.setHorizontalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelLogoutLayout.setVerticalGroup(
            panelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, -1, -1));

        iconContainer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sidePane.add(iconContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 120));

        bg.add(sidePane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        jPanel1.setBackground(new java.awt.Color(0, 80, 208));

        titleText.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        titleText.setForeground(new java.awt.Color(255, 255, 255));
        titleText.setText("Home");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titleText, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 880, 70));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 80, 208), 2));
        jPanel8.setLayout(new java.awt.CardLayout());

        homeView.setBackground(new java.awt.Color(255, 255, 255));
        homeView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeView.add(fotoProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 28, 184, 209));

        labelNama.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNama.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 290, 45));

        labelAlamat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelAlamat.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 290, 45));

        labelTelepon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTelepon.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 290, 45));

        labelAlamat2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelAlamat2.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelAlamat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 40, 45));

        labelNama2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNama2.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelNama2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 40, 45));

        labelTelepon2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTelepon2.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelTelepon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 40, 45));

        jPanel8.add(homeView, "card2");

        foodView.setBackground(new java.awt.Color(255, 255, 255));
        foodView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        foodView.add(namaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 25, 250, -1));

        hargaItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaItemKeyTyped(evt);
            }
        });
        foodView.add(hargaItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 100, 250, -1));

        moodItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagi Hari", "Siang Hari", "Sore Hari", "Malam Hari" }));
        moodItem.setSelectedIndex(-1);
        foodView.add(moodItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 105, 220, -1));

        jenisItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Makanan", "Minuman" }));
        jenisItem.setSelectedIndex(-1);
        foodView.add(jenisItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 35, 220, -1));

        fotoFood.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodView.add(fotoFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 250, 184, 209));

        jPanel2.setBackground(new java.awt.Color(0, 80, 208));
        jPanel2.setPreferredSize(new java.awt.Dimension(80, 40));

        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addBtn.setText("ADD");
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        foodView.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 80, 208));
        jPanel3.setPreferredSize(new java.awt.Dimension(80, 40));

        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editBtn.setText("EDIT");
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        foodView.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        jPanel4.setBackground(new java.awt.Color(0, 80, 208));
        jPanel4.setPreferredSize(new java.awt.Dimension(80, 40));

        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteBtn.setText("DELETE");
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        foodView.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        jPanel6.setBackground(new java.awt.Color(0, 80, 208));
        jPanel6.setPreferredSize(new java.awt.Dimension(80, 40));

        selectImgBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectImgBtn.setForeground(new java.awt.Color(255, 255, 255));
        selectImgBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectImgBtn.setText("SELECT IMAGE");
        selectImgBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selectImgBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectImgBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectImgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        foodView.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 130, -1));

        jPanel5.setBackground(new java.awt.Color(0, 80, 208));
        jPanel5.setPreferredSize(new java.awt.Dimension(80, 40));

        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearBtn.setText("CLEAR");
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        foodView.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        foodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Harga", "Jenis", "Mood"
            }
        ));
        foodTable.setFocusable(false);
        foodTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        foodTable.setRowHeight(25);
        foodTable.setSelectionBackground(new java.awt.Color(255, 172, 58));
        foodTable.setShowGrid(false);
        foodTable.getTableHeader().setReorderingAllowed(false);
        foodTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(foodTable);

        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        foodView.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 600, 250));

        jPanel8.add(foodView, "card3");

        bg.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 860, 520));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("X");
        jLabel13.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        bg.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, 30, -1));

        hiContainer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hiContainer.setText("Hello, Admin :)");
        bg.add(hiContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 340, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("/");
        bg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 10, -1));

        url.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        url.setText("Home");
        bg.add(url, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 220, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("SuggestMe");
        bg.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void panelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseClicked
        // TODO add your handling code here:
        LoginForm a = new LoginForm();
        this.dispose();
        a.setVisible(true);
    }//GEN-LAST:event_panelLogoutMouseClicked

    private void panelHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMousePressed
        // TODO add your handling code here:
        panelHome.setBackground(new Color(255,207,136));
        panelFood.setBackground(new Color(255,188,90));
        homeView.setVisible(true);
        foodView.setVisible(false);
        url.setText("Home");
        titleText.setText("Home");
    }//GEN-LAST:event_panelHomeMousePressed

    private void panelFoodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFoodMousePressed
        // TODO add your handling code here:
        panelFood.setBackground(new Color(255,207,136));
        panelHome.setBackground(new Color(255,188,90));
        homeView.setVisible(false);
        foodView.setVisible(true);
        url.setText("Food Suggestion Management");
        titleText.setText("Food Suggestion Management");
        
        a = new FoodController();
        initData();
    }//GEN-LAST:event_panelFoodMousePressed

    private void addBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMousePressed
        // TODO add your handling code here:
        if((namaItem.getText().isEmpty()) || (hargaItem.getText().isEmpty()) || (jenisItem.getSelectedItem().toString().isEmpty()) || (moodItem.getSelectedItem().toString().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Silahkan lengkapi data terlebih dahulu!");
        } else if (foodImg == null) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih gambar terlebih dahulu!");
        } else {
            a.insertFoodData(namaItem.getText(), jenisItem.getSelectedItem().toString(), moodItem.getSelectedItem().toString(), Integer.parseInt(hargaItem.getText()), foodImg, loginUser);
            initData();
        }
    }//GEN-LAST:event_addBtnMousePressed

    private void selectImgBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectImgBtnMousePressed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if(f != null) {
            String filename = f.getName();
            String extension = filename.substring(filename.lastIndexOf('.') + 1);
            
            if(extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")){
            ImageIcon icon = new ImageIcon(f.toString());
            Image image = icon.getImage().getScaledInstance(fotoFood.getWidth(), fotoFood.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(image);

            fotoFood.setIcon(ic);
            
            foodImg = f;
            } else {
                JOptionPane.showMessageDialog(this, "File Tidak Support!");
            }
        }
    }//GEN-LAST:event_selectImgBtnMousePressed

    private void hargaItemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaItemKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_hargaItemKeyTyped

    private void clearBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtnMousePressed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clearBtnMousePressed

    private void editBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMousePressed
        // TODO add your handling code here:
        if((namaItem.getText().isEmpty()) || (hargaItem.getText().isEmpty()) || (jenisItem.getSelectedItem().toString().isEmpty()) || (moodItem.getSelectedItem().toString().isEmpty()) || foodImg == null) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Terlebih Dahulu!");
        } else {
            a.updateFoodData(namaItem.getText(), jenisItem.getSelectedItem().toString(), moodItem.getSelectedItem().toString(), Integer.parseInt(hargaItem.getText()), foodImg, food_id, loginUser);
            initData();
        }
    }//GEN-LAST:event_editBtnMousePressed

    private void foodTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)foodTable.getModel();
        int idx = foodTable.getSelectedRow();
        
        food_id = Integer.parseInt(model.getValueAt(idx, 0).toString());
        namaItem.setText(model.getValueAt(idx, 1).toString());
        hargaItem.setText(model.getValueAt(idx, 2).toString());
        jenisItem.setSelectedItem(model.getValueAt(idx, 3).toString());
        moodItem.setSelectedItem(model.getValueAt(idx, 4).toString());
        if(a.getGambar(food_id, loginUser) != null) {
            foodImg = a.getGambar(food_id, loginUser);
            ImageIcon icon = new ImageIcon(foodImg.toString());
            Image image = icon.getImage().getScaledInstance(fotoFood.getWidth(), fotoFood.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(image);

            fotoFood.setIcon(ic);
        }
    }//GEN-LAST:event_foodTableMouseClicked

    private void deleteBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMousePressed
        // TODO add your handling code here:
        if((namaItem.getText().isEmpty()) || (hargaItem.getText().isEmpty()) || (jenisItem.getSelectedItem().toString().isEmpty()) || (moodItem.getSelectedItem().toString().isEmpty()) || foodImg == null) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Terlebih Dahulu!");
        } else {
            a.deleteFoodData(food_id, loginUser, foodImg);
            initData();
        }
    }//GEN-LAST:event_deleteBtnMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBtn;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel clearBtn;
    private javax.swing.JLabel deleteBtn;
    private javax.swing.JLabel editBtn;
    private javax.swing.JTable foodTable;
    private javax.swing.JPanel foodView;
    private javax.swing.JLabel fotoFood;
    private javax.swing.JLabel fotoProfile;
    private textfield.TextField hargaItem;
    private javax.swing.JLabel hiContainer;
    private javax.swing.JPanel homeView;
    private javax.swing.JLabel iconContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private combobox.Combobox jenisItem;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelAlamat2;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelNama2;
    private javax.swing.JLabel labelTelepon;
    private javax.swing.JLabel labelTelepon2;
    private combobox.Combobox moodItem;
    private textfield.TextField namaItem;
    private javax.swing.JPanel panelFood;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JLabel selectImgBtn;
    private javax.swing.JPanel sidePane;
    private javax.swing.JLabel titleText;
    private javax.swing.JLabel url;
    // End of variables declaration//GEN-END:variables
}
