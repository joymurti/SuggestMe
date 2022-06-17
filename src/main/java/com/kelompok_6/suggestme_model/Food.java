/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

import javax.swing.JOptionPane;

/**
 *
 * @author wwijo
 */
public class Food {
    private String mood;
    private int food_id;
    private String nama;
    private String jenis;
    private int harga;
    private String gambar;
    private Admin admin;
    
    public Food(String mood, Admin admin) {
        this.mood = mood;
        this.admin = admin;
    }
    
    public Food(String mood) {
        this.mood = mood;
    }
    
    public void setMood(String mood) {
        this.mood = mood;
    }
    
    public void setFoodData(int food_id, String nama, String jenis, int harga, String gambar) {
        this.food_id = food_id;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
        this.gambar = gambar;
    }
    
    public String getMood() {
        return this.mood;
    }
    
    public String[] getFood() {
        String[] food = {String.valueOf(this.food_id), this.nama, String.valueOf(this.harga), this.jenis, this.gambar};
        return food;
    }
}
