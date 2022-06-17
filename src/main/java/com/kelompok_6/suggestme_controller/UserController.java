/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_controller;

import com.kelompok_6.suggestme_model.Admin;
import com.kelompok_6.suggestme_model.Customer;
import com.kelompok_6.suggestme_model.Food;
import com.kelompok_6.suggestme_model.Keuangan;
import com.kelompok_6.suggestme_model.Person;
import com.kelompok_6.suggestme_view.AdminHome;
import com.kelompok_6.suggestme_view.Home;
import com.kelompok_6.suggestme_view.LoginForm;
import com.kelompok_6.suggestme_view.RegisterForm;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wwijo
 */
public class UserController {
    private PreparedStatement stmt;
    private Person loginUser;
    private Customer registeredUser;
    
    public UserController(String role) {
        if(role.equals("Admin")) {
            this.loginUser = new Admin();
        } else if(role.equals("User")) {
            this.loginUser = new Customer();
        }
    }
    
    public void Login(String role, String username, String password, LoginForm loginView) throws IOException {
        try {        
            if(role.equals("Admin")) {
                stmt = (PreparedStatement) loginUser.getConnection().prepareStatement("SELECT * FROM user WHERE username=? AND password=?"); 
            } else if (role.equals("User")) {
                stmt = (PreparedStatement) loginUser.getConnection().prepareStatement("SELECT user.id, user.role_id, user.username, user.password, user.nama, user.alamat, user.alamat, user.telepon, user.gambar, keuangan.total_saldo FROM user INNER JOIN keuangan ON user.id = keuangan.user_id WHERE username=? AND password=?"); 
            }
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if((rs.getInt("role_id") == 1) && (role.equals("Admin"))) {
                    Admin newLoginUser = (Admin) loginUser;
                    newLoginUser.setUsername(rs.getString("username"));
                    newLoginUser.setPassword(rs.getString("password"));
                    newLoginUser.setNama(rs.getString("nama"));
                    newLoginUser.setAlamat(rs.getString("alamat"));
                    newLoginUser.setTelepon(rs.getString("telepon"));
                    newLoginUser.setGambar(rs.getString("gambar"));
//                    Direct to admin view:
                    AdminHome a = new AdminHome(newLoginUser);
                    JOptionPane.showMessageDialog(null, "You have successfully logged in");
                    loginView.dispose();
                    a.setVisible(true);
                  
                } else if((rs.getInt("role_id") == 2) && (role.equals("User"))) {
                    Customer newLoginUser = (Customer) loginUser;
                    newLoginUser.setUsername(rs.getString("username"));
                    newLoginUser.setPassword(rs.getString("password"));
                    newLoginUser.setNama(rs.getString("nama"));
                    newLoginUser.setAlamat(rs.getString("alamat"));
                    newLoginUser.setTelepon(rs.getString("telepon"));
                    newLoginUser.setGambar(rs.getString("gambar"));
                    newLoginUser.setId(rs.getInt("id"));
                    newLoginUser.setKeuanganUser(new Keuangan(rs.getInt("id"), rs.getInt("total_saldo")));
                        
                    if(newLoginUser.getKeuangan().getTotalSaldo() == 0) {
//                        Direct view add saldo baru:
//                        UserHomeInit a = new UserHomeInit(newLoginUser, rs.getInt("id"));
//                        JOptionPane.showMessageDialog(this, "You have successfully logged in");
//                        dispose();
//                        a.setVisible(true);
                        Home a = new Home(newLoginUser, true);
                        JOptionPane.showMessageDialog(null, "You have successfully logged in");
                        loginView.dispose();
                        a.setVisible(true);  
                    } else {
//                        Direct view user lama:
                        Home a = new Home(newLoginUser, false);
                        JOptionPane.showMessageDialog(null, "You have successfully logged in");
                        loginView.dispose();
                        a.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username, Password Atau Role Salah!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username, Password Atau Role Salah!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void register(String username, String password, String re_password, String nama, String alamat, String telepon, RegisterForm registerView) {
        if(!(password.equals(re_password))) {
            JOptionPane.showMessageDialog(null, "Password dan Konfirmasi Password Tidak Sama!");
        } else {
            registeredUser = new Customer(username, password);
        
            registeredUser.setNama(nama);
            registeredUser.setAlamat(alamat);
            registeredUser.setTelepon(telepon);
            registeredUser.setKeuanganUser(new Keuangan (0));

            try {
                PreparedStatement st = (PreparedStatement) registeredUser.getConnection().prepareStatement("INSERT INTO user (role_id, username, password, nama, alamat, telepon, gambar) VALUES (?,?,?,?,?,?,?)");
                st.setInt(1, registeredUser.getRole());
                st.setString(2, registeredUser.getUsername());
                st.setString(3, registeredUser.getPassword());
                st.setString(4, registeredUser.getNama());
                st.setString(5, registeredUser.getAlamat());
                st.setString(6, registeredUser.getTelepon());
                st.setString(7, "profile.png");
                int row = st.executeUpdate();
                if(row != 0) {
                    st = (PreparedStatement) registeredUser.getConnection().prepareStatement("INSERT INTO keuangan (user_id, total_saldo) VALUES ((SELECT id FROM user WHERE username=?),?)");
                    st.setString(1, registeredUser.getUsername());
                    st.setInt(2, registeredUser.getKeuangan().getTotalSaldo());
                    row = st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil Registrasi!");
                    LoginForm a = new LoginForm();
                    registerView.dispose();
                    a.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal Melakukan Registrasi! Coba Lagi!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insertSaldo(Customer user, int saldo, Home initHome) {
        try {
            stmt = (PreparedStatement) user.getConnection().prepareStatement("UPDATE keuangan SET total_saldo=? WHERE user_id=?");
            stmt.setInt(1, saldo);
            stmt.setInt(2, user.getId());
            int row = stmt.executeUpdate();
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Saldo Awal Berhasil Ditambahkan!");
                Home a = new Home(user, false);
                initHome.dispose();
                a.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Saldo Awal Gagal Ditambahkan!");
        }
    }
    
    public void editPassword(Customer user, String password, Home home) {
        try {
            stmt = (PreparedStatement) user.getConnection().prepareStatement("UPDATE user SET password=? WHERE id=?");
            stmt.setString(1, password);
            stmt.setInt(2, user.getId());
            int row = stmt.executeUpdate();
            if (row == 1) {
                JOptionPane.showMessageDialog(null, "Password Berhasil Di Update!");
                home.showPanel(true, false, false, false, false, false, false);
                home.panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
                home.changeAttr("Home");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Saldo Awal Gagal Ditambahkan!");
        }
    }
    
    public File getGambar(Customer user) {
        File gambar = null;
        try {
            stmt = user.getConnection().prepareStatement("SELECT gambar from user WHERE id=?");
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                gambar = new File("src/foto/"+ rs.getString("gambar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gambar;
    }
    
    public void editProfile(Customer user, String nama, String alamat, String telepon, File gambar, Home home) {
        try {
            if(gambar == null) {
                JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data!");
            }
            File gambarBefore = getGambar(user);
            stmt = user.getConnection().prepareStatement("UPDATE user SET nama=?, alamat=?, telepon=?, gambar=? WHERE id=?");
            stmt.setString(1, nama);
            stmt.setString(2, alamat);
            stmt.setString(3, telepon);
            stmt.setString(4, gambar.getName());
            stmt.setInt(5, user.getId());
            int row = stmt.executeUpdate();
            if (row == 1) {
                try {
                    if(!(gambarBefore.getName().equals("profile.png")) && !(gambarBefore.getName().equals(gambar.getName()))) {
                            gambarBefore.delete();
                    }
                    if((gambarBefore != null) && (gambar != null)) {
                        if(!(gambarBefore.getName().equals(gambar.getName()))) {
                            Files.copy(gambar.toPath(), new File("src/foto/" + gambar.getName()).toPath());
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                            user.setNama(nama);
                            user.setAlamat(alamat);
                            user.setTelepon(telepon);
                            user.setGambar(gambar.getName());
                        } else if ((row == 1) && (gambarBefore.getName().equals(gambar.getName()))) {
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                            user.setNama(nama);
                            user.setAlamat(alamat);
                            user.setTelepon(telepon);
                            user.setGambar(gambar.getName());
                        } else {
                            JOptionPane.showMessageDialog(null, "Gambar gagal disimpan/diubah!");
                        }
                        home.initDataUpdate(nama, alamat, telepon, gambar);
                        home.showPanel(true, false, false, false, false, false, false);
                        home.panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
                        home.changeAttr("Home");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Data Gagal Diubah!");
                }
            }
        } catch (SQLException ex) {
                Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
