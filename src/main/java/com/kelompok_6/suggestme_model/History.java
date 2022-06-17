/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

import java.util.Date;

/**
 *
 * @author wwijo
 */
public class History {
    private String keterangan;
    private int perubahanSaldo;
    private String tipe;
    private java.util.Date tanggal;
    
    public History(String keterangan, int perubahanSaldo, String tipe) {
        this.keterangan = keterangan;
        this.perubahanSaldo = perubahanSaldo;
        this.tipe = tipe;
        this.tanggal = new java.util.Date();
    }
    
    public History(String keterangan, int perubahanSaldo, String tipe, Date tanggal) {
        this.keterangan = keterangan;
        this.perubahanSaldo = perubahanSaldo;
        this.tipe = tipe;
        this.tanggal = tanggal;
    }
    
    public void setDetailHistory(String keterangan, int perubahanSaldo, String tipe, Date tanggal) {
        this.keterangan = keterangan;
        this.perubahanSaldo = perubahanSaldo;
        this.tipe = tipe;
        this.tanggal = tanggal;
    }
    
    public int getPerubahanSaldo() {
        return this.perubahanSaldo;
    }
    
    public String getKeterangan() {
        return this.keterangan;
    }
    
    public String getTipe() {
        return this.tipe;
    }
    
    public Date getTanggal() {
        return this.tanggal;
    }
}
