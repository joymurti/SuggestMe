/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author wwijo
 */
public class Customer extends Person {
    final int role = 2;
    private int id;
    private Keuangan keuanganUser;
    private History latestHistoryUser;
    private List<History> allHistoryUser = new ArrayList<History>();
    
    public Customer() {
        setConnection();
        keuanganUser = new Keuangan(0);
    }
    
    public Customer(String username, String password) {
        setUsername(username);
        setPassword(password);
        setConnection();
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    @Override
    public int getRole() {
        return this.role;
    }
    
    public String getRoleString() {
        return "User";
    }
    
    public void setKeuanganUser(Keuangan a) {
        this.keuanganUser = a;
    }
    
    public Keuangan getKeuangan() {
        return this.keuanganUser;
    }
    
    public void setLatestHistoryUser(History a) {
        this.latestHistoryUser = a;
    }
    
    public History getHistoryUser() {
        return this.latestHistoryUser;
    }
    
    public List <History> getAllHistoryUser(int id) {
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            String sql = "SELECT * FROM history WHERE user_id=?";
            PreparedStatement stmt = this.connect.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                History history = new History(rs.getString("keterangan"), rs.getInt("perubahan_saldo"), rs.getString("tipe"), rs.getDate("tanggal"));
                this.allHistoryUser.add(history);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot get data from table");
        }
        return this.allHistoryUser;
    }
}
