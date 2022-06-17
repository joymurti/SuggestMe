/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_controller;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import com.kelompok_6.suggestme_model.Admin;
import com.kelompok_6.suggestme_model.Customer;
import com.kelompok_6.suggestme_model.Food;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wwijo
 */
public class FoodController {
    private Connection conn;
    private Statement st;
    private PreparedStatement stmt;
    private List<Food> foodData = new ArrayList<Food>();
    
    public FoodController() {}
   
    public List <Food> getFoodData(Admin admin) {
       this.foodData = new ArrayList<Food>();
        try{
            String sql = "SELECT * FROM food_suggestion WHERE admin_id=?";
            stmt = admin.getConnection().prepareStatement(sql);
            stmt.setInt(1, admin.getId());
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Food food = new Food(rs.getString("mood"), admin);
                food.setFoodData(rs.getInt("id"), rs.getString("nama"), rs.getString("jenis"), rs.getInt("harga"), rs.getString("gambar"));
                this.foodData.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return this.foodData;
    }
    
    public List <Food> getFoodData(String mood, Customer customer) {
        try{
            String sql = "SELECT * FROM food_suggestion WHERE harga <= (SELECT total_saldo FROM keuangan WHERE user_id=?) AND mood=?";
            PreparedStatement stmt = customer.getConnection().prepareStatement(sql);
            stmt.setInt(1, customer.getId());
            stmt.setString(2, mood);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Food food = new Food(rs.getString("mood"));
                food.setFoodData(rs.getInt("id"), rs.getString("nama"), rs.getString("jenis"), rs.getInt("harga"), rs.getString("gambar"));
                this.foodData.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return this.foodData;
    }
    
    public File getGambar(int id, Admin admin) {
        File gambar = null;
        try {
            stmt = admin.getConnection().prepareStatement("SELECT gambar from food_suggestion WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                gambar = new File("src/foto/"+ rs.getString("gambar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gambar;
    }
    
    public File getGambar(int id, Customer user) {
        File gambar = null;
        try {
            stmt = user.getConnection().prepareStatement("SELECT gambar from food_suggestion WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                gambar = new File("src/foto/"+ rs.getString("gambar"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gambar;
    }
    
    public void insertFoodData(String namaItem, String jenisItem, String moodItem, int hargaItem, File gambar, Admin admin) {
        try {
            stmt = admin.getConnection().prepareStatement("INSERT INTO food_suggestion (nama, jenis, mood, harga, gambar, admin_id) VALUES (?,?,?,?,?,?) ");
            stmt.setString(1, namaItem);
            stmt.setString(2, jenisItem);
            stmt.setString(3, moodItem);
            stmt.setInt(4, hargaItem);
            stmt.setString(5, gambar.getName());
            stmt.setInt(6, admin.getId());
            int row = stmt.executeUpdate();
            if (row == 1) {
                try {
                    Files.copy(gambar.toPath(), new File("src/foto/" + gambar.getName()).toPath());
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
                } catch (IOException ex) {
                    Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Data gagal disimpan!");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateFoodData(String namaItem, String jenisItem, String moodItem, int hargaItem, File gambar, int food_id, Admin admin) {
        try {
            if(gambar == null) {
                JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data!");
            }
            File gambarBefore = getGambar(food_id, admin);
            stmt = admin.getConnection().prepareStatement("UPDATE food_suggestion SET nama=?, jenis=?, mood=?, harga=?, gambar=? WHERE id=?");
            stmt.setString(1, namaItem);
            stmt.setString(2, jenisItem);
            stmt.setString(3, moodItem);
            stmt.setInt(4, hargaItem);
            stmt.setString(5, gambar.getName());
            stmt.setInt(6, food_id);
            int row = stmt.executeUpdate();
            if (row == 1) {
                try {
                    if(!(gambarBefore.getName().equals("default_food.jpg")) && !(gambarBefore.getName().equals(gambar.getName()))) {
                            gambarBefore.delete();
                    }
                    if((gambarBefore != null) && (gambar != null)) {
                        if(!(gambarBefore.getName().equals(gambar.getName()))) {
                            Files.copy(gambar.toPath(), new File("src/foto/" + gambar.getName()).toPath());
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                        } else if ((row == 1) && (gambarBefore.getName().equals(gambar.getName()))) {
                            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Gambar gagal disimpan/diubah!");
                        }
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
    
    public void deleteFoodData(int id, Admin admin, File gambar) {
        File gambarBefore = getGambar(id, admin);
        try {
            stmt = admin.getConnection().prepareStatement("DELETE FROM food_suggestion WHERE id=?");
            stmt.setInt(1, id);
            stmt.addBatch();
            stmt.addBatch("SET  @num := 0");
            stmt.addBatch("UPDATE food_suggestion SET id = @num := (@num+1)");
            stmt.addBatch("ALTER TABLE food_suggestion AUTO_INCREMENT =1");
            int[] row = stmt.executeBatch();
            if(row.length > 0) {
                if(!(gambarBefore.getName().equals("default_food.jpg"))) {
                    gambarBefore.delete();
                }
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            }
            stmt.clearBatch();
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
