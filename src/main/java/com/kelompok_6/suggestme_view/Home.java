/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kelompok_6.suggestme_view;

import com.kelompok_6.suggestme_controller.FoodController;
import com.kelompok_6.suggestme_controller.MoneyManagementController;
import com.kelompok_6.suggestme_controller.UserController;
import com.kelompok_6.suggestme_model.Customer;
import com.kelompok_6.suggestme_model.Food;
import com.kelompok_6.suggestme_model.History;
import java.awt.Color;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.chart.ModelPieChart;
import static javaswingdev.chart.PieChart.PeiChartType.DONUT_CHART;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import scrollbar.ModernScrollBarUI;

/**
 *
 * @author wwijo
 */
public class Home extends javax.swing.JFrame {
    private Customer loginUser;
    private boolean newUser;
    private UserController a;
    private MoneyManagementController b;
    private FoodController c;
    private int totalPengeluaranInt, totalPemasukanInt, countPengeluaran=0, countPemasukan=0;
    private File profileImg;
    private List<Food> listFood = new ArrayList<Food>();
    private List<History> listHistory = new ArrayList<History>();
    private int food_id;
    private File foodImg;
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        jLabel1.setIcon(new ImageIcon("images/icons8_home_25px_1.png"));
        jLabel3.setIcon(new ImageIcon("images/icons8_contacts_32px.png"));
        jLabel5.setIcon(new ImageIcon("images/icons8_street_food_25px.png"));
        jLabel7.setIcon(new ImageIcon("images/icons8_money_25px.png"));
        jLabel9.setIcon(new ImageIcon("images/icons8_request_money_25px.png"));
        jLabel11.setIcon(new ImageIcon("images/icons8_statistics_25px.png"));
        jLabel15.setIcon(new ImageIcon("images/logout.png"));
        iconContainer.setIcon(new ImageIcon("suggestMe.png"));
        panelHome.setBackground(new Color(255,207,136));
        panelEditProfile.setBackground(new Color(255,188,90));
        panelFood.setBackground(new Color(255,188,90));
        panelPemasukan.setBackground(new Color(255,188,90));
        panelPengeluaran.setBackground(new Color(255,188,90));
        panelStatistik.setBackground(new Color(255,188,90));
        saldoField.setLabelText("Input Saldo Awal");
        clearBtn.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
        submitBtn.setIcon(new ImageIcon("images/icons8_save_25px_1"));
        editBtn.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
    }
    
    public Home(Customer loginUser, boolean isNewUser) {
        initComponents();
        jLabel1.setIcon(new ImageIcon("images/icons8_home_25px_1.png"));
        jLabel3.setIcon(new ImageIcon("images/icons8_contacts_32px.png"));
        jLabel5.setIcon(new ImageIcon("images/icons8_street_food_25px.png"));
        jLabel7.setIcon(new ImageIcon("images/icons8_money_25px.png"));
        jLabel9.setIcon(new ImageIcon("images/icons8_request_money_25px.png"));
        jLabel11.setIcon(new ImageIcon("images/icons8_statistics_25px.png"));
        jLabel15.setIcon(new ImageIcon("images/logout.png"));
        iconContainer.setIcon(new ImageIcon("suggestMe.png"));
        panelHome.setBackground(new Color(255,207,136));
        panelEditProfile.setBackground(new Color(255,188,90));
        panelFood.setBackground(new Color(255,188,90));
        panelPemasukan.setBackground(new Color(255,188,90));
        panelPengeluaran.setBackground(new Color(255,188,90));
        panelStatistik.setBackground(new Color(255,188,90));
        this.loginUser = loginUser;
        hiContainer.setText("Hello, " + loginUser.getNama()  + " :)");
        labelNama.setText(loginUser.getNama());
        labelAlamat.setText(loginUser.getAlamat());
        labelTelepon.setText(loginUser.getTelepon());
        labelAlamat1.setIcon(new ImageIcon("images/icons8_address_32px.png"));
        labelTelepon2.setIcon(new ImageIcon("images/icons8_telephone_32px.png"));
        labelSaldo.setText("Rp." + String.valueOf(this.loginUser.getKeuangan().getTotalSaldo()) + ",00");
        File file = new File("src/foto/"+ loginUser.getGambar());
        BufferedImage bufferedImg;
        try {
            bufferedImg = ImageIO.read(file);
            fotoProfile.setIcon(new ImageIcon(bufferedImg.getScaledInstance(fotoProfile.getWidth(), fotoProfile.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.newUser = isNewUser;
        this.a = new UserController("User");
        this.b = new MoneyManagementController();
        if(isNewUser) {
            showPanel(false, true, false, false, false, false, false);
            titleText.setText("Input Saldo Awal");
            urlNext.setText("Input Saldo Awal");
            saldoField.setLabelText("Input Saldo Awal");
            clearBtn.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
            submitBtn.setIcon(new ImageIcon("images/icons8_save_25px_1.png"));
        } else {
            showPanel(true, false, false, false, false, false, false);
            titleText.setText("Home");
            urlNext.setText("Home");
        }
        editBtn.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
    }   
    
    public void showPanel(boolean home, boolean homeInit, boolean profile, boolean food, boolean pemasukan, boolean pengeluaran, boolean statistik) {
        this.homeView.setVisible(home);
        this.homeViewInit.setVisible(homeInit);
        this.pemasukanView.setVisible(pemasukan);
        this.pengeluaranView.setVisible(pengeluaran);
        this.foodSuggestionView.setVisible(food);
        this.editProfileView.setVisible(profile);
        this.statistikView.setVisible(statistik);
    }
    
    public void panelBackground(Color a, Color b, Color c, Color d, Color e, Color f) {
        panelHome.setBackground(a);
        panelEditProfile.setBackground(b);
        panelFood.setBackground(c);
        panelPemasukan.setBackground(d);
        panelPengeluaran.setBackground(e);
        panelStatistik.setBackground(f);
    }
    
    public void changeAttr(String a) {
        titleText.setText(a);
        urlNext.setText(a);
    }
    
    public void initDataUpdate(String nama, String alamat, String telepon, File img) {
        profileImg = img;
        BufferedImage bufferedImg;
        try {
            bufferedImg = ImageIO.read(profileImg);
            fotoProfile.setIcon(new ImageIcon(bufferedImg.getScaledInstance(fotoProfile.getWidth(), fotoProfile.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelNama.setText(nama);
        labelAlamat.setText(alamat);
        labelTelepon.setText(telepon);
    }
    
    public void initData() {
        try {
            listHistory = loginUser.getAllHistoryUser(loginUser.getId());
            DefaultTableModel tblModel = new DefaultTableModel();
            
            tblModel.addColumn("Keterangan");
            tblModel.addColumn("Tipe");
            tblModel.addColumn("Perubahan Saldo");
            tblModel.addColumn("Tanggal");
            
//            tblModel.setRowCount(0);

            for(History history: listHistory)
            {
                String keterangan = history.getKeterangan();
                int perubahanSaldo = history.getPerubahanSaldo();
                String tipe = history.getTipe();
                String tanggal = history.getTanggal().toString();
                
                if(tipe.equals("pengeluaran")) {
                    totalPengeluaranInt+=perubahanSaldo;
                    countPengeluaran+=1;
                } else if (tipe.equals("pemasukan")) {
                    totalPemasukanInt+=perubahanSaldo;
                    countPemasukan+=1;
                }
                
                tblModel.addRow(new Object[]{keterangan,tipe,perubahanSaldo,tanggal.toString()});
            } 
            
            tableDataStatistik.setModel(tblModel); 
            tableDataStatistik.setModel(tblModel);

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(new Color(0,80,208));
            headerRenderer.setForeground(Color.WHITE);

            for (int i = 0; i < tableDataStatistik.getModel().getColumnCount(); i++) {
                tableDataStatistik.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
            
            pieChartStat.addData(new ModelPieChart("Pemasukan", countPemasukan, new Color(0, 80, 208)));
            pieChartStat.addData(new ModelPieChart("Pengeluaran", countPengeluaran, new Color(255,172,58)));
            
            totalSaldo.setText("Rp. " + String.valueOf(loginUser.getKeuangan().getTotalSaldo()));
            totalPemasukan.setText("Rp. " + String.valueOf(totalPemasukanInt));
            totalPengeluaran.setText("Rp. " + String.valueOf(totalPengeluaranInt));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void clear() {
        try {
            File file = new File("src/foto/default_food.jpg");
            BufferedImage bufferedImg;
            bufferedImg = ImageIO.read(file);
            namaItem1.setText("");
            hargaItem1.setText("");
            jenisItem.setText("");
            moodText.setText("");
            listFood.clear();
            qty.setValue(0);
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initData(String mood) {
        try {
            clear();
            foodImg = null;
            listFood = c.getFoodData(mood, loginUser);
            DefaultTableModel tblModel = new DefaultTableModel();
            
            tblModel.addColumn("ID");
            tblModel.addColumn("Nama");
            tblModel.addColumn("Harga");
            tblModel.addColumn("Jenis");

            for(Food f: listFood)
            {    
                tblModel.addRow(new Object[]{f.getFood()[0], f.getFood()[1], f.getFood()[2], f.getFood()[3]});
            }
            
            tableFoodData.setModel(tblModel);

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(new Color(0,80,208));
            headerRenderer.setForeground(Color.WHITE);

            for (int i = 0; i < tableFoodData.getModel().getColumnCount(); i++) {
                tableFoodData.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
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
        panelEditProfile = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelFood = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelPemasukan = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelPengeluaran = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelStatistik = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        iconContainer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        titleText = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        foodSuggestionView = new javax.swing.JPanel();
        moodMalam = new javax.swing.JPanel();
        moodMalamBtn = new javax.swing.JLabel();
        moodSore = new javax.swing.JPanel();
        moodSoreBtn = new javax.swing.JLabel();
        moodSiang = new javax.swing.JPanel();
        moodSiangBtn = new javax.swing.JLabel();
        moodPagi = new javax.swing.JPanel();
        moodPagiBtn = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFoodData = new javax.swing.JTable();
        qtyLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        qty = new spinner.Spinner();
        moodText = new javax.swing.JLabel();
        pesanLabel = new javax.swing.JPanel();
        pesanBtn = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jenisItem = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        namaItem1 = new javax.swing.JLabel();
        hargaItem1 = new javax.swing.JLabel();
        fotoFood = new javax.swing.JLabel();
        homeViewInit = new javax.swing.JPanel();
        saldoField = new textfield.TextField();
        submitBtnPanel = new javax.swing.JPanel();
        submitBtn = new javax.swing.JLabel();
        clearBtnPanel = new javax.swing.JPanel();
        clearBtn = new javax.swing.JLabel();
        homeView = new javax.swing.JPanel();
        labelAlamat = new javax.swing.JLabel();
        labelTelepon = new javax.swing.JLabel();
        labelAlamat1 = new javax.swing.JLabel();
        labelTelepon2 = new javax.swing.JLabel();
        panelRound1 = new NewPanel.PanelRound();
        fotoProfile = new javax.swing.JLabel();
        labelNama = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        foodView = new javax.swing.JPanel();
        pemasukanView = new javax.swing.JPanel();
        saldoField1 = new textfield.TextField();
        submitBtnPanel1 = new javax.swing.JPanel();
        submitBtn1 = new javax.swing.JLabel();
        clearBtnPanel1 = new javax.swing.JPanel();
        clearBtn1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        decsField1 = new javax.swing.JTextArea();
        decsLabel1 = new javax.swing.JLabel();
        pengeluaranView = new javax.swing.JPanel();
        saldoField2 = new textfield.TextField();
        submitBtnPanel2 = new javax.swing.JPanel();
        submitBtn2 = new javax.swing.JLabel();
        clearBtnPanel2 = new javax.swing.JPanel();
        clearBtn2 = new javax.swing.JLabel();
        decsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        decsField = new javax.swing.JTextArea();
        statistikView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataStatistik = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        totalPengeluaran = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        totalSaldo = new javax.swing.JLabel();
        totalPemasukan = new javax.swing.JLabel();
        printPanel = new javax.swing.JPanel();
        printBtn = new javax.swing.JLabel();
        pieChartStat = new javaswingdev.chart.PieChart();
        editProfileView = new javax.swing.JPanel();
        editPassword = new javax.swing.JPanel();
        editPasswordBtn = new javax.swing.JLabel();
        editProfile = new javax.swing.JPanel();
        editProfileBtn = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        editProfileDataView = new javax.swing.JPanel();
        namaField = new textfield.TextField();
        alamatField = new textfield.TextField();
        teleponField = new textfield.TextField();
        jPanel6 = new javax.swing.JPanel();
        selectImgBtn = new javax.swing.JLabel();
        fotoProfileEdit = new javax.swing.JLabel();
        editPanel1 = new javax.swing.JPanel();
        editBtn1 = new javax.swing.JLabel();
        editPasswordView = new javax.swing.JPanel();
        passwordField1 = new textfield.PasswordField();
        passwordField2 = new textfield.PasswordField();
        editPanel = new javax.swing.JPanel();
        editBtn = new javax.swing.JLabel();
        showPassword = new checkbox.JCheckBoxCustom();
        jLabel13 = new javax.swing.JLabel();
        hiContainer = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        urlNext = new javax.swing.JLabel();
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

        panelEditProfile.setBackground(new java.awt.Color(255, 188, 90));
        panelEditProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelEditProfileMousePressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(58, 140, 255));
        jLabel4.setText("Edit Profile");

        javax.swing.GroupLayout panelEditProfileLayout = new javax.swing.GroupLayout(panelEditProfile);
        panelEditProfile.setLayout(panelEditProfileLayout);
        panelEditProfileLayout.setHorizontalGroup(
            panelEditProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelEditProfileLayout.setVerticalGroup(
            panelEditProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelEditProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        panelFood.setBackground(new java.awt.Color(255, 188, 90));
        panelFood.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFoodMousePressed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(58, 140, 255));
        jLabel6.setText("Food Suggestion");

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

        sidePane.add(panelFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, -1, -1));

        panelPemasukan.setBackground(new java.awt.Color(255, 188, 90));
        panelPemasukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelPemasukanMousePressed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(58, 140, 255));
        jLabel8.setText("Input Pemasukan");

        javax.swing.GroupLayout panelPemasukanLayout = new javax.swing.GroupLayout(panelPemasukan);
        panelPemasukan.setLayout(panelPemasukanLayout);
        panelPemasukanLayout.setHorizontalGroup(
            panelPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPemasukanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelPemasukanLayout.setVerticalGroup(
            panelPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelPemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));

        panelPengeluaran.setBackground(new java.awt.Color(255, 188, 90));
        panelPengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelPengeluaranMousePressed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(58, 140, 255));
        jLabel10.setText("Input Pengeluaran");

        javax.swing.GroupLayout panelPengeluaranLayout = new javax.swing.GroupLayout(panelPengeluaran);
        panelPengeluaran.setLayout(panelPengeluaranLayout);
        panelPengeluaranLayout.setHorizontalGroup(
            panelPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelPengeluaranLayout.setVerticalGroup(
            panelPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, -1, -1));

        panelStatistik.setBackground(new java.awt.Color(255, 188, 90));
        panelStatistik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelStatistikMousePressed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(58, 140, 255));
        jLabel12.setText("Statistik");

        javax.swing.GroupLayout panelStatistikLayout = new javax.swing.GroupLayout(panelStatistik);
        panelStatistik.setLayout(panelStatistikLayout);
        panelStatistikLayout.setHorizontalGroup(
            panelStatistikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelStatistikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        panelStatistikLayout.setVerticalGroup(
            panelStatistikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        sidePane.add(panelStatistik, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, -1, -1));

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

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 80, 208), 2));
        jPanel9.setLayout(new java.awt.CardLayout());

        foodSuggestionView.setBackground(new java.awt.Color(255, 255, 255));
        foodSuggestionView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        moodMalam.setBackground(new java.awt.Color(0, 80, 208));
        moodMalam.setForeground(new java.awt.Color(255, 255, 255));
        moodMalam.setPreferredSize(new java.awt.Dimension(150, 50));

        moodMalamBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moodMalamBtn.setForeground(new java.awt.Color(255, 255, 255));
        moodMalamBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moodMalamBtn.setText("Mood Malam");
        moodMalamBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moodMalamBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout moodMalamLayout = new javax.swing.GroupLayout(moodMalam);
        moodMalam.setLayout(moodMalamLayout);
        moodMalamLayout.setHorizontalGroup(
            moodMalamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodMalamBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        moodMalamLayout.setVerticalGroup(
            moodMalamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodMalamBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        foodSuggestionView.add(moodMalam, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        moodSore.setBackground(new java.awt.Color(0, 80, 208));
        moodSore.setForeground(new java.awt.Color(255, 255, 255));
        moodSore.setPreferredSize(new java.awt.Dimension(150, 50));

        moodSoreBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moodSoreBtn.setForeground(new java.awt.Color(255, 255, 255));
        moodSoreBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moodSoreBtn.setText("Mood Sore");
        moodSoreBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moodSoreBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout moodSoreLayout = new javax.swing.GroupLayout(moodSore);
        moodSore.setLayout(moodSoreLayout);
        moodSoreLayout.setHorizontalGroup(
            moodSoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodSoreBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        moodSoreLayout.setVerticalGroup(
            moodSoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodSoreBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        foodSuggestionView.add(moodSore, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        moodSiang.setBackground(new java.awt.Color(0, 80, 208));
        moodSiang.setForeground(new java.awt.Color(255, 255, 255));
        moodSiang.setPreferredSize(new java.awt.Dimension(150, 50));

        moodSiangBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moodSiangBtn.setForeground(new java.awt.Color(255, 255, 255));
        moodSiangBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moodSiangBtn.setText("Mood Siang");
        moodSiangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moodSiangBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout moodSiangLayout = new javax.swing.GroupLayout(moodSiang);
        moodSiang.setLayout(moodSiangLayout);
        moodSiangLayout.setHorizontalGroup(
            moodSiangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodSiangBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        moodSiangLayout.setVerticalGroup(
            moodSiangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodSiangBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        foodSuggestionView.add(moodSiang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        moodPagi.setBackground(new java.awt.Color(0, 80, 208));
        moodPagi.setForeground(new java.awt.Color(255, 255, 255));
        moodPagi.setPreferredSize(new java.awt.Dimension(150, 50));
        moodPagi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moodPagiMousePressed(evt);
            }
        });

        moodPagiBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moodPagiBtn.setForeground(new java.awt.Color(255, 255, 255));
        moodPagiBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moodPagiBtn.setText("Mood Pagi");
        moodPagiBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moodPagiBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout moodPagiLayout = new javax.swing.GroupLayout(moodPagi);
        moodPagi.setLayout(moodPagiLayout);
        moodPagiLayout.setHorizontalGroup(
            moodPagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodPagiBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        moodPagiLayout.setVerticalGroup(
            moodPagiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moodPagiBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        foodSuggestionView.add(moodPagi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tableFoodData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Harga", "Jenis"
            }
        ));
        tableFoodData.setFocusable(false);
        tableFoodData.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableFoodData.setRowHeight(25);
        tableFoodData.setSelectionBackground(new java.awt.Color(255, 172, 58));
        tableFoodData.setShowGrid(false);
        tableFoodData.getTableHeader().setReorderingAllowed(false);
        tableFoodData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFoodDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableFoodData);

        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 590, 250));

        qtyLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        qtyLabel.setText("QTY:");
        jPanel3.add(qtyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, 20));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Mood :");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 290, -1, 20));
        jPanel3.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 280, 60, -1));

        moodText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        moodText.setText("Pagi");
        jPanel3.add(moodText, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, 20));

        pesanLabel.setBackground(new java.awt.Color(0, 80, 208));
        pesanLabel.setForeground(new java.awt.Color(255, 255, 255));
        pesanLabel.setPreferredSize(new java.awt.Dimension(150, 50));

        pesanBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pesanBtn.setForeground(new java.awt.Color(255, 255, 255));
        pesanBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pesanBtn.setText("Pesan");
        pesanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pesanBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pesanLabelLayout = new javax.swing.GroupLayout(pesanLabel);
        pesanLabel.setLayout(pesanLabelLayout);
        pesanLabelLayout.setHorizontalGroup(
            pesanLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pesanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        pesanLabelLayout.setVerticalGroup(
            pesanLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pesanBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel3.add(pesanLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Jenis Item : ");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 380, -1, 20));

        jenisItem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jenisItem.setText("Pagi");
        jPanel3.add(jenisItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, -1, 20));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Nama Item : ");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 320, -1, 20));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("Harga Item : ");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 350, -1, 20));

        namaItem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        namaItem1.setText("Pagi");
        jPanel3.add(namaItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, 20));

        hargaItem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hargaItem1.setText("Pagi");
        jPanel3.add(hargaItem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, 20));

        fotoFood.setPreferredSize(new java.awt.Dimension(210, 250));
        jPanel3.add(fotoFood, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, -1));

        foodSuggestionView.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 820, 410));

        jPanel9.add(foodSuggestionView, "card9");

        homeViewInit.setBackground(new java.awt.Color(255, 255, 255));
        homeViewInit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        homeViewInit.add(saldoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 340, -1));

        submitBtnPanel.setBackground(new java.awt.Color(0, 80, 208));
        submitBtnPanel.setForeground(new java.awt.Color(255, 255, 255));
        submitBtnPanel.setPreferredSize(new java.awt.Dimension(100, 50));

        submitBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitBtn.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        submitBtn.setText("SUBMIT");
        submitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                submitBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout submitBtnPanelLayout = new javax.swing.GroupLayout(submitBtnPanel);
        submitBtnPanel.setLayout(submitBtnPanelLayout);
        submitBtnPanelLayout.setHorizontalGroup(
            submitBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        submitBtnPanelLayout.setVerticalGroup(
            submitBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        homeViewInit.add(submitBtnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, -1, -1));

        clearBtnPanel.setBackground(new java.awt.Color(0, 80, 208));
        clearBtnPanel.setForeground(new java.awt.Color(255, 255, 255));
        clearBtnPanel.setPreferredSize(new java.awt.Dimension(100, 50));

        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearBtn.setText("CLEAR");
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout clearBtnPanelLayout = new javax.swing.GroupLayout(clearBtnPanel);
        clearBtnPanel.setLayout(clearBtnPanelLayout);
        clearBtnPanelLayout.setHorizontalGroup(
            clearBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        clearBtnPanelLayout.setVerticalGroup(
            clearBtnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        homeViewInit.add(clearBtnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, -1, -1));

        jPanel9.add(homeViewInit, "card2");

        homeView.setBackground(new java.awt.Color(255, 255, 255));
        homeView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelAlamat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelAlamat.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 290, 45));

        labelTelepon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTelepon.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelTelepon, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 290, 45));

        labelAlamat1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelAlamat1.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelAlamat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 40, 45));

        labelTelepon2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTelepon2.setForeground(new java.awt.Color(0, 80, 208));
        homeView.add(labelTelepon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 40, 45));

        panelRound1.setBackground(new java.awt.Color(0, 80, 208));
        panelRound1.setPreferredSize(new java.awt.Dimension(250, 400));
        panelRound1.setRoundBottomLeft(50);
        panelRound1.setRoundBottomRight(50);
        panelRound1.setRoundTopLeft(50);
        panelRound1.setRoundTopRight(50);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotoProfile.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelRound1.add(fotoProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 28, 184, 209));

        labelNama.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelNama.setForeground(new java.awt.Color(255, 255, 255));
        labelNama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound1.add(labelNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 225, 80));

        labelSaldo.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        labelSaldo.setForeground(new java.awt.Color(255, 255, 255));
        labelSaldo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelRound1.add(labelSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 225, 40));

        homeView.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel9.add(homeView, "card2");

        foodView.setBackground(new java.awt.Color(255, 255, 255));
        foodView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(foodView, "card3");

        pemasukanView.setBackground(new java.awt.Color(255, 255, 255));
        pemasukanView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pemasukanView.add(saldoField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 340, -1));

        submitBtnPanel1.setBackground(new java.awt.Color(0, 80, 208));
        submitBtnPanel1.setForeground(new java.awt.Color(255, 255, 255));
        submitBtnPanel1.setPreferredSize(new java.awt.Dimension(100, 50));

        submitBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitBtn1.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        submitBtn1.setText("SUBMIT");
        submitBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                submitBtn1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout submitBtnPanel1Layout = new javax.swing.GroupLayout(submitBtnPanel1);
        submitBtnPanel1.setLayout(submitBtnPanel1Layout);
        submitBtnPanel1Layout.setHorizontalGroup(
            submitBtnPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        submitBtnPanel1Layout.setVerticalGroup(
            submitBtnPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pemasukanView.add(submitBtnPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, -1, -1));

        clearBtnPanel1.setBackground(new java.awt.Color(0, 80, 208));
        clearBtnPanel1.setForeground(new java.awt.Color(255, 255, 255));
        clearBtnPanel1.setPreferredSize(new java.awt.Dimension(100, 50));

        clearBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn1.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearBtn1.setText("CLEAR");
        clearBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearBtn1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout clearBtnPanel1Layout = new javax.swing.GroupLayout(clearBtnPanel1);
        clearBtnPanel1.setLayout(clearBtnPanel1Layout);
        clearBtnPanel1Layout.setHorizontalGroup(
            clearBtnPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        clearBtnPanel1Layout.setVerticalGroup(
            clearBtnPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pemasukanView.add(clearBtnPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, -1, -1));

        decsField1.setColumns(20);
        decsField1.setRows(5);
        jScrollPane4.setViewportView(decsField1);

        pemasukanView.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 340, 140));

        decsLabel1.setForeground(new java.awt.Color(150, 150, 150));
        decsLabel1.setText("Deskripsi");
        pemasukanView.add(decsLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jPanel9.add(pemasukanView, "card2");

        pengeluaranView.setBackground(new java.awt.Color(255, 255, 255));
        pengeluaranView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pengeluaranView.add(saldoField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 340, -1));

        submitBtnPanel2.setBackground(new java.awt.Color(0, 80, 208));
        submitBtnPanel2.setForeground(new java.awt.Color(255, 255, 255));
        submitBtnPanel2.setPreferredSize(new java.awt.Dimension(100, 50));

        submitBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitBtn2.setForeground(new java.awt.Color(255, 255, 255));
        submitBtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        submitBtn2.setText("SUBMIT");
        submitBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                submitBtn2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout submitBtnPanel2Layout = new javax.swing.GroupLayout(submitBtnPanel2);
        submitBtnPanel2.setLayout(submitBtnPanel2Layout);
        submitBtnPanel2Layout.setHorizontalGroup(
            submitBtnPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        submitBtnPanel2Layout.setVerticalGroup(
            submitBtnPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(submitBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pengeluaranView.add(submitBtnPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 350, -1, -1));

        clearBtnPanel2.setBackground(new java.awt.Color(0, 80, 208));
        clearBtnPanel2.setForeground(new java.awt.Color(255, 255, 255));
        clearBtnPanel2.setPreferredSize(new java.awt.Dimension(100, 50));

        clearBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn2.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearBtn2.setText("CLEAR");
        clearBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearBtn2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout clearBtnPanel2Layout = new javax.swing.GroupLayout(clearBtnPanel2);
        clearBtnPanel2.setLayout(clearBtnPanel2Layout);
        clearBtnPanel2Layout.setHorizontalGroup(
            clearBtnPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        clearBtnPanel2Layout.setVerticalGroup(
            clearBtnPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearBtn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        pengeluaranView.add(clearBtnPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, -1, -1));

        decsLabel.setForeground(new java.awt.Color(150, 150, 150));
        decsLabel.setText("Deskripsi");
        pengeluaranView.add(decsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        decsField.setColumns(20);
        decsField.setRows(5);
        jScrollPane3.setViewportView(decsField);

        pengeluaranView.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 340, 140));

        jPanel9.add(pengeluaranView, "card2");

        statistikView.setBackground(new java.awt.Color(255, 255, 255));
        statistikView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableDataStatistik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Keterangan", "Tipe", "Perubahan Saldo", "Tanggal"
            }
        ));
        tableDataStatistik.setFocusable(false);
        tableDataStatistik.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableDataStatistik.setRowHeight(25);
        tableDataStatistik.setSelectionBackground(new java.awt.Color(255, 172, 58));
        tableDataStatistik.setShowGrid(false);
        tableDataStatistik.getTableHeader().setReorderingAllowed(false);
        tableDataStatistik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataStatistikMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataStatistik);

        jScrollPane1.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        statistikView.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 800, 170));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 80, 208));
        jLabel14.setText("Total Pengeluaran : ");
        statistikView.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));

        totalPengeluaran.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        totalPengeluaran.setForeground(new java.awt.Color(0, 80, 208));
        totalPengeluaran.setText("Total Pemasukan : ");
        statistikView.add(totalPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 80, 208));
        jLabel19.setText("Total Saldo Saat Ini : ");
        statistikView.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 80, 208));
        jLabel21.setText("Total Pemasukan : ");
        statistikView.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, -1));

        totalSaldo.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        totalSaldo.setForeground(new java.awt.Color(0, 80, 208));
        totalSaldo.setText("Total Pemasukan : ");
        statistikView.add(totalSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, -1, -1));

        totalPemasukan.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        totalPemasukan.setForeground(new java.awt.Color(0, 80, 208));
        totalPemasukan.setText("Total Pemasukan : ");
        statistikView.add(totalPemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        printPanel.setBackground(new java.awt.Color(0, 80, 208));
        printPanel.setPreferredSize(new java.awt.Dimension(75, 75));

        printBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        printBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout printPanelLayout = new javax.swing.GroupLayout(printPanel);
        printPanel.setLayout(printPanelLayout);
        printPanelLayout.setHorizontalGroup(
            printPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        printPanelLayout.setVerticalGroup(
            printPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        statistikView.add(printPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 420, -1, -1));
        statistikView.add(pieChartStat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 390, 290));

        jPanel9.add(statistikView, "card7");

        editProfileView.setBackground(new java.awt.Color(255, 255, 255));
        editProfileView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editPassword.setBackground(new java.awt.Color(0, 80, 208));
        editPassword.setForeground(new java.awt.Color(255, 255, 255));
        editPassword.setPreferredSize(new java.awt.Dimension(150, 50));

        editPasswordBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editPasswordBtn.setForeground(new java.awt.Color(255, 255, 255));
        editPasswordBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editPasswordBtn.setText("Edit Password");
        editPasswordBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editPasswordBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout editPasswordLayout = new javax.swing.GroupLayout(editPassword);
        editPassword.setLayout(editPasswordLayout);
        editPasswordLayout.setHorizontalGroup(
            editPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPasswordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        editPasswordLayout.setVerticalGroup(
            editPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editPasswordBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        editProfileView.add(editPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        editProfile.setBackground(new java.awt.Color(0, 80, 208));
        editProfile.setForeground(new java.awt.Color(255, 255, 255));
        editProfile.setPreferredSize(new java.awt.Dimension(150, 50));

        editProfileBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editProfileBtn.setForeground(new java.awt.Color(255, 255, 255));
        editProfileBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editProfileBtn.setText("Edit Profile");
        editProfileBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editProfileBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout editProfileLayout = new javax.swing.GroupLayout(editProfile);
        editProfile.setLayout(editProfileLayout);
        editProfileLayout.setHorizontalGroup(
            editProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        editProfileLayout.setVerticalGroup(
            editProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editProfileBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        editProfileView.add(editProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        editProfileDataView.setBackground(new java.awt.Color(255, 255, 255));
        editProfileDataView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        editProfileDataView.add(namaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 51, 300, -1));
        editProfileDataView.add(alamatField, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 115, 300, -1));
        editProfileDataView.add(teleponField, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 179, 300, -1));

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

        editProfileDataView.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 130, -1));

        fotoProfileEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editProfileDataView.add(fotoProfileEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 184, 209));

        editPanel1.setBackground(new java.awt.Color(0, 80, 208));
        editPanel1.setForeground(new java.awt.Color(255, 255, 255));
        editPanel1.setPreferredSize(new java.awt.Dimension(150, 50));

        editBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBtn1.setForeground(new java.awt.Color(255, 255, 255));
        editBtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editBtn1.setText("Edit Profile");
        editBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editBtn1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout editPanel1Layout = new javax.swing.GroupLayout(editPanel1);
        editPanel1.setLayout(editPanel1Layout);
        editPanel1Layout.setHorizontalGroup(
            editPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        editPanel1Layout.setVerticalGroup(
            editPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        editProfileDataView.add(editPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jPanel2.add(editProfileDataView, "card3");

        editPasswordView.setBackground(new java.awt.Color(255, 255, 255));
        editPasswordView.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passwordField1.setText("passwordField1");
        editPasswordView.add(passwordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 63, 300, -1));

        passwordField2.setText("passwordField2");
        editPasswordView.add(passwordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 157, 300, -1));

        editPanel.setBackground(new java.awt.Color(0, 80, 208));
        editPanel.setForeground(new java.awt.Color(255, 255, 255));
        editPanel.setPreferredSize(new java.awt.Dimension(150, 50));

        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editBtn.setText("Edit Password");
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editBtnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout editPanelLayout = new javax.swing.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        editPasswordView.add(editPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, -1, -1));

        showPassword.setText("Show Password");
        showPassword.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showPasswordItemStateChanged(evt);
            }
        });
        editPasswordView.add(showPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jPanel2.add(editPasswordView, "card2");

        editProfileView.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 820, 410));

        jPanel9.add(editProfileView, "card7");

        bg.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 860, 520));

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
        hiContainer.setText("Hello, User :)");
        bg.add(hiContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 340, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("/");
        bg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 10, -1));

        urlNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        urlNext.setText(" Home");
        bg.add(urlNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 380, -1));

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
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            titleText.setText("Input Saldo Awal");
            urlNext.setText("Input Saldo Awal");
        } else {
            showPanel(true, false, false, false, false, false, false);
            titleText.setText("Home");
            urlNext.setText("Home");
        }
        
        panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
    }//GEN-LAST:event_panelHomeMousePressed

    private void panelEditProfileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditProfileMousePressed
        // TODO add your handling code here:
        editProfileBtn.setIcon(new ImageIcon("images/icons8_profile_32px.png"));
        editPasswordBtn.setIcon(new ImageIcon("images/icons8_password_reset_32px.png"));
        File file = new File("src/foto/"+ loginUser.getGambar());
        profileImg = file;
        BufferedImage bufferedImg;
        try {
            bufferedImg = ImageIO.read(file);
            fotoProfileEdit.setIcon(new ImageIcon(bufferedImg.getScaledInstance(fotoProfileEdit.getWidth(), fotoProfileEdit.getHeight(), Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        namaField.setText(loginUser.getNama());
        alamatField.setText(loginUser.getAlamat());
        teleponField.setText(loginUser.getTelepon());
        namaField.setLabelText("Nama");
        alamatField.setLabelText("Alamat");
        teleponField.setLabelText("Telepon");
        editBtn.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
        editBtn1.setIcon(new ImageIcon("images/icons8_edit_25px.png"));
        selectImgBtn.setIcon(new ImageIcon("images/icons8_image_25px.png"));
        passwordField1.setLabelText("Password");
        passwordField1.setText(loginUser.getPassword());
        passwordField2.setLabelText("Confirm Password");
        passwordField2.setText("");
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        } else {
            titleText.setText("Edit Profile");
            urlNext.setText("Edit Profile");
            showPanel(false, false, true, false, false, false, false);
            panelBackground(new Color(255,188,90), new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        }
    }//GEN-LAST:event_panelEditProfileMousePressed

    private void panelFoodMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFoodMousePressed
        // TODO add your handling code here:
        moodPagiBtn.setIcon(new ImageIcon("images/icons8_Morning_News_32px.png"));
        moodSiangBtn.setIcon(new ImageIcon("images/icons8_midday_32px.png"));
        moodSoreBtn.setIcon(new ImageIcon("images/icons8_sunset_32px.png"));
        moodMalamBtn.setIcon(new ImageIcon("images/icons8_night_32px.png"));
        pesanBtn.setIcon(new ImageIcon("images/icons8_Automatic_Order_32px.png"));
        qty.setValue(0);
        qty.setLabelText("Jumlah Item");
        c = new FoodController();
        initData("Pagi Hari");
        moodText.setText("Pagi Hari");
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        } else {
            titleText.setText("Food Suggestion");
            urlNext.setText("Food Suggestion");
            showPanel(false, false, false, true, false, false, false);
            panelBackground(new Color(255,188,90), new Color(255,188,90), new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        }
    }//GEN-LAST:event_panelFoodMousePressed

    private void panelPemasukanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPemasukanMousePressed
        // TODO add your handling code here:
        saldoField1.setText("");
        decsField1.setText("");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        saldoField1.setLabelText("Pemasukan");
        clearBtn1.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
        submitBtn1.setIcon(new ImageIcon("images/icons8_save_25px_1.png"));
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        } else {
            titleText.setText("Input Pemasukan");
            urlNext.setText("Input Pemasukan");
            showPanel(false, false, false, false, true, false, false);
            panelBackground(new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,207,136), new Color(255,188,90), new Color(255,188,90));
        }
    }//GEN-LAST:event_panelPemasukanMousePressed

    private void panelPengeluaranMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPengeluaranMousePressed
        // TODO add your handling code here:
        saldoField2.setText("");
        decsField.setText("");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        saldoField2.setLabelText("Pengeluaran");
        clearBtn2.setIcon(new ImageIcon("images/icons8_erase_25px.png"));
        submitBtn2.setIcon(new ImageIcon("images/icons8_save_25px_1.png"));
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        } else {
            titleText.setText("Input Pengeluaran");
            urlNext.setText("Input Pengeluaran");
            showPanel(false, false, false, false, false, true, false);
            panelBackground(new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,207,136), new Color(255,188,90));
        }
    }//GEN-LAST:event_panelPengeluaranMousePressed

    private void panelStatistikMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelStatistikMousePressed
        // TODO add your handling code here:
        printBtn.setIcon(new ImageIcon("images/icons8_print_50px.png"));
        pieChartStat.setChartType(DONUT_CHART);
        initData();
        if (newUser) {
            showPanel(false, true, false, false, false, false, false);
            panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
        } else {
            titleText.setText("Statistik");
            urlNext.setText("Statistik");
            showPanel(false, false, false, false, false, false, true);
            panelBackground(new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,207,136));
        }
    }//GEN-LAST:event_panelStatistikMousePressed

    private void submitBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtnMousePressed
        // TODO add your handling code here:
        if(saldoField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Anda Belum Memasukkan Saldo Awal!");
        } else {
            this.a.insertSaldo(this.loginUser, Integer.parseInt(saldoField.getText()), this);
            labelSaldo.setText("Rp." + String.valueOf(this.loginUser.getKeuangan().getTotalSaldo()) + ",00");
        }
    }//GEN-LAST:event_submitBtnMousePressed

    private void clearBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtnMousePressed
        // TODO add your handling code here:
        saldoField.setText("");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
    }//GEN-LAST:event_clearBtnMousePressed

    private void submitBtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtn1MousePressed
        // TODO add your handling code here:
        if(saldoField1.getText().equals("") || decsField1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ada Field Yang Belum Diisi!");
        } else {
//            a.insertSaldo(this.loginUser, Integer.parseInt(saldoField1.getText()), this);
            this.b.insertPemasukan(loginUser, Integer.parseInt(saldoField1.getText()), decsField1.getText(), this);
            labelSaldo.setText("Rp." + String.valueOf(this.loginUser.getKeuangan().getTotalSaldo()) + ",00");
        }
    }//GEN-LAST:event_submitBtn1MousePressed

    private void clearBtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtn1MousePressed
        // TODO add your handling code here:
        saldoField1.setText("");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
    }//GEN-LAST:event_clearBtn1MousePressed

    private void submitBtn2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitBtn2MousePressed
        // TODO add your handling code here:
        if(saldoField2.getText().equals("") || decsField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ada Field yang belum diisi!");
        } else {
            this.b.insertPengeluaran(loginUser, Integer.parseInt(saldoField2.getText()), decsField.getText(), this);
            labelSaldo.setText("Rp." + String.valueOf(this.loginUser.getKeuangan().getTotalSaldo()) + ",00");
        }
    }//GEN-LAST:event_submitBtn2MousePressed

    private void clearBtn2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtn2MousePressed
        // TODO add your handling code here:
        saldoField2.setText("");
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
    }//GEN-LAST:event_clearBtn2MousePressed

    private void tableDataStatistikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataStatistikMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDataStatistikMouseClicked

    private void editProfileBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileBtnMousePressed
        // TODO add your handling code here:
        editProfileDataView.setVisible(true);
        editPasswordView.setVisible(false);
    }//GEN-LAST:event_editProfileBtnMousePressed

    private void editPasswordBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPasswordBtnMousePressed
        // TODO add your handling code here:
        editProfileDataView.setVisible(false);
        editPasswordView.setVisible(true);
    }//GEN-LAST:event_editPasswordBtnMousePressed

    private void editBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMousePressed
        // TODO add your handling code here:
        if(passwordField1.getText().isEmpty() || passwordField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Isi Semua Field Yang Ada!");
        } else {
            if(passwordField1.getText().equals(passwordField2.getText())) {
                a.editPassword(loginUser, passwordField1.getText(), this);
            } else {
                JOptionPane.showMessageDialog(this, "Password dan Confirm Password Berbeda!");
            }
        }
    }//GEN-LAST:event_editBtnMousePressed

    private void showPasswordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showPasswordItemStateChanged
        // TODO add your handling code here:
        if (showPassword.isSelected()) {
            passwordField1.setEchoChar((char)0);
        } else {
            passwordField1.setEchoChar('*');
        }
    }//GEN-LAST:event_showPasswordItemStateChanged

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
                Image image = icon.getImage().getScaledInstance(fotoProfileEdit.getWidth(), fotoProfileEdit.getHeight(), Image.SCALE_SMOOTH);

                ImageIcon ic = new ImageIcon(image);

                fotoProfileEdit.setIcon(ic);

                profileImg = f;
            } else {
                JOptionPane.showMessageDialog(this, "File Tidak Support!");
            }
        }
    }//GEN-LAST:event_selectImgBtnMousePressed

    private void editBtn1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtn1MousePressed
        // TODO add your handling code here:
        if((namaField.getText().equals("")) || (alamatField.getText().equals("")) || (teleponField.getText().equals("")) || profileImg == null) {
            JOptionPane.showMessageDialog(this, "Data Tidak Lengkap! Silahkan Isi Kembali!");
        } else {
            a.editProfile(loginUser, namaField.getText(), alamatField.getText(), teleponField.getText(), profileImg, this);
        }
    }//GEN-LAST:event_editBtn1MousePressed

    private void moodPagiBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moodPagiBtnMousePressed
        // TODO add your handling code here:
        qty.setValue(0);
        initData("Pagi Hari");
        moodText.setText("Pagi Hari");
    }//GEN-LAST:event_moodPagiBtnMousePressed

    private void moodSiangBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moodSiangBtnMousePressed
        // TODO add your handling code here:
        qty.setValue(0);
        initData("Siang Hari");
        moodText.setText("Siang Hari");
    }//GEN-LAST:event_moodSiangBtnMousePressed

    private void moodSoreBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moodSoreBtnMousePressed
        // TODO add your handling code here:
        qty.setValue(0);
        initData("Sore Hari");
        moodText.setText("Sore Hari");
    }//GEN-LAST:event_moodSoreBtnMousePressed

    private void moodMalamBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moodMalamBtnMousePressed
        // TODO add your handling code here:
        qty.setValue(0);
        initData("Malam Hari");
        moodText.setText("Malam Hari");
    }//GEN-LAST:event_moodMalamBtnMousePressed

    private void tableFoodDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFoodDataMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)tableFoodData.getModel();
        int idx = tableFoodData.getSelectedRow();
        
        food_id = Integer.parseInt(model.getValueAt(idx, 0).toString());
        namaItem1.setText(model.getValueAt(idx, 1).toString());
        hargaItem1.setText(model.getValueAt(idx, 2).toString());
        jenisItem.setText(model.getValueAt(idx, 3).toString());
        if(c.getGambar(food_id, loginUser) != null) {
            foodImg = c.getGambar(food_id, loginUser);
            ImageIcon icon = new ImageIcon(foodImg.toString());
            Image image = icon.getImage().getScaledInstance(fotoFood.getWidth(), fotoFood.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(image);

            fotoFood.setIcon(ic);
        }
    }//GEN-LAST:event_tableFoodDataMouseClicked

    private void pesanBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesanBtnMousePressed
        // TODO add your handling code here:
        if(((Integer) qty.getValue() == 0) && (namaItem1.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Item!");
            initData(moodText.getText());
        } else if ((Integer) qty.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "Silahkan Ubah Jumlah Item Yang Ingin Dibeli!");
            initData(moodText.getText());
        } else if (namaItem1.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Item!");
            initData(moodText.getText());
        } else {
            int hargaBeli = Integer.parseInt(hargaItem1.getText()) * (Integer) qty.getValue();
            String pembelian = "Pembelian " + namaItem1.getText() + " sebanyak: " + qty.getValue().toString() + " buah, seharga: " + hargaItem1.getText();
            String tipe = "pengeluaran";
            b.insertPengeluaran(loginUser, hargaBeli, pembelian, this);
        }
    }//GEN-LAST:event_pesanBtnMousePressed

    private void moodPagiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moodPagiMousePressed
        // TODO add your handling code here:
        initData("Pagi Hari");
    }//GEN-LAST:event_moodPagiMousePressed

    private void printBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printBtnMousePressed
        // TODO add your handling code here:
        String totalPemasukanReport = totalPemasukan.getText();
        String totalPengeluaranReport = totalPengeluaran.getText();
        String totalSaldoReport = totalSaldo.getText();
        String pattern = "yyyy-MM-dd-HH-mm-ss";
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        
        try {
            File file = new File("src/main/java/com/kelompok_6/suggestme_view/moneyManagementReport.jrxml");
            JasperDesign jdesign = JRXmlLoader.load(file);
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listHistory);
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("date", new java.util.Date());
            params.put("nama", loginUser.getNama());
            params.put("totalPemasukan", totalPemasukanReport);
            params.put("totalPengeluaran", totalPengeluaranReport);
            params.put("totalSaldo", totalSaldoReport);
            params.put("totalTransaksi", String.valueOf(countPengeluaran+countPemasukan));
            params.put("tableData", itemsJRBean);
            
            JasperPrint jprint = JasperFillManager.fillReport(jreport, params, new JREmptyDataSource());
            
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(new File("src/report/"+loginUser.getNama()+"_Report_" + date + ".pdf"));
                JasperExportManager.exportReportToPdfStream(jprint, outputStream);
                JOptionPane.showMessageDialog(this, "Report Berhasil Dibuat! Cek di: folder_project/src/report");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Report Gagal Dibuat!");
            }
        } catch (JRException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printBtnMousePressed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private textfield.TextField alamatField;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel clearBtn;
    private javax.swing.JLabel clearBtn1;
    private javax.swing.JLabel clearBtn2;
    private javax.swing.JPanel clearBtnPanel;
    private javax.swing.JPanel clearBtnPanel1;
    private javax.swing.JPanel clearBtnPanel2;
    private javax.swing.JTextArea decsField;
    private javax.swing.JTextArea decsField1;
    private javax.swing.JLabel decsLabel;
    private javax.swing.JLabel decsLabel1;
    private javax.swing.JLabel editBtn;
    private javax.swing.JLabel editBtn1;
    private javax.swing.JPanel editPanel;
    private javax.swing.JPanel editPanel1;
    private javax.swing.JPanel editPassword;
    private javax.swing.JLabel editPasswordBtn;
    private javax.swing.JPanel editPasswordView;
    private javax.swing.JPanel editProfile;
    private javax.swing.JLabel editProfileBtn;
    private javax.swing.JPanel editProfileDataView;
    private javax.swing.JPanel editProfileView;
    private javax.swing.JPanel foodSuggestionView;
    private javax.swing.JPanel foodView;
    private javax.swing.JLabel fotoFood;
    private javax.swing.JLabel fotoProfile;
    private javax.swing.JLabel fotoProfileEdit;
    private javax.swing.JLabel hargaItem1;
    private javax.swing.JLabel hiContainer;
    private javax.swing.JPanel homeView;
    private javax.swing.JPanel homeViewInit;
    private javax.swing.JLabel iconContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jenisItem;
    private javax.swing.JLabel labelAlamat;
    private javax.swing.JLabel labelAlamat1;
    private javax.swing.JLabel labelNama;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTelepon;
    private javax.swing.JLabel labelTelepon2;
    private javax.swing.JPanel moodMalam;
    private javax.swing.JLabel moodMalamBtn;
    private javax.swing.JPanel moodPagi;
    private javax.swing.JLabel moodPagiBtn;
    private javax.swing.JPanel moodSiang;
    private javax.swing.JLabel moodSiangBtn;
    private javax.swing.JPanel moodSore;
    private javax.swing.JLabel moodSoreBtn;
    private javax.swing.JLabel moodText;
    private textfield.TextField namaField;
    private javax.swing.JLabel namaItem1;
    private javax.swing.JPanel panelEditProfile;
    private javax.swing.JPanel panelFood;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelPemasukan;
    private javax.swing.JPanel panelPengeluaran;
    private NewPanel.PanelRound panelRound1;
    private javax.swing.JPanel panelStatistik;
    private textfield.PasswordField passwordField1;
    private textfield.PasswordField passwordField2;
    private javax.swing.JPanel pemasukanView;
    private javax.swing.JPanel pengeluaranView;
    private javax.swing.JLabel pesanBtn;
    private javax.swing.JPanel pesanLabel;
    private javaswingdev.chart.PieChart pieChartStat;
    private javax.swing.JLabel printBtn;
    private javax.swing.JPanel printPanel;
    private spinner.Spinner qty;
    private javax.swing.JLabel qtyLabel;
    private textfield.TextField saldoField;
    private textfield.TextField saldoField1;
    private textfield.TextField saldoField2;
    private javax.swing.JLabel selectImgBtn;
    private checkbox.JCheckBoxCustom showPassword;
    private javax.swing.JPanel sidePane;
    private javax.swing.JPanel statistikView;
    private javax.swing.JLabel submitBtn;
    private javax.swing.JLabel submitBtn1;
    private javax.swing.JLabel submitBtn2;
    private javax.swing.JPanel submitBtnPanel;
    private javax.swing.JPanel submitBtnPanel1;
    private javax.swing.JPanel submitBtnPanel2;
    private javax.swing.JTable tableDataStatistik;
    private javax.swing.JTable tableFoodData;
    private textfield.TextField teleponField;
    private javax.swing.JLabel titleText;
    private javax.swing.JLabel totalPemasukan;
    private javax.swing.JLabel totalPengeluaran;
    private javax.swing.JLabel totalSaldo;
    private javax.swing.JLabel urlNext;
    // End of variables declaration//GEN-END:variables
}
