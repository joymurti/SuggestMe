/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_controller;

import com.kelompok_6.suggestme_model.Customer;
import com.kelompok_6.suggestme_model.History;
import com.kelompok_6.suggestme_view.Home;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author wwijo
 */
public class MoneyManagementController {
    private PreparedStatement stmt;
    
    public void insertPemasukan(Customer customer, int pemasukan, String deskripsi, Home home) {
        customer.getKeuangan().setTotalSaldo((customer.getKeuangan().getTotalSaldo()) + pemasukan);
        customer.setLatestHistoryUser(new History(deskripsi, pemasukan, "pemasukan"));
        try {
            stmt = (PreparedStatement) customer.getConnection().prepareStatement("UPDATE keuangan SET total_saldo=? WHERE user_id=?");
            stmt.setInt(1, customer.getKeuangan().getTotalSaldo());
            stmt.setInt(2, customer.getId());
            int row = stmt.executeUpdate();
            if(row!=0) {
                stmt = (PreparedStatement) customer.getConnection().prepareStatement("INSERT INTO history (user_id, keterangan, perubahan_saldo, tipe) VALUES (?,?,?,?)");
                stmt.setInt(1, customer.getId());
                stmt.setString(2, customer.getHistoryUser().getKeterangan());
                stmt.setInt(3, customer.getHistoryUser().getPerubahanSaldo());
                stmt.setString(4, customer.getHistoryUser().getTipe());
                row = stmt.executeUpdate();
                if (row == 1) {
                    JOptionPane.showMessageDialog(null, "Data Pemasukan Anda Telah Dicatat!");
                    home.showPanel(true, false, false, false, false, false, false);
                    home.panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
                    home.changeAttr("Home");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertPengeluaran(Customer customer, int pengeluaran, String deskripsi, Home home) {
        int pengeluaranPrefix = pengeluaran * -1;
        if (customer.getKeuangan().getTotalSaldo() < pengeluaran) {
            JOptionPane.showMessageDialog(null, "Data Pengeluaran Anda Gagal Dicatat! (Saldo < Pengeluaran)");
        } else {
            customer.getKeuangan().setTotalSaldo((customer.getKeuangan().getTotalSaldo()) - pengeluaran);
            customer.setLatestHistoryUser(new History(deskripsi, pengeluaranPrefix, "pengeluaran"));
                
            try {
                stmt = (PreparedStatement) customer.getConnection().prepareStatement("UPDATE keuangan SET total_saldo=? WHERE user_id=?");
                stmt.setInt(1, customer.getKeuangan().getTotalSaldo());
                stmt.setInt(2, customer.getId());
                int row = stmt.executeUpdate();
                if(row!=0) {
                    stmt = (PreparedStatement) customer.getConnection().prepareStatement("INSERT INTO history (user_id, keterangan, perubahan_saldo, tipe) VALUES (?,?,?,?)");
                    stmt.setInt(1, customer.getId());
                    stmt.setString(2, customer.getHistoryUser().getKeterangan());
                    stmt.setInt(3, customer.getHistoryUser().getPerubahanSaldo());
                    stmt.setString(4, customer.getHistoryUser().getTipe());
                    row = stmt.executeUpdate();
                    if (row == 1) {
                        JOptionPane.showMessageDialog(null, "Data Pengeluaran Anda Telah Dicatat!");
                        home.showPanel(true, false, false, false, false, false, false);
                        home.panelBackground(new Color(255,207,136), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90), new Color(255,188,90));
                        home.changeAttr("Home");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}