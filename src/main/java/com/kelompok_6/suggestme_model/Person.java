/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok_6.suggestme_model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author wwijo
 */
public abstract class Person implements DBInterface {
    //Encapsulation    
    private final String databaseName = "diriku";
    private final String databaseUserName = "root";
    private final String databasePassword = "";
    protected Connection connect = null;
    
    //Person Attribute    
    private String username;
    private String password;
    private String nama;
    private String alamat;
    private String telepon;
    private String gambar;
    
    Person() {}
    
    //Implements DBInterface    
    @Override
    public void setConnection() {
        try {
          this.connect = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseName, databaseUserName, databasePassword);
          if(this.connect != null) {
              System.out.println("Connection Established!");
          }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Connection getConnection() {
        return this.connect;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public String getAlamat() {
        return this.alamat;
    }
    
    public String getTelepon() {
        return this.telepon;
    }
    
    public String getGambar() {
        return this.gambar;
    }
    
    public String[] getLogin() {
        String[] login = {this.username, this.password};
        return login;
    }
    
    public int getRole() {
        return 0;
    }
    
    public String[] getProfile() {
        String[] profile = {this.nama, this.alamat, this.telepon, this.gambar};
        return profile;
    }
}
