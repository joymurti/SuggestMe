/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

/**
 *
 * @author wwijo
 */
public class Keuangan {
    private int user_id;
    private int total_saldo;
    
    public Keuangan(int total_saldo) {
        this.total_saldo = total_saldo;
    }
    
    public Keuangan(int user_id, int total_saldo) {
        this.user_id = user_id;
        this.total_saldo = total_saldo;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    public void setTotalSaldo(int total_saldo) {
        this.total_saldo = total_saldo;
    }
    
    public int getUserId() {
        return this.user_id;
    }
    
    public int getTotalSaldo() {
        return this.total_saldo;
    }
    
    public String getTotalSaldoString() {
        return String.valueOf(this.getTotalSaldo());
    }
}
